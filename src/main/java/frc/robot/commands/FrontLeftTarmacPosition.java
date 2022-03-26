// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autocommands.AutoDrive;
import frc.robot.commands.autocommands.AutoIntakeGoSpin;
import frc.robot.commands.autocommands.AutoIntakeStop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FrontLeftTarmacPosition extends SequentialCommandGroup {
  /** Creates a new FrontLeftTarmacPosition. */

  //Intake intake;
  //Drivetrain drivetrain;
  //RamseteCommand ramseteCommand;
  
  public FrontLeftTarmacPosition(Intake intake, Drivetrain drivetrain) {
    //this.intake = intake;
    //this.drivetrain = drivetrain;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(    
      new AutoIntakeGoSpin(intake),
      new AutoDrive(0.5, 3, drivetrain)

      //without this the code wont work
    ); 
  }
}

