package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterDriveSubsystem extends SubsystemBase
{
    private PWMSparkMax shooterMotorBack;
    private PWMSparkMax shooterMotor;
    private PWMSparkMax rollerMotor;
    private PWMSparkMax inTakeLiftMotor;

    private final int shooterMotorBackChannel = 7;
    private final int shooterMotorChannel = 8;
    private final int rollerMotorChannel = 9;
    private final int inTakeLiftMotorChannel = 2;

    private double intakeSpeed = 1;
    private double shooterSpeed = 1;

    public ShooterDriveSubsystem()
    {
        shooterMotor = new PWMSparkMax(shooterMotorChannel);
        shooterMotorBack = new PWMSparkMax(shooterMotorBackChannel);
        rollerMotor = new PWMSparkMax(rollerMotorChannel);
        //inTakeLiftMotor = new PWMSparkMax(inTakeLiftMotorChannel);
    }
    
    public void RunIntake()
    {
        rollerMotor.set(intakeSpeed * 0.8);
    }

    public void RunIntakeOut()
    {
        rollerMotor.set(intakeSpeed * -1);
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
