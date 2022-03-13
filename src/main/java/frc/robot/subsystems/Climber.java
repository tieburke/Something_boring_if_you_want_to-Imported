// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {


  private final DoubleSolenoid climberFL;
  private final DoubleSolenoid climberFR;
  private final DoubleSolenoid climberBL;
  private final DoubleSolenoid climberBR;

  
  /** Creates a new Climber. */
  public Climber() {
    climberFR = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 0, 1); 
    climberBR = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 2, 3); 
    climberBL = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 4, 5);  
    climberFL = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 6, 7); 

    climberFR.set(DoubleSolenoid.Value.kForward);
    climberBR.set(DoubleSolenoid.Value.kForward);
    climberBL.set(DoubleSolenoid.Value.kReverse);
    climberFL.set(DoubleSolenoid.Value.kReverse);

  }

  public void climberValve(){
    climberFR.toggle();
		climberFL.toggle();
    climberBL.toggle();
    climberBR.toggle();
	}

  public void orangeValve(){
    climberBL.toggle();
    climberBR.toggle();
  }

  public void hookValve(){
    climberFR.toggle();
		climberFL.toggle();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
