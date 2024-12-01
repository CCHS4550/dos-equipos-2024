package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Door extends SubsystemBase {
    public CCSparkMax  door = new CCSparkMax("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
    private  PIDController pidController;
    private boolean isUp;

    // Encoder target values for open and closed positions
    

    public Door() {
        // Initialize motor and PID controller
        isUp = true;
        pidController = new PIDController(0.6, 0, 0); // Set PID , adjust as needed
        pidController.setTolerance(1.0); //tolerance
    }

    public void openDoor() {
        moveToPosition(Constants.MechanismPositions.DOOR_UP_POSITION);
    }

    public void closeDoor() {
        moveToPosition(Constants.MechanismPositions.DOOR_DOWN_POSITION);
    }

    public void moveToPosition(double targetPosition) {
        // Calculate motor power based on target position and current position
        
        door.set(pidSet(targetPosition));

        // Stop the motor when it's close enough to the target
        if (pidController.atSetpoint()) {
            door.set(0.0);
        }
    }

    public boolean atGoal(double goal){
        if (pidController.atSetpoint()||(door.getPosition()>goal-0.25&&door.getPosition()<goal+0.25)){
        // if (door.getPosition()>goal-0.25 && door.getPosition()<goal+0.25){
            return true;
        }
        else
            return false;
    }
    public double pidSet(double targetPosition){
        double power = pidController.calculate(door.getPosition(), targetPosition);
        return power;
    }

    public void manualDoorUp(){
        door.set(0.6); //maybe this is negative idk
    }
    public void manualDoorDown(){
        door.set(-0.6); //maybe this is position idk
    }

    public void manualDoor(double speed){
        door.set(speed*0.65);
    }

    public Command doorUp(){
        isUp = true;
        //added the until because maybe the thing is not running for as long
        return this.run(()->openDoor()).until(()-> atGoal(Constants.MechanismPositions.DOOR_DOWN_POSITION));
        // return this.run(()-> openDoor());
    }
    public Command doorDown(){
        isUp = false;
        return this.run(()->closeDoor()).until(()-> atGoal(Constants.MechanismPositions.DOOR_UP_POSITION));
        // return this.run(()->closeDoor());
    }

    public Command manualUp(){
        return this.run(()-> manualDoorUp());
    }

    public Command manualDownForTime(double time){
        return this.run(()-> manualDoorDown()).withTimeout(time);
    }


    public Command doorManual(double speed){
        return this.run(()->manualDoor(speed));
    }

    public Command manualDown(){
        return this.run(()-> manualDoorDown());
    }

    public boolean isUp(){
        return isUp;
    }

    public Command halt (){
        return this.runOnce(()->{});
    }

    @Override
    public void periodic() {
        // Periodically called to update motor power while moving to target position
    }
}