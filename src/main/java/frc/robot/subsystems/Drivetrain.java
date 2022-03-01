// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
	/** Creates a new Drivetrain. */
  private final CANSparkMax frontLeft, frontRight, midLeft, midRight, backLeft, backRight;

	private final MotorControllerGroup right, left;

	private final DifferentialDrive drive;

	private final AHRS ahrs;
	private RelativeEncoder leftEncoder;
	private RelativeEncoder rightEncoder;

	double gyroOffset = 0;
	
  public Drivetrain() {
    frontLeft = new CANSparkMax(Constants.FL_DRIVE_PORT, MotorType.kBrushless);
	frontRight = new CANSparkMax(Constants.FR_DRIVE_PORT, MotorType.kBrushless);
	midLeft = new CANSparkMax(Constants.MR_DRIVE_PORT, MotorType.kBrushless);
    midRight = new CANSparkMax(Constants.ML_DRIVE_PORT, MotorType.kBrushless);
	backLeft = new CANSparkMax(Constants.BR_DRIVE_PORT, MotorType.kBrushless);
	backRight = new CANSparkMax(Constants.BR_DRIVE_PORT, MotorType.kBrushless);

    	frontLeft.restoreFactoryDefaults();
		frontRight.restoreFactoryDefaults();
		midLeft.restoreFactoryDefaults();
		midRight.restoreFactoryDefaults();
		backLeft.restoreFactoryDefaults();
		backRight.restoreFactoryDefaults();

		frontLeft.setIdleMode(IdleMode.kBrake);
		frontRight.setIdleMode(IdleMode.kBrake);
		midLeft.setIdleMode(IdleMode.kBrake);
		midRight.setIdleMode(IdleMode.kBrake);
		backLeft.setIdleMode(IdleMode.kBrake);
		backRight.setIdleMode(IdleMode.kBrake);
    
    	left = new MotorControllerGroup(frontLeft, midLeft, backLeft);
		right = new MotorControllerGroup(frontRight, midRight, backRight);

		drive = new DifferentialDrive(left, right);

		ahrs = new AHRS(SPI.Port.kMXP);

		resetEncoders();

		leftEncoder = frontLeft.getEncoder();
		rightEncoder = frontRight.getEncoder();

  }

  	public void arcadeDrive(double speed, double rotate, boolean squareInputs) {
		drive.arcadeDrive(speed, rotate, squareInputs);
	}
	public void arcadeDrive(double speed, double rotate) {
		drive.arcadeDrive(speed, rotate);
	}

	public void resetEncoders() {
		frontLeft.getEncoder().setPosition(0);
		frontRight.getEncoder().setPosition(0);
		midLeft.getEncoder().setPosition(0);
		midRight.getEncoder().setPosition(0);
		backLeft.getEncoder().setPosition(0);
		backRight.getEncoder().setPosition(0);
	}

	public double getEncoderLeft() {
		return frontLeft.getEncoder().getPosition() + backLeft.getEncoder().getPosition();
	}
	public double getEncoderRight() {
		return frontRight.getEncoder().getPosition() + backRight.getEncoder().getPosition();
	}

	public double getEncoderAverage() {
		return (getEncoderLeft() + -getEncoderRight()) / 2;
	}

	public double getAngle() {
		return ahrs.getAngle() - gyroOffset;
	}
	
	public void resetAngle() {
		gyroOffset = ahrs.getAngle();
	}

//This is a test ignore this

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
