package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherDriveSubsystem extends SubsystemBase {

  private PWMSparkMax motorLeft;
  private PWMSparkMax motorRight;
  private final int motorLeftChannel = 0;
  private final int motorRightChannel = 1;

  public LauncherDriveSubsystem() {
    motorLeft = new PWMSparkMax(motorLeftChannel);
    motorRight = new PWMSparkMax(motorRightChannel);

    motorLeft.stopMotor();
    motorRight.stopMotor();
  }

  public void RunMotors() {
    motorLeft.set(0.5);
    motorRight.set(0.5);
  }

  public void StopMotors() {
    motorLeft.stopMotor();
    motorRight.stopMotor();
  }
}
