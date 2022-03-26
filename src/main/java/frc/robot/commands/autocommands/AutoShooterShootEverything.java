// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.RunTransportWithSensor;
import frc.robot.commands.RunUpperTransportLow;
import frc.robot.commands.StopUpperTransport;
import frc.robot.subsystems.Transport;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShooterShootEverything extends SequentialCommandGroup {
  /** Creates a new ShooterShootEverything. */
  Transport transport;
  public AutoShooterShootEverything(Transport transport) {
    this.transport = transport;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      
      new RunUpperTransportLow(transport),
      new WaitCommand(3),
       new RunTransportWithSensor(transport),
        new WaitCommand(1), 
        new RunTransportWithSensor(transport), 
        new WaitCommand(1), 
        new RunTransportWithSensor(transport), 
        new WaitCommand(1), 
        new RunTransportWithSensor(transport), 
        new WaitCommand(1), 
        new StopUpperTransport(transport)
        );
  }
}
