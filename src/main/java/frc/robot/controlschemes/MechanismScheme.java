package frc.controlschemes;

import static edu.wpi.first.wpilibj2.command.Commands.parallel;
import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static edu.wpi.first.wpilibj2.command.Commands.sequence;
import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.helpers.ControlScheme;
import frc.robot.Constants;
import frc.robot.subsystems.Door;
import frc.robot.subsystems.DriveTrain;

public class MechanismScheme implements ControlScheme {
        private static CommandXboxController controller;
        public static BooleanSupplier inAmpPosition = () -> false;

       
        // public static CommandXboxController controller;

        public static void configure(Door door,
                        int port) {
                controller = new CommandXboxController(port);
                // controller = new CommandXboxController(3);
                configureButtons(port, door);
                // arm.setDefaultCommand(Commands.run(() -> arm.moveArm(OI.axis(1,
                // ControlMap.L_JOYSTICK_VERTICAL) * 0.5,
                // OI.axis(1, ControlMap.R_JOYSTICK_VERTICAL) * 0.5), arm));

                // intake.setDefaultCommand(Commands.run(() -> intake.spin(
                // OI.axis(1, ControlMap.LT), OI.axis(1, ControlMap.RT)), intake));

                // new JoystickButton(OI.joystickArray[1], ControlMap.A_BUTTON)
                // .onTrue(Commands.run(() -> intake.toggleReverse(true),
                // intake)).onFalse(Commands.run(() -> intake.toggleReverse(false), intake));\
        }

        public static void configureButtons(int port, Door door) {

                Command doorDown = (door.doorRun(Constants.MechanismPositions.DOOR_DOWN_POSITION));
                Command doorUp  = (door.doorRun(Constants.MechanismPositions.DOOR_UP_POSITION));
                

                // Command targetSubWooferShoot =
                // sequence(wrist.wristToSetpoint(Constants.MechanismPositions.WRIST_SHOOT),
                // autoShoot).withName("Subwoofer Shoot");

                controller.rightBumper().onTrue(doorDown);
                controller.leftBumper().onTrue(doorUp);

                
        }

        public static void periodic() {
                

        }

       
}