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

    public void RunIntake20()
    {
        rollerMotor.set(intakeSpeed * 0.2);
    }

    public void RunIntake40()
    {
        rollerMotor.set(intakeSpeed * 0.4);
    }

    public void RunIntake60()
    {
        rollerMotor.set(intakeSpeed * 0.6);
    }
    
    public void RunIntake80()
    {
        rollerMotor.set(intakeSpeed * 0.8);
    }

    public void RunShooterForward()
    {
        shooterMotor.set(-1 * intakeSpeed);
        shooterMotorBack.set(-1 * shooterSpeed);
    }

    public void RunShooterBackward()
    {
        shooterMotor.set(intakeSpeed);
        shooterMotorBack.set(shooterSpeed);
    }

    public void StopMotors()
    {
        shooterMotorBack.stopMotor();
        shooterMotor.stopMotor();
        rollerMotor.stopMotor();
    }

}
