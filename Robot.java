/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // joystick constants \\
    private static final int kAButton = 1;
    private static final int kBButton = 2;
    private static final int kXButton = 3;
    private static final int kYButton = 4;
    private static final int kLeftTopButton = 5;
    private static final int kRightTopButton = 6;
    private static final int kStartButton_ball = 7;
    private static final int kSelectButton_hatch = 8;
    private static final int kLeftJoytstickButton = 9;
    private static final int kRightJoystickButton = 10;

    private static final int kLeftJoystickAxis_x = 0;
    private static final int kLeftJoystickAxis_y = 1;
    private static final int kLeftTriggerAxis = 2;
    private static final int kRightTriggerAxis = 3;
    private static final int kRightJoystickAxis_x = 4;
    private static final int kRightJoystickAxis_y = 5;
    private static final double kTriggerThreshold = 0.3;

    private static final int kDPad_up = 0;
    private static final int kDPad_left = 90;
    private static final int kDPad_down = 180;
    private static final int kDPad_right = 270;

    // debug setting \\
    private static final boolean kDebug = true;

    // driver ports \\
    private static final int kDriverPort = 0;
    private static final int kCoDriverPort = 1;

    // component ports \\
    private static final int kTurretSparkPort = 5;
    private static final int kShooterSparkPort1 = 3;
    private static final int kShooterSparkPort2 = 4;

    // TODO: Configure these
    private static final int kIntakeSparkPort = 0;
    private static final int kCarouselSparkPort = 1;
    private static final int kDriveLeftMasterPort = 6;
    private static final int kDriveRightMasterPort = 7;
    private static final int kDriveLeftSlavePort = 8;
    private static final int kDriveRightSlavePort = 9;

    // components \\
    private Joystick driver_;
    private Joystick coDriver_;
    private Turret turret_;
    private Cam camera_;
    private Shooter shooty_;
    private Intake intake_;
    private ArcadeDrive drive_;
    private Carousel carousel_;

    /**
     *
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        driver_ = new Joystick(kDriverPort);
        coDriver_ = new Joystick(kCoDriverPort);
        turret_ = new Turret(kTurretSparkPort);
        camera_ = new Cam(turret_);
        shooty_ = new Shooter(kShooterSparkPort1, kShooterSparkPort2);
        intake_ = new Intake(kIntakeSparkPort);
        carousel_ = new Carousel(kCarouselSparkPort);
        drive_ = new ArcadeDrive(kDriveLeftMasterPort, kDriveRightMasterPort, kDriveLeftSlavePort, kDriveRightSlavePort);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        if (kDebug) {
            turret_.debug();
            camera_.debug();
            shooty_.debug();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        // Autonomous Code
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        // DRIVER CODE \\

        // CO-DRIVER CODE \\
        // A Button = turn on Camera and aim
        if (coDriver_.getRawButtonPressed(kAButton)) {
            camera_.startAiming();
        }

        camera_.update();
    }
}
