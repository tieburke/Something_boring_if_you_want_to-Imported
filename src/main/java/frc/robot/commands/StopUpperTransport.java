// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class StopUpperTransport extends CommandBase {

  private final Transport transport;
  /** Creates a new RunPinkTransport. */
  public StopUpperTransport(Transport transport) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.transport = transport;
    addRequirements(transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //transport.setTopTransport(0);  uncomment if you want to run top transport using power
    transport.setTopTransportRPM(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
