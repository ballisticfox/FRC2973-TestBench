package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterDriveSubsystem extends SubsystemBase
{
    private PWMSparkMax shooterMotor;
    private PWMSparkMax rollerMotor;

    private final int shooterMotorChannel = 8;
    private final int rollerMotorChannel = 9;

    private double intakeSpeed = 1;
    private double shooterSpeed = 1;

    public ShooterDriveSubsystem()
    {
        shooterMotor = new PWMSparkMax(shooterMotorChannel);
        rollerMotor = new PWMSparkMax(rollerMotorChannel);
    }

    public void RunIntake()
    {
        rollerMotor.set(intakeSpeed);
        shooterMotor.set(shooterSpeed);
    }
    
    public void RunShooter()
    {
        shooterMotor.set(intakeSpeed);
    }

    public void StopMotors()
    {
        shooterMotor.stopMotor();
        rollerMotor.stopMotor();
    }

}
