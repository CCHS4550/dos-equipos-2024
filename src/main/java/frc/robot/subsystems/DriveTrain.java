package frc.robot.subsystems;
 

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    
    CCSparkMax frontLeft = new CCSparkMax ("front left", "fl", Constants.MotorConstants.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_LEFT_REVERSE);
    CCSparkMax frontRight = new CCSparkMax ("front right", "fr", Constants.MotorConstants.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_RIGHT_REVERSE);
    CCSparkMax backLeft = new CCSparkMax ("back left", "bl", Constants.MotorConstants.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_LEFT_REVERSE);
    CCSparkMax backRight = new CCSparkMax ("back right", "br", Constants.MotorConstants.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_RIGHT_REVERSE);
    public  DifferentialDrive diffDrive = new DifferentialDrive(
        (double output) -> {
            frontLeft.set(output);
            backLeft.set(output);
            },
        (double output) -> {
            frontRight.set(output);
            backRight.set(output);
        });
    
    public void difDrive(double moveSpeed, double turnSpeed){
        diffDrive.arcadeDrive(moveSpeed, turnSpeed);
     }
   
    public Command drive (double moveSpeed, double turnSpeed){
        return this.run (()-> difDrive(moveSpeed, turnSpeed));
    }

    public Command driveForTime(double moveSpeed, double turnSpeed, double time){
        return this.run(()-> difDrive(moveSpeed,turnSpeed)).withTimeout(time);
    }
}

