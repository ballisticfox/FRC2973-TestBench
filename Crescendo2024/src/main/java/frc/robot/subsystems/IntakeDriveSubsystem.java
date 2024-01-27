package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeDriveSubsystem extends SubsystemBase
{

    private PWMSparkMax motor;
    private final int motorChannel = 9;
    


    public IntakeDriveSubsystem()
    {
        motor = new PWMSparkMax(motorChannel);

        motor.stopMotor();
    }

    public void RunMotors()
    {
        motor.set(0.5);
    }

    public void StopMotors()
    {
        motor.stopMotor();
    }
}