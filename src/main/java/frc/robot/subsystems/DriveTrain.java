package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.revrobotics.CANSparkBase.IdleMode; //idk why these are showing up as errors
import com.revrobotics.CANSparkLowLevel.MotorType; //idk why this is showing up as an error
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    
    CCSparkMax frontLeft = new CCSparkMax ("front left", "fl", Constants.MotorConstants.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_LEFT_REVERSE);
    CCSparkMax frontRight = new CCSparkMax ("front right", "fr", Constants.MotorConstants.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_RIGHT_REVERSE);
    CCSparkMax backLeft = new CCSparkMax ("front left", "bl", Constants.MotorConstants.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_LEFT_REVERSE);
    CCSparkMax backRight = new CCSparkMax ("front left", "br", Constants.MotorConstants.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_RIGHT_REVERSE);
    // Spark frontLeft  = new Spark(Constants.MotorConstants.FRONT_LEFT);
    // Spark frontRight = new Spark(Constants.MotorConstants.FRONT_RIGHT);
    // Spark backLeft = new Spark (Constants.MotorConstants.BACK_LEFT);
    // Spark backRight = new Spark (Constants.MotorConstants.BACK_RIGHT);
    DifferentialDrive frontDifferentialDrive = new DifferentialDrive (frontLeft, frontRight);
    DifferentialDrive backDifferentialDrive = new DifferentialDrive(backLeft, backRight);
    public void difDrive (double moveSpeed, double turnSpeed){
        frontDifferentialDrive.arcadeDrive(moveSpeed, turnSpeed);
        backDifferentialDrive.arcadeDrive(moveSpeed, turnSpeed);
    }
    public Command drive (double moveSpeed, double turnSpeed){
        return this.run (()-> difDrive(moveSpeed, turnSpeed));
    }



}
