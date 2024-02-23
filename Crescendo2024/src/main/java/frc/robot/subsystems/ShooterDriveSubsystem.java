package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterDriveSubsystem extends SubsystemBase
{
    private PWMSparkMax shooterMotorBack;
    private PWMSparkMax shooterMotor;
    private PWMSparkMax rollerMotor;

    private final int shooterMotorBackChannel = 7;
    private final int shooterMotorChannel = 8;
    private final int rollerMotorChannel = 9;

    private double intakeSpeed = 1;
    private double shooterSpeed = 1;

    public ShooterDriveSubsystem()
    {
        shooterMotor = new PWMSparkMax(shooterMotorChannel);
        shooterMotorBack = new PWMSparkMax(shooterMotorBackChannel);
        rollerMotor = new PWMSparkMax(rollerMotorChannel);
    }

    public void RunIntake()
    {
        rollerMotor.set(intakeSpeed);
        shooterMotor.set(shooterSpeed);
        shooterMotorBack.set(shooterSpeed);
    }
    
    public void RunShooter()
    {
        shooterMotor.set(-1 * intakeSpeed);
        shooterMotorBack.set(-1 * shooterSpeed);
    }

    public void StopMotors()
    {
        shooterMotorBack.stopMotor();
        shooterMotor.stopMotor();
        rollerMotor.stopMotor();
    }

}
