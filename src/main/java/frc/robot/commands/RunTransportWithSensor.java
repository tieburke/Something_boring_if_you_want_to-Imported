// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class RunTransportWithSensor extends CommandBase {

  /** Creates a new RunTransportWithSensor. */
    private Integer transportState;
    private final Transport transport;
    Timer timer;
    boolean endCommand;
  public RunTransportWithSensor(Transport transport) {
    this.transport = transport;
    transportState = 0;
    endCommand = false;
    timer = new Timer();
    addRequirements(transport);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    transportState = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    timer.reset();
    timer.stop();
    timer.start();
    while(timer.get() < .3){
      transport.setPinkTransport(.3);
    }
    while(!transport.getPinkInput()){
      transport.setPinkTransport(.3);
    }
      transport.setPinkTransport(0);
      endCommand = true;
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transport.setPinkTransport(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return endCommand;
  }
}
