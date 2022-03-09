// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //CAN IDs
    public static final int FL_DRIVE_PORT = 1,
                            FR_DRIVE_PORT = 2,
                            ML_DRIVE_PORT = 3,
                            MR_DRIVE_PORT = 4,
                            BL_DRIVE_PORT = 5,
                            BR_DRIVE_PORT = 6,
                            INTAKE_MOTOR_PORT = 7,
                            PINK_TRANSPORT = 8,
                            TOP_TRANSPORT = 9,
                            SHOOTER_PORT = 10,
                            TURRET_MOTOR = 11;

    //Servo Ports
    public static final int SHOOTER_SERVO = 0;  
    
    public static final double kShooterP = 0.00008,
                               kShooterI = 0.0000001,
                               kShooterF = 0.000175;

}
