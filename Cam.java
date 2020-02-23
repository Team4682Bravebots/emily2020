package frc.robot;

import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Cam {

    private static final double kP = -0.1f;
    private static final double kMinPower = 0.5;
    private static final double kErrorThreshold = 1.0;

    private Turret controlTurret_;
    private boolean isAiming_;
    private boolean targetFound_;
    private NetworkTable networkTable_;
    private double xError_;
    private double yError_;

    public Cam(Turret turret) {
        controlTurret_ = turret;
        isAiming_ = false;
        targetFound_ = false;
        networkTable_ = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void update() {
        if (isAiming_) {
            aim();
            controlTurret_.update();
        }
    }

    public void startAiming() {
        isAiming_ = true;
    }

    public boolean isTargetFound() {
        return targetFound_;
    }

    public void debug() {
        SmartDashboard.putNumber("X Error", xError_);
        SmartDashboard.putNumber("Y Error", yError_);
        SmartDashboard.putBoolean("Is Cam Aiming?", isAiming_);
        SmartDashboard.putBoolean("Has Cam Found Target?", targetFound_);
    }

    private void aim() {
        // get error
        xError_ = networkTable_.getEntry("tx").getDouble(0);
        yError_ = networkTable_.getEntry("ty").getDouble(0);

        double adjust = 0.0f;
        if (xError_ > kErrorThreshold) {
            adjust = kP * xError_ - kMinPower;

        } else if (xError_ < kErrorThreshold) {
            adjust = kP * xError_ + kMinPower;
        }

        if (adjust != 0.0) {
            controlTurret_.setPower(adjust);
        } else {
            setTargetFound();
        }
    }

    private void setTargetFound() {
        controlTurret_.turnOff();
        targetFound_ = true;
        isAiming_ = false;
    }
}
