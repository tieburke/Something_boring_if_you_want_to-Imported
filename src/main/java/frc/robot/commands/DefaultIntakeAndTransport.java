// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

public class DefaultIntakeAndTransport extends CommandBase {
  /** Creates a new DefaultIntakeAndTransport. */
  Intake intake;
  Transport transport;
  int ballIn;
  Timer timer;
  BooleanSupplier ballReset;
  public DefaultIntakeAndTransport(Intake intake, Transport transport, int ballIn, BooleanSupplier ballReset) {
    this.intake = intake;
    this.transport = transport;
    this.ballIn = ballIn;
    this.ballReset = ballReset;
    timer = new Timer();

    addRequirements(intake, transport);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
      if(transport.getBallInput() && ballIn == 0){
        transport.setPinkTransport(.7);
        ballIn++;
      }
      if(transport.getPinkInput() && !transport.getBallInput() && ballIn == 1){
        transport.setPinkTransport(0);
        ballIn++;
      }
      
      if(transport.getBallInput() && ballIn == 2){
        timer.reset();
        timer.stop();
        timer.start();
        ballIn++;
        while(timer.get() < .3){
          transport.setPinkTransport(.7);
        }
        transport.setPinkTransport(0);
        
      }

      if(ballReset.getAsBoolean()){
        ballIn = 0;
      }

      SmartDashboard.putNumber("timer", timer.get());
      SmartDashboard.putNumber("ballIn", ballIn);

      

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transport.setPinkTransport(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
