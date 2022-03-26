package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoTurnWithGyro extends CommandBase {

    private final Drivetrain drivetrain;
    private final double PPI = 10.63;

    private double power, angle, startAngle, leftEncoder, rightEncoder;

    public AutoTurnWithGyro(double angle, double power, Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        this.angle = angle;
        this.power = power;
        startAngle = drivetrain.getAngle();
    }

    @Override
    public void initialize() {
        drivetrain.resetAngle();
        startAngle = drivetrain.getAngle();
        leftEncoder =  drivetrain.getEncoderLeft();
        rightEncoder = drivetrain.getEncoderRight();
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(angle, power, false);
        if(angle < 0){
            power = -power;
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0, false);
    }

    @Override
    public boolean isFinished() {
        if(drivetrain.getAngle() >= angle && angle > 0) {
             return true;
        }
        if(drivetrain.getAngle() <= angle && angle < 0) {
            return true;
        }

        //if(drivetrain.getEncoderLeft() >= leftEncoder + angle && power > 0) {
        //    return true;
        //}
        // if(drivetrain.getEncoderLeft() <= leftEncoder - angle && power < 0) {
        //    return true;
        // }
        return false;
    }
}