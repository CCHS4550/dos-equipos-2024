package frc.robot.controlschemes;

import static edu.wpi.first.wpilibj2.command.Commands.parallel;
import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static edu.wpi.first.wpilibj2.command.Commands.sequence;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.helpers.ControlScheme;
import frc.helpers.OI;
import frc.maps.ControlMap;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Door;

/**
 * Control scheme for swerve drive. Includes movement, the toggle between
 * field centric and robot centric, and a button to zero the gyro.
 */
public class DriveScheme implements ControlScheme {
    private static CommandXboxController controller;
   
    public static void configure(DriveTrain driveTrain, int port) {
       
        controller = new CommandXboxController(port);

       
        configureButtons(driveTrain, port);
    }

    /**
     * Configures buttons and their respective commands.
     * 
     * @param swerveDrive The SwerveDrive object being configured.
     * @param port        The controller port of the driving controller.
     */
    private static void configureButtons(DriveTrain driveTrain,  int port) {
  

        driveTrain.setDefaultCommand(
            new RunCommand(()-> driveTrain.drive(controller.getLeftY(), controller.getRightX()), driveTrain));
    
    
        // // controller.b().onTrue(runOnce(() -> toggleFieldCentric()));
        // controller.a().onTrue(runOnce(() -> swerveDrive.zeroHeading()));
        // controller.y().onTrue(sequence(swerveDrive.generatePathFindToPose(swerveDrive.getNearestSpeakerPose()),
        //         runOnce(() -> OI.setRumble(0, 0.5))));

        // // controller.b().onTrue((sequence(swerveDrive.generatePathFindToPose(new
        // // Pose2d(0, 0, new Rotation2d(0))),
        // // runOnce(() -> OI.setRumble(0, 0.5)))));

        // controller.x().onTrue(runOnce(() -> toggleOrientationLock(swerveDrive)))
        //         .onFalse(runOnce(() -> toggleOrientationLock(swerveDrive)));

        // controller.rightBumper().whileTrue(parallel(shooter.shoot(() -> 0.2), indexer.index(() -> 0.3)));
        // // controller.leftBumper().onTrue(swerveDrive.generatePathFindToPose(swerveDrive.getAmpPose()));


        // controller.leftTrigger().onTrue(runOnce(() -> setFastMode())).onFalse(runOnce(() -> setNormalMode()));
        // controller.rightTrigger().onTrue(runOnce(() -> setSlowMode())).onFalse(runOnce(() -> setNormalMode()));

        

    }

    

    /**
     * Toggle field centric and robot centric driving.
     */
   
}