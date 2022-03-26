// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class RunUpperTransportLow extends CommandBase {

  private final Transport transport;
  /** Creates a new RunUpperTransport. */
  public RunUpperTransportLow(Transport transport) {

    this.transport = transport;
    addRequirements(transport);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //transport.setTopTransport(1);  uncomment this if you want to run by power
    //low is 3100, high is 5500
    transport.setTopTransportRPM(3100);
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
