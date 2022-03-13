package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDrive extends CommandBase {

    private final Drivetrain drivetrain;

    private final double power, distance, startEncoder;

    private final double PPI = 10.63;

    public AutoDrive(double power, double distance, Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        this.power = power;
        this.distance = distance;
        startEncoder = drivetrain.getEncoderAverage();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(0, power);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        if(drivetrain.getEncoderAverage() >= startEncoder + distance*this.PPI && power > 0) {
            return true;
        }
        if(drivetrain.getEncoderAverage() <= startEncoder - distance*this.PPI && power < 0) {
            return true;
        }
        return false;
    }
}
