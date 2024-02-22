package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDBlinkinSubsystem extends SubsystemBase
{

    private PWMSparkMax LED;
    private int iterator;
    private double[] patterns = {0.03,-0.79,0.13,0.15,-0.01,0.55,};

    public LEDBlinkinSubsystem()
    {

        ///DATA CHANNELS///
        final int ledChannel  = 9;

        ///MOTOR SETUP
        LED  = new PWMSparkMax(ledChannel);

        //LED SETUP
        iterator = 0;
        
    }

    public void ledStrip()
    {
        LED.set(patterns[iterator]);
        iterator++;
        if(iterator > patterns.length){
            iterator = 0;
        }
    }


}
