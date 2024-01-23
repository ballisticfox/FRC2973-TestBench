package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightCameraSubsystem extends SubsystemBase {
  private final NetworkTable m_limelightTable =
      NetworkTableInstance.getDefault().getTable("limelight");

  private final NetworkTableEntry m_tx = m_limelightTable.getEntry("tx");
  private final NetworkTableEntry m_ty = m_limelightTable.getEntry("ty");
  private final NetworkTableEntry m_ta = m_limelightTable.getEntry("ta");
  private final NetworkTableEntry m_tv = m_limelightTable.getEntry("tv");

  public static final double HEIGHT_OF_TARGET_INCHES = 32;
  public static final double HEIGHT_OF_CAMERA_INCHES = 16;
  public static final double ANGLE_BETWEEN_LEVEL_PLANE_AND_CAMERA = 0;

  public double getXOffset() {
    return m_tx.getDouble(0.0);
  }

  public double getYOffset() {
    return m_ty.getDouble(0.0);
  }

  public double getVisualArea() {
    return m_ta.getDouble(0.0);
  }

  public boolean isTargetVisible() {
    return m_tv.getDouble(0) == 1;
  }
}
