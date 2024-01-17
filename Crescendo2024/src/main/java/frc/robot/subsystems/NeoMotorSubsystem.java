package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoMotorSubsystem extends SubsystemBase {

  // Constants
  private static final int DEVICE_ID_MOTOR1 = 4; // Set the device ID for the first motor
  private static final int DEVICE_ID_MOTOR2 = 7; // Set the device ID for the second motor

  // Motor Controllers
  private final CANSparkMax motor1;
  private final CANSparkMax motor2;

  // Encoders
  private final RelativeEncoder encoder1;
  private final RelativeEncoder encoder2;

  // PID Controllers
  private final SparkPIDController pidController1;
  private final SparkPIDController pidController2;

  // Constructor
  public NeoMotorSubsystem() {
    // Initialize motor controllers
    motor1 = new CANSparkMax(DEVICE_ID_MOTOR1, MotorType.kBrushless);
    motor2 = new CANSparkMax(DEVICE_ID_MOTOR2, MotorType.kBrushless);

    // Get encoders
    encoder1 = motor1.getEncoder();
    encoder2 = motor2.getEncoder();

    // Get PID Controllers from Spark Max
    pidController1 = motor1.getPIDController();
    pidController2 = motor2.getPIDController();

    // PID Coefficients and control settings
    // These values will need to be tuned for your specific robot
    pidController1.setP(0.1);
    pidController1.setI(0);
    pidController1.setD(0);
    pidController1.setIZone(0);
    pidController1.setFF(0);
    pidController1.setOutputRange(-1, 1);

    pidController2.setP(0.1);
    pidController2.setI(0);
    pidController2.setD(0);
    pidController2.setIZone(0);
    pidController2.setFF(0);
    pidController2.setOutputRange(-1, 1);
  }

  // Method to set motor speed
  public void setMotorSpeed(double speed) {
    motor1.set(speed);
    motor2.set(speed);
  }

  // Method to set motors to a specific RPM
  public void setMotorRPM(double rpm) {
    pidController1.setReference(rpm, CANSparkMax.ControlType.kVelocity);
    pidController2.setReference(rpm, CANSparkMax.ControlType.kVelocity);
  }

  // Example usage to set motors to 300 RPM
  public void setMotorsTo300RPM() {
    setMotorRPM(300);
  }

  // Method to stop all motors
  public void stopMotors() {
    motor1.set(0);
    motor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // You can add code here that needs to be run periodically
  }
}
