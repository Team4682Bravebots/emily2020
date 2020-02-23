package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// TODO: Need Feeder Pneumatics for 4 and 5
public class Shooter {
    private static final double kDefaultPower = 1.0;
    private static final double kOffPower = 0.0;

    private CANSparkMax CSM1;
    private CANSparkMax CSM0;

    public Shooter(int port1, int port2) {
        CSM0 = new CANSparkMax(port1, CANSparkMaxLowLevel.MotorType.kBrushless);
        CSM0.restoreFactoryDefaults();
        CSM1 = new CANSparkMax(port2, CANSparkMaxLowLevel.MotorType.kBrushless);
        CSM1.restoreFactoryDefaults();
    }

    public void setPower(double power) {
        CSM0.set(power);
        CSM1.set(power);
    }

    public void stop() {
        setPower(kOffPower);
    }

    public void shoot() {
        setPower(kDefaultPower);
    }

    public void debug() {

    }

}
