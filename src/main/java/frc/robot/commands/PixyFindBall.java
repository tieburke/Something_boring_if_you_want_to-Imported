// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.Pixy2;
import frc.robot.util.Pixy2Obj;

public class PixyFindBall extends CommandBase {
  
  private Drivetrain drivetrain;
  private Pixy2Obj pixy;
  private PIDController pid;
  private Timer indexTimer;

  private double turn, kP;
  private boolean found, finished, sensorWasTriggered;

  double maxVal = 0;

  public PixyFindBall(Drivetrain drivetrain, Pixy2Obj pixy) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.pixy = pixy;
    turn = 0; 
    found = false;
    finished = false;
    kP = 160;

    indexTimer = new Timer();
    pid = new PIDController(.0025, 0, 0);
    //pid.setSetpoint(160);
  }

  @Override
  public void initialize() {
    found = false;
    finished = false;
    sensorWasTriggered = false;
    kP = 160;
  }
    

  @Override
  public void execute() {
    pixy.updateValues();  //updates pixy values

    if(pixy.pixySig(1) || pixy.pixySig(2)) {
      found = true;
    } // found if statement
    else if(pixy.pixySig(0)) {
      pixy.updateValues();
      //drivetrain.arcadeDrive(0, 0);
    }
    else {
      pixy.updateValues();
      //drivetrain.arcadeDrive(0,0);
    }



      if(Math.abs(-(pid.calculate(kP))) > Math.abs(maxVal)) {
        maxVal = -(pid.calculate(kP));
      }


      if(pixy.getPixyX() != 0) {
        kP = (pixy.getPixyX() - 160);
      }
      turn = -(pid.calculate(kP));
      /* turn 
      better */
      
      if(turn > .175) {
        turn += .2;
      }
      else if (turn < -.175) {
        turn += -.2;
      }
      
      drivetrain.arcadeDrive(.4, turn);

      SmartDashboard.putNumber("Pixy Turn", turn);
      SmartDashboard.putNumber("Pixy MaxVal", maxVal);
  }


/*
   //(pixySig(0) means no ball, pixySig(1) means ball seen)
      if(pixy.pixySig(0)){ //checks for ball, if no ball, do this 
        pixy.updateValues(); //checks for values before turning
        drivetrain.arcadeDrive(0, 0); //spins left in a circle, no forward movement
      }
      if(pixy.pixySig(1)){ //when pixy sees ball, do this
        pixy.updateValues();
        int x = pixy.getPixyX();  
        int y = pixy.getPixyY();
        //fare warning: this code is bad
        //if its bad just use higgens cause after i looked at it for 12 minutes it made more sense adn was more organized
        if (x < 150){  //while the ball is left of the center of the pixy (x=160), turn left while moving forward
          pixy.updateValues();
          drivetrain.arcadeDrive(0.1, .25);
          pixy.updateValues();
        }
        else if (x > 170){ //while the ball is right of the center of the pixy, turn right while moving forward
          pixy.updateValues();
          drivetrain.arcadeDrive(.1, -.25);
          pixy.updateValues();
        }
        else if (x > 150 && x < 170){ //when ball is somewhat centered drive forward
          pixy.updateValues();
          drivetrain.arcadeDrive(.4, 0);
          pixy.updateValues();
        }
      }
*/


      
  
    

  @Override
  public void end(boolean interrupted) {
    drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}