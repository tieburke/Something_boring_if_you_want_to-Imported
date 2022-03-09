// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends SubsystemBase {

  public final WPI_TalonSRX turret;
  public final CANSparkMax shooter;
  public final Servo shooterServo;
  /** Creates a new Shooter. */
  public Shooter() {
    shooter = new CANSparkMax(Constants.SHOOTER_PORT, MotorType.kBrushless);
    turret = new WPI_TalonSRX(Constants.TURRET_MOTOR);
    shooterServo = new Servo(Constants.SHOOTER_SERVO);

    shooter.restoreFactoryDefaults();
    shooter.setIdleMode(IdleMode.kBrake);

    turret.configFactoryDefault();
    turret.setNeutralMode(NeutralMode.Brake);

    shooter.getPIDController().setP(Constants.kShooterP);
    shooter.getPIDController().setI(Constants.kShooterI);
    shooter.getPIDController().setFF(Constants.kShooterF);
    shooter.getPIDController().setOutputRange(-1, 1);
  }

  public void setShooterPower(double power){
    shooter.set(power);
}
  
  public void setShooterRPM(double rpm){
    shooter.getPIDController().setReference(rpm, ControlType.kVelocity);
  }

public void setTurret(double power){
  turret.set(power);
}

public void shooterServoSet(double angle){
  shooterServo.setAngle(angle);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
