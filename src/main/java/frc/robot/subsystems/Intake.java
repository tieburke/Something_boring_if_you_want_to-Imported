package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    public final WPI_TalonSRX intakeMotor;
    public final WPI_TalonSRX lowerMotor;

public Intake() {

    intakeMotor = new WPI_TalonSRX(Constants.INTAKE_MOTOR_PORT);
    lowerMotor = new WPI_TalonSRX(Constants.LOWER_INTAKE_MOTOR_PORT);

    intakeMotor.configFactoryDefault();
    intakeMotor.setNeutralMode(NeutralMode.Brake);
}

    public void setIntakeMotor(double power){
        intakeMotor.set(power);
    }

    public void setLowerMotor(double power){
        lowerMotor.set(power);
    }

    public double getLowerMotorEncoder(){
        return lowerMotor.getSelectedSensorPosition();
    }
   
}