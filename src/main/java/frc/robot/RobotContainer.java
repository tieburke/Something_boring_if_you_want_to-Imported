// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.Limelight;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick stick;
  private final XboxController xbox;
  private final Drivetrain drivetrain;
  private final Limelight limelight;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    stick = new Joystick(0);
    xbox = new XboxController(1);
    drivetrain = new Drivetrain();
    limelight = new Limelight();
    // Configure the button bindings
    configureButtonBindings();

    setDefaultCommands();
  }

  private void setDefaultCommands(){
	  drivetrain.setDefaultCommand(new TankDrive(() -> stick.getY(), () -> stick.getX(), () -> stick.getRawButtonPressed(1), drivetrain));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12;
    final JoystickButton xA, xB, xX, xY, xRB, xLB, xSelect, xMenu, x9, x10;

    j1 = new JoystickButton(stick, 1);
    j2 = new JoystickButton(stick, 2);
    j3 = new JoystickButton(stick, 3);
    j4 = new JoystickButton(stick, 4);
    j5 = new JoystickButton(stick, 5);
    j6 = new JoystickButton(stick, 6);
    j7 = new JoystickButton(stick, 7);
    j8 = new JoystickButton(stick, 8);
    j9 = new JoystickButton(stick, 9);
    j10 = new JoystickButton(stick, 10);
    j11 = new JoystickButton(stick, 11);
    j12 = new JoystickButton(stick, 12);

    xA = new JoystickButton(xbox, 1);
    xB = new JoystickButton(xbox, 2);
    xX = new JoystickButton(xbox, 3);
    xY = new JoystickButton(xbox, 4);
    xLB = new JoystickButton(xbox, 5);
    xRB = new JoystickButton(xbox, 6);
    xMenu = new JoystickButton(xbox, 7);

    xSelect = new JoystickButton(xbox, 8);
    x9 = new JoystickButton(xbox, 9);
    x10 = new JoystickButton(xbox, 10);
    

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    // An ExampleCommand will run in autonomous
  }
}
