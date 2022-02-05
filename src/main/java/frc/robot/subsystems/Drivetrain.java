// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final CANSparkMax frontLeft, frontRight, backLeft, backRight;
  MotorControllerGroup left;
private final MotorControllerGroup right;
	private final DifferentialDrive drive;


  public Drivetrain() {
    frontLeft = new CANSparkMax(Constants.FL_DRIVE_PORT, MotorType.kBrushless);
		frontRight = new CANSparkMax(Constants.FR_DRIVE_PORT, MotorType.kBrushless);
		backLeft = new CANSparkMax(Constants.BL_DRIVE_PORT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BR_DRIVE_PORT, MotorType.kBrushless);

    frontLeft.restoreFactoryDefaults();
		frontRight.restoreFactoryDefaults();
		backLeft.restoreFactoryDefaults();
		backRight.restoreFactoryDefaults();

		frontLeft.setIdleMode(IdleMode.kBrake);
		frontRight.setIdleMode(IdleMode.kBrake);
		backLeft.setIdleMode(IdleMode.kBrake);
		backRight.setIdleMode(IdleMode.kBrake);
    
    left = new MotorControllerGroup(frontLeft, backLeft);
		right = new MotorControllerGroup(frontRight, backRight);

		drive = new DifferentialDrive(left, right);

  }

  public void arcadeDrive(double speed, double rotate, boolean squareInputs) {
		drive.arcadeDrive(speed, rotate, squareInputs);
	}
	public void arcadeDrive(double speed, double rotate) {
		drive.arcadeDrive(speed, rotate);
	}

//This is a test ignore this

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
