package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoMotorSubsystem extends SubsystemBase {

  // Constants
  private static final int DEVICE_ID_MOTOR2 = 7; // Set the device ID for the second motor

  // Motor Controllers
  private final CANSparkMax m_motor;

  // Encoders
  private final RelativeEncoder m_encoder;

  // PID Controllers
  private final SparkPIDController m_pidController;

  // PID and Motor parameters
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, setPoint;

  // Constructor
  public NeoMotorSubsystem() {
    // Initialize motor controller and restore Factory Defaults
    m_motor = new CANSparkMax(DEVICE_ID_MOTOR2, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();

    // Get PID Controller from Spark Max
    m_pidController = m_motor.getPIDController();

    // Get encoder
    m_encoder = m_motor.getEncoder();

    // PID coefficients
    kP = 6e-5;
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = 0.000015;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 100;

    // Set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // Display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
  }

  // Method to set motor speed
  public void setMotorSpeed(double speed) {
    m_motor.set(speed);
  }

  // Method to set motors to a specific RPM
  public void setMotorRPM(double rpm) {
    setPoint = rpm;
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
  }

  // Example usage to set motors to 300 RPM
  public void setMotorTo300RPM() {
    setMotorRPM(300);
  }

  public void manualMotorControl(double stickInput) {
    // 5% range for dead stick
    double percentageOfFullRPM = stickInput;
    if (stickInput >= -0.05 && stickInput <= 0.05) {
      percentageOfFullRPM = 0;
    }
    setMotorRPM(percentageOfFullRPM * maxRPM);
  }

  // Method to stop all motors
  public void stopMotors() {
    m_motor.set(0);
  }

  @Override
  public void periodic() {
    // Read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // If PID coefficients on SmartDashboard have changed, write new values to controller
    if ((p != kP)) {
      m_pidController.setP(p);
      kP = p;
    }
    if ((i != kI)) {
      m_pidController.setI(i);
      kI = i;
    }
    if ((d != kD)) {
      m_pidController.setD(d);
      kD = d;
    }
    if ((iz != kIz)) {
      m_pidController.setIZone(iz);
      kIz = iz;
    }
    if ((ff != kFF)) {
      m_pidController.setFF(ff);
      kFF = ff;
    }
    if ((max != kMaxOutput) || (min != kMinOutput)) {
      m_pidController.setOutputRange(min, max);
      kMinOutput = min;
      kMaxOutput = max;
    }

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());
  }
}
