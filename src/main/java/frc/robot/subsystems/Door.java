import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode; //idk why these are showing up as errors
import com.revrobotics.CANSparkLowLevel.MotorType; //idk why this is showing up as an error
public class Door extends SubsystemBase{
    CCSparkMax door = new CCSparkMax ("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
    PIDController doorPIDController = new PIDController (1.0, 0, 0); //random values, tune these
    // public double doorPIDcalculate (double target){
    //     return doorPIDController.calculate(door.getPosition(), target);
    // }
    public void runDoor(double target){ //probably make targets the closed and open encoder positions
        door.set(doorPIDController.calculate(door.getPosition(), target));
    }
    public Command doorRun (double target){
        this.run(()-> runDoor(target));
    }

}
