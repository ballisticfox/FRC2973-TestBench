package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {
  private MecanumDrive m_robotDrive;

  public MecanumDriveSubsystem() {

    /// DATA CHANNELS///
    final int frontLeftChannel = 0;
    final int rearLeftChannel = 1;
    final int frontRightChannel = 2;
    final int rearRightChannel = 3;

    /// INVERTED MOTORS///
    final boolean invertFrontLeft = false;
    final boolean invertRearLeft = false;
    final boolean invertFrontRight = false;
    final boolean invertRearRight = false;

    /// MOTOR SETUP
    PWMSparkMax frontLeft = new PWMSparkMax(frontLeftChannel);
    PWMSparkMax rearLeft = new PWMSparkMax(rearLeftChannel);
    PWMSparkMax frontRight = new PWMSparkMax(frontRightChannel);
    PWMSparkMax rearRight = new PWMSparkMax(rearRightChannel);

    frontLeft.setInverted(invertFrontLeft);
    rearLeft.setInverted(invertRearLeft);
    frontRight.setInverted(invertFrontRight);
    rearRight.setInverted(invertRearRight);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
  }

  public void driveCartesian(double yAxis, double xAxis, double rzAxis) {
    m_robotDrive.driveCartesian(-yAxis, xAxis, rzAxis);
  }
}
