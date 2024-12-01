// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;

import static edu.wpi.first.wpilibj2.command.Commands.run;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Door;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command forwardAndDrop(DriveTrain driveTrain, Door door) {
    // return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
    return Commands.sequence(driveTrain.driveForTime(-1.0, 0.0, 5.0), door.doorDown() );
    // return Commands.sequence(driveTrain.driveForTime(1.0, 0.0, 5.0), door.manualDownForTime(2));
  }

  public static Command forward(DriveTrain driveTrain, Door door){
    return Commands.sequence(driveTrain.driveForTime(1.0,0.0, 5.0));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
