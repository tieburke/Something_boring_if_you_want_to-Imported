// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultIntakeAndTransport;
import frc.robot.commands.FrontLeftTarmacPosition;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeGoSpin;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.NewDefaultIntakeAndTransport;
import frc.robot.commands.PixyFindBall;
import frc.robot.commands.RunPinkTransport;
import frc.robot.commands.RunTransportWithSensor;
import frc.robot.commands.RunUpperTransport;
import frc.robot.commands.SetShooterVelocity;
import frc.robot.commands.ShooterShootEverything;
import frc.robot.commands.StopUpperTransport;
import frc.robot.commands.SwitchGears;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TransportGoBrr;
import frc.robot.commands.TransportandIntake;
import frc.robot.commands.WhateverYouWant;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import frc.robot.util.Limelight;
import frc.robot.util.Pixy2;
import frc.robot.util.Pixy2Obj;
import frc.robot.util.SPILink;
import frc.robot.commands.autocommands.AutoDrive;
import frc.robot.commands.autocommands.AutoIntakeGoSpin;
import frc.robot.commands.autocommands.AutoIntakeStop;
import frc.robot.commands.autocommands.AutoShooterShootEverything;
import frc.robot.commands.autocommands.AutoTurn;
import frc.robot.commands.autocommands.AutoTurnWithGyro;

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
  //private final Shooter shooter; we took out shooter before first competition
  private final Climber climber;
  private final Limelight limelight;
  private final Transport transport;
  private final Intake intake;
  private final Pixy2Obj pixy;
  private final Trigger trigger;
  private SendableChooser<Command> chooser;
  public int ballcount = 0;

  final JoystickButton j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12;
  final JoystickButton xA, xB, xX, xY, xRB, xLB, xSelect, xMenu, x9, x10;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    stick = new Joystick(0);
    xbox = new XboxController(1);
    drivetrain = new Drivetrain();
    //shooter = new Shooter(); we took out shooter before first competition
    climber = new Climber();
    limelight = new Limelight();
    pixy = new Pixy2Obj();
    transport = new Transport();
    intake = new Intake();
    chooser = new SendableChooser<>();

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
    trigger = new Trigger();

    setDefaultCommands();
    
    // Configure the button bindings
    configureButtonBindings();

  //  initializeAutoChooser();
    initializeAutoChooser();  
    
  }


  private void setDefaultCommands(){
	  drivetrain.setDefaultCommand(new TankDrive(() -> filter(stick.getY()*-0.5), () -> filter(stick.getTwist()*0.5), () -> stick.getRawButtonPressed(3), drivetrain, stick.getTrigger(), stick.getRawButton(2)));
    transport.setDefaultCommand(new NewDefaultIntakeAndTransport(transport, 0));
  }

  /**
   * 
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    if(trigger.getAsBoolean()){
      new RunPinkTransport(transport);
    }

    //j2.whenPressed(new SwitchGears(drivetrain));
    j2.whenPressed(new InstantCommand(drivetrain::switchGears, drivetrain));

    j1.whenPressed(new ShooterShootEverything(transport));
    //j1.whenPressed(new RunTransportWithSensor(transport));

    //xMenu.whenPressed(new WhateverYouWant(drivetrain, pixy));
    xMenu.whenPressed(new InstantCommand(climber::orangeValve, climber));
    xSelect.whenPressed(new InstantCommand(climber::hookValve, climber));
    //xA.whenPressed(new InstantCommand(climber::climberValve, climber));
    xB.whenHeld(new TransportandIntake(transport, intake));
    //xX.whenHeld(new TransportGoBrr(transport));
    xY.whenHeld(new IntakeGoSpin(intake));
    
    xX.whenHeld(new RunPinkTransport(transport));
    xA.whenHeld(new RunUpperTransport(transport));
    xA.whenReleased(new StopUpperTransport(transport));

    xRB.whenHeld(new IntakeDown(intake));
    xLB.whenHeld(new IntakeUp(intake));
  }
  
  public double filter(double value) {
    if(Math.abs(value) < .2) {
      value = 0;
    }
    return value;
    
  }

  public void initializeAutoChooser() {
    
      chooser.setDefaultOption(
		  	"nothing",
        new WaitCommand(10)
		  );

      chooser.addOption("DriveOutTarmac",
      new AutoDrive(0.5, 5, drivetrain)
      );

      chooser.addOption("DriveOutTarmacBackwards",
      new AutoDrive(0.5, -9, drivetrain)
      );

      chooser.addOption("FrontLeftTarmacPosition",
      new SequentialCommandGroup(
          new ParallelCommandGroup( 
            new AutoDrive(0.5, 5, drivetrain),
            new AutoIntakeGoSpin(intake)
          )
        ));


        chooser.addOption("FrontRightTarmacPosition",
        new SequentialCommandGroup(
            new ParallelCommandGroup( 
              new AutoDrive(0.5, 3, drivetrain),
              new AutoIntakeGoSpin(intake) 
            ),
            new WaitCommand(1),
            new AutoTurn(8.7, 0.5, drivetrain),
            new AutoIntakeStop(intake),
            new WaitCommand(1),
            new AutoDrive(0.5, 3, drivetrain),
            new WaitCommand(1),
            new AutoShooterShootEverything(transport),
            new AutoDrive(-0.5, 3, drivetrain)
          ));

          chooser.addOption("FrontRightTarmacPositionWithGyroTurn",
        new SequentialCommandGroup(
            new InstantCommand(() -> drivetrain.switchGears()),
            new ParallelCommandGroup( 
              new AutoDrive(0.5, 16, drivetrain),
              new AutoIntakeGoSpin(intake) 
            ),
            new WaitCommand(1),
            new AutoTurnWithGyro(120, 0.3, drivetrain),
            new AutoIntakeStop(intake),
            new WaitCommand(1),
            new AutoDrive(0.5, 16, drivetrain),
            new WaitCommand(1),
            new AutoShooterShootEverything(transport),
            new AutoDrive(-0.5, 16, drivetrain)
          ));

          chooser.addOption("FrontLeftTarmacPosition",
        new SequentialCommandGroup(
            new ParallelCommandGroup( 
              new AutoDrive(0.5, 3, drivetrain),
              new AutoIntakeGoSpin(intake) 
            ),
            new WaitCommand(1),
            new AutoTurn(-9, -0.5, drivetrain),
            new AutoIntakeStop(intake),
            new WaitCommand(1),
            new AutoDrive(0.5, 3, drivetrain),
            new WaitCommand(1),
            new AutoShooterShootEverything(transport),
            new AutoDrive(-0.5, 3, drivetrain)
          ));

      chooser.addOption("Spin Test", 
        new AutoTurn(5, 0.5, drivetrain)
      );

      chooser.addOption("Shoot Low and Back Up",
      new SequentialCommandGroup(
        new AutoShooterShootEverything(transport),
        new AutoDrive(-0.5, 3, drivetrain)
      ));

      chooser.addOption("IgnoreThis", 
      new SequentialCommandGroup(
        new AutoDrive(0.5, 3, drivetrain),
        new InstantCommand(() -> drivetrain.switchGears()),
        new AutoDrive(0.5, 3, drivetrain)
      )
      );

      chooser.addOption("Test", 
      new SequentialCommandGroup(
        new TankDrive(() -> .5, () -> 0, () -> false, drivetrain, false, false).withTimeout(2), 
        new TankDrive(() -> 0, () -> 0, () -> false, drivetrain, false, false).withTimeout(2),
        new IntakeGoSpin(intake).withTimeout(2)
        //new InstantCommand(() -> intake.setLowerMotor(.5)).withTimeout(5)
        )
      );

      // chooser.addOption(  
      // "BetterDriveOutTarmacBackwards",
      // new RunCommand(() -> drivetrain.arcadeDrive(0, -.4))
      // .withTimeout(2)
      // .andThen(() -> drivetrain.arcadeDrive(0, 0), drivetrain)
      // );



      SmartDashboard.putData(chooser);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
    // An ExampleCommand will run in autonomous
  }
}
