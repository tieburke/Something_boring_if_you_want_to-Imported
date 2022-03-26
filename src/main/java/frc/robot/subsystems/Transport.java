// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {

  public final DigitalInput ballInput, pinkInput;
  public final WPI_TalonSRX pinkTransport;
  public final CANSparkMax topTransport;
  public final Timer timer;
  /** Creates a new Transport. */
  public Transport() {
    pinkTransport = new WPI_TalonSRX(Constants.PINK_TRANSPORT);
    topTransport = new CANSparkMax(Constants.TOP_TRANSPORT, MotorType.kBrushless);

    ballInput = new DigitalInput(0);
    pinkInput = new DigitalInput(1);

    pinkTransport.configFactoryDefault();
    pinkTransport.setNeutralMode(NeutralMode.Brake);

    topTransport.restoreFactoryDefaults();
    topTransport.setIdleMode(IdleMode.kBrake);

    topTransport.getPIDController().setP(Constants.kShooterP);
    topTransport.getPIDController().setI(Constants.kShooterI);
    topTransport.getPIDController().setFF(Constants.kShooterF);
    topTransport.getPIDController().setOutputRange(-1, 1);

    timer = new Timer();
  }

  public void setPinkTransport(double power){
    pinkTransport.set(power);
}

public void setTopTransport(double power){
  topTransport.set(power);
}


public void setTopTransportRPM(double rpm){
  topTransport.getPIDController().setReference(rpm, ControlType.kVelocity);
  
}

public void intake(double power){
  pinkTransport.set(power);
  topTransport.set(power);
}

public boolean getBallInput(){
  return(!ballInput.get());
}

public boolean getPinkInput(){
  return(!pinkInput.get());
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putBoolean("BallInput", getBallInput());
    SmartDashboard.putBoolean("PinkInput", getPinkInput());
    SmartDashboard.putNumber("Max RPM", topTransport.getPIDController().getOutputMax());
  }
}
