package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightCameraSubsystem;
import frc.robot.subsystems.MecanumDriveSubsystem;

public class AlignmentApproachCommand extends Command {
  private final MecanumDriveSubsystem m_driveSubsystem;
  private final LimelightCameraSubsystem m_cameraSubsystem;
  private final Timer m_timer = new Timer();
  private static final double MAX_RUN_TIME_SECONDS = 5;
  private static final double FINAL_TARGET_DISTANCE_INCHES = 12;
  private static final double ALIGNMENT_TOLERANCE_DEGREES = 3;
  private static final double APPROACH_SPEED = 0.5;

  public AlignmentApproachCommand(
      MecanumDriveSubsystem driveSubsystem, LimelightCameraSubsystem cameraSubsystem) {
    m_driveSubsystem = driveSubsystem;
    m_cameraSubsystem = cameraSubsystem;

    addRequirements(m_driveSubsystem, m_cameraSubsystem);
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() {
    if (m_cameraSubsystem.isTargetVisible()) {
      double xOffset = m_cameraSubsystem.getXOffset();
      double yOffset = m_cameraSubsystem.getYOffset();
      double distance = calculateDistance(yOffset);

      double alignmentAdjustment = 0.0;
      if (Math.abs(xOffset) > ALIGNMENT_TOLERANCE_DEGREES) {
        alignmentAdjustment = xOffset / 100;
      }

      double alignmentSpeed = 0.0;
      if (distance > FINAL_TARGET_DISTANCE_INCHES) {
        alignmentSpeed = APPROACH_SPEED;
      }

      m_driveSubsystem.driveCartesian(alignmentSpeed, 0.0, alignmentAdjustment);
    }
  }

  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(MAX_RUN_TIME_SECONDS)
        || (calculateDistance(m_cameraSubsystem.getYOffset()) <= FINAL_TARGET_DISTANCE_INCHES);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.driveCartesian(0.0, 0.0, 0.0);
    m_timer.stop();
  }

  // TODO: re-factor these hard-coded values to a more logical place
  private double calculateDistance(double yOffset) {
    return (LimelightCameraSubsystem.HEIGHT_OF_TARGET_INCHES
            - LimelightCameraSubsystem.HEIGHT_OF_CAMERA_INCHES)
        / Math.tan(
            Math.toRadians(
                LimelightCameraSubsystem.ANGLE_BETWEEN_LEVEL_PLANE_AND_CAMERA + yOffset));
  }
}
