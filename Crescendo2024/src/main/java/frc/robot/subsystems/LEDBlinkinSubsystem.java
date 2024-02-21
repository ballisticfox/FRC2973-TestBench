package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDBlinkinSubsystem extends SubsystemBase
{

    private PWMSparkMax LED;

    public LEDBlinkinSubsystem()
    {

        ///DATA CHANNELS///
        final int ledChannel  = 9;

        ///MOTOR SETUP
        LED  = new PWMSparkMax(ledChannel);
        
    }

    public void ledStrip()
    {
        LED.set(0.03);
    }


}
