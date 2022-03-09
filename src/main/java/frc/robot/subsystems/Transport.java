// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {

  public final WPI_TalonSRX pinkTransport;
  public final CANSparkMax topTransport;
  /** Creates a new Transport. */
  public Transport() {
    pinkTransport = new WPI_TalonSRX(Constants.PINK_TRANSPORT);
    topTransport = new CANSparkMax(Constants.TOP_TRANSPORT, MotorType.kBrushless);

    pinkTransport.configFactoryDefault();
    pinkTransport.setNeutralMode(NeutralMode.Brake);

    topTransport.restoreFactoryDefaults();
    topTransport.setIdleMode(IdleMode.kBrake);
  }

  public void setPinkTransport(double power){
    pinkTransport.set(power);
}
public void setTopTransport(double power){
  topTransport.set(power);
}

public void intake(double power){
  pinkTransport.set(power);
  topTransport.set(power);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
