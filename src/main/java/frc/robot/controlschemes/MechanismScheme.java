package frc.robot.controlschemes;

import static edu.wpi.first.wpilibj2.command.Commands.parallel;
import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static edu.wpi.first.wpilibj2.command.Commands.sequence;
import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.helpers.ControlScheme;
import frc.robot.Constants;
import frc.robot.subsystems.Door;
import frc.robot.subsystems.DriveTrain;

public class MechanismScheme implements ControlScheme {
        private static CommandXboxController controller;
        

       
        // public static CommandXboxController controller;

        public static void configure(Door door,
                        int port) {
                controller = new CommandXboxController(port);
                // controller = new CommandXboxController(3);
                configureButtons(port, door);

                Command doorRunCommand = door.doorManual(controller.getRightY());
    door.setDefaultCommand(doorRunCommand);
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

                
                
                

                // Command targetSubWooferShoot =
                // sequence(wrist.wristToSetpoint(Constants.MechanismPositions.WRIST_SHOOT),
                // autoShoot).withName("Subwoofer Shoot");

                if (door.isUp()){
                    controller.a().onTrue(door.doorDown());
                }
                else
                {             
                    controller.a().onTrue(door.doorUp());
                }

                controller.rightTrigger().onTrue(door.halt());

                controller.rightBumper().whileTrue(door.manualUp());
                controller.leftBumper().whileTrue(door.manualDown());
                
        }
        // private static Command doorManual(double doorSpeed) {
        //         //         TODO Auto-generated method stub
        //                 throw new UnsupportedOperationException("Unimplemented method 'doorManual'");
        //              }

        public static void periodic() {
                

        }

       
}