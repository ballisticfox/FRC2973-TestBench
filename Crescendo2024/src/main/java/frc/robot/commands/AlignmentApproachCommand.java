package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightCameraSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

public class AlignmentApproachCommand extends Command 
{

  private final TankDriveSubsystem m_driveSubsystem;
  private final LimelightCameraSubsystem m_cameraSubsystem;

  private final Timer m_timer = new Timer();
  private static final double MAX_RUN_TIME_SECONDS = 5;


  //TODO: Refactor the way the step size is created, otherwise you end up with Zeno's Paradox
  private static final double STEP_SIZE = 0.1;
  private static final double FINAL_TARGET_DISTANCE_INCHES = 12;
  private static final double APPROACH_SPEED = 0.5;

  public AlignmentApproachCommand(
      TankDriveSubsystem driveSubsystem, LimelightCameraSubsystem cameraSubsystem) {
    m_driveSubsystem = driveSubsystem;
    m_cameraSubsystem = cameraSubsystem;

    addRequirements(m_driveSubsystem, m_cameraSubsystem);
  }

  @Override
  public void initialize() 
  {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() 
  {
    if (m_cameraSubsystem.isTargetVisible()) 
    {
      double xOffset = m_cameraSubsystem.getXOffset();
      double yOffset = m_cameraSubsystem.getYOffset();

      //TODO: Write zRotation method for limelight camera subsystem
      double tagZRotation = 0.0;

      double distance = calculateDistance(yOffset);

      if (distance > FINAL_TARGET_DISTANCE_INCHES)
      {
        double tagXPosition = distance * Math.cos(xOffset);
        double tagYPosition = distance * Math.sin(yOffset);



        double interceptDistance = 0;

        //If the tag's rotation ends up being 0 then we'll get a div 0 error.
        //Luckily, we should just be able to set the intercept distance to the xPosition of the aprilTag in this case
        try
        {
          interceptDistance = ( tagXPosition * Math.tan(tagZRotation)-tagYPosition) / ( -1 * Math.tan(tagZRotation) );
        }
        catch (Exception e)
        {
          interceptDistance = tagXPosition;
        }

        double trajectoryPointX = ((-2 * STEP_SIZE * interceptDistance) + (2 * interceptDistance) + (STEP_SIZE*tagXPosition)) * STEP_SIZE;
        double trajectoryPointY = (STEP_SIZE*STEP_SIZE * tagYPosition);

        double angleOfCorrection = Math.atan(trajectoryPointY/trajectoryPointX);

        //TODO: Some sort of method to convert the angle of correction and speed to useable motor inputs

        // m_driveSubsystem.driveArcade(alignmentSpeed, alignmentAdjustment);
      }

    }
  }

  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(MAX_RUN_TIME_SECONDS)
        || (calculateDistance(m_cameraSubsystem.getYOffset()) <= FINAL_TARGET_DISTANCE_INCHES);
  }

  @Override
  public void end(boolean interruped) {
    m_driveSubsystem.driveArcade(0.0, 0.0);
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
