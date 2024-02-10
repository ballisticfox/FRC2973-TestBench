package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDBlinkinSubsystem extends SubsystemBase
{

    private MecanumDrive m_robotDrive;

    public LEDBlinkinSubsystem()
    {

        ///DATA CHANNELS///
        final int ledChannel  = 9;


        ///MOTOR SETUP
        PWMSparkMax ledStrip  = new PWMSparkMax(ledChannel);
        

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void driveCartesian(double yAxis, double xAxis, double rzAxis)
    {
        m_robotDrive.driveCartesian(-yAxis, xAxis, rzAxis);
    }
}
