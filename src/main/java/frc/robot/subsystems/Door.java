import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

//public class Door extends SubsystemBase{
  //  CCSparkMax door = new CCSparkMax ("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);

//}


public class Door extends SubsystemBase {
    private final CCSparkMax door;
    private final PIDController pidController;

    // Encoder target values for open and closed positions
    private static final double DOOR_CLOSED_POSITION = 0.0;
    private static final double DOOR_OPEN_POSITION = 120.0;

    private double targetPosition = DOOR_CLOSED_POSITION;

    public Door() {
        // Initialize motor and PID controller
        door = new CCSparkMax("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
        pidController = new PIDController(0.1, 0, 0); // Set PID, adjust as needed
        pidController.setTolerance(1.0); // Set tolerance
    }

    public void openDoor() {
        targetPosition = DOOR_OPEN_POSITION;
        moveToPosition(targetPosition);
    }

    public void closeDoor() {
        targetPosition = DOOR_CLOSED_POSITION;
        moveToPosition(targetPosition);
    }

    private void moveToPosition(double targetPosition) {
        // Calculate motor power based on target position and current position
        double power = pidController.calculate(door.getPosition(), targetPosition);
        door.set(power);
    }

    public boolean isAtTargetPosition() {
        return pidController.atSetpoint();
    }

    public void stop() {
        door.stopMotor();
    }

    public Command open(){
        return this.run(()-> openDoor(), this);
    }

    public Commmand close(){
        return this.run(()-> closeDoor(), this);
    }

    @Override
    public void periodic() {
        // Periodically update motor power while moving to target position
        moveToPosition(targetPosition);
    }
}
