// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoIntakeGoSpin extends CommandBase {

Timer timer;

private final Intake intake;

  /** Creates a new SetShooterVelocity. */
  public AutoIntakeGoSpin(Intake intake) {

    timer = new Timer();
    this.intake = intake;
    addRequirements(intake);

    timer.stop();
    timer.reset();
    timer.start();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    SmartDashboard.putString("intake started", "intake started");
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     timer.start();
     intake.setIntakeMotor(1);
    SmartDashboard.putString("intake running", "intake running");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
     timer.stop();
     timer.reset();
     intake.setIntakeMotor(0);
    SmartDashboard.putString("intake stopped", "intake stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  return (timer.get() > 3);
  //return true;
  }
}