// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

///SUBSYSTEM IMPORTS///
import frc.robot.subsystems.ShooterDriveSubsystem;

/**xw
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  /// SUBSYSTEMS ///
  //Remmber these are members of the class meaning they should start with the m_ prefix and end with the Subsystem suffix
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ShooterDriveSubsystem m_ShooterDriveSubsystem = new ShooterDriveSubsystem();

  /// CONTROLLERS ///
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);




  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    // Configure the trigger bindings
    configureBindings();
  }



  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() 
  {
    /// BOUND COMMANDS ///
    /*
     *     Continuous Command
     *     m_driverController.y().onTrue(new ExampleCommand(args*));
     * 
     *     Single Input Command / Do not use a Command class
     *     m_driverController.y().onTrue(Commands.runOnce(() -> m_subsystemExample.exampleMethod(), m_subsystemExample));
     */



    //Run Intake Motor on (Y) push
    m_driverController.y().onTrue(Commands.runOnce(() -> m_ShooterDriveSubsystem.RunIntake(), m_ShooterDriveSubsystem));

    //Spit out of Intake Motor on (A) push
    m_driverController.a().onTrue(Commands.runOnce(() -> m_ShooterDriveSubsystem.RunIntakeOut(), m_ShooterDriveSubsystem));

    //Run Shooter Motor on (X) push
    m_driverController.x().onTrue(Commands.runOnce(() -> m_ShooterDriveSubsystem.RunShooterForward(), m_ShooterDriveSubsystem));

    //Stop all Motors on (B) push
    m_driverController.b().onTrue(Commands.runOnce(() -> m_ShooterDriveSubsystem.StopMotors(), m_ShooterDriveSubsystem));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
