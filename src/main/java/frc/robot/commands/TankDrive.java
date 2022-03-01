// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class TankDrive extends CommandBase {
  /**  Creates a new TankDrive. */
  Drivetrain drivetrain;
  DoubleSupplier forward, rotation;
  BooleanSupplier invert;
  Boolean shift1, shift2;

  private int direction = 1;

  public TankDrive(DoubleSupplier forward, DoubleSupplier rotation, BooleanSupplier invert, 
                  Drivetrain drivetrain, boolean shift1, boolean shift2) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
    
    this.forward = forward;
    this.rotation = rotation;
    this.invert = invert;
    this.drivetrain = drivetrain;
    this.shift1 = shift1;
    this.shift2 = shift2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(invert.getAsBoolean()) direction *= -1;

    double speed = forward.getAsDouble() * direction;
    double rotate = rotation.getAsDouble();


    drivetrain.arcadeDrive(speed, rotate, false);

    if (shift1){
      drivetrain.setGearShift(true);
    }
    if (shift2){
      drivetrain.setGearShift(false);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.arcadeDrive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
