package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * -- TEAM 3916 --
 * Robot functions using FTCLib library. Used to program season-specific robot functionality.
 * NOTICE: all dimensions are measured in meters (unless specified). all measurements should be taken in meters or else calculations will absolutely mess up
 *
 * NOTICE: all angles are measured in radians (unless specified). please don't ever use degrees for angles
 *
 * @author Aman Anas
 * @author Gabrian Chua
 *
 * @since November 2020
 * @version October 2021
 *
 */

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */


    //Set motors and servos

    //Example:
    //public MotorEx flywheelMotor;

    public MotorEx slideMotor;
    public MotorEx intakeMotor;
    public MotorEx duckMotor;
    public ServoEx intakeBucketServo;
    public ServoEx intakeArmServo;


    //initialize motors and servos
    public void initBot(HardwareMap hw) {
        super.init(hw);

        //Example:
        //flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);

        intakeBucketServo = new SimpleServo(hw, "intake bucket", 0, 180);

        intakeArmServo = new SimpleServo(hw, "intake arm", 0, 270);

        slideMotor = new MotorEx(hw, "slide");
        slideMotor.setRunMode(Motor.RunMode.RawPower);
        slideMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        intakeMotor = new MotorEx(hw, "intake");
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        duckMotor = new MotorEx(hw, "duck motor");
        duckMotor.setRunMode(Motor.RunMode.RawPower);
        duckMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    /*
               ////////////////////////// Methods for extra components //////////////////////////
    */

    public void runIntakeMotor(double speed) {
        intakeMotor.set(speed*(TeleOpConfig.INTAKE_MOTOR_MULTIPLIER));
    }

    public void runSlideMotor(double speed) {
        slideMotor.set(speed*(TeleOpConfig.LINEAR_SLIDE_MULTIPLIER));
    }

    public void runDuckMotor(double speed) {
        duckMotor.set(speed*(TeleOpConfig.DUCK_MOTOR_MULTIPLIER));
    }
    public void runIntakeBucketServo(double speed){
        intakeBucketServo.setPosition(speed*(TeleOpConfig.INTAKE_BUCKET_MULTIPLIER));
    }
    public void runIntakeArmServo(double speed){
        intakeArmServo.setPosition(speed*(TeleOpConfig.INTAKE_ARM_MULTIPLIER));

    }



    /* Example:

    //Flywheel
    public void setFlywheelMotor(double speed) {
       //Set PID Coefficients
       flywheelMotor.setVeloCoefficients(TeleOpConfig.FLYWHEEL_KP, TeleOpConfig.FLYWHEEL_KI, TeleOpConfig.FLYWHEEL_KD);
       //Set Velocity
       flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);
    }

    Refer to FTCLib docs here: https://docs.ftclib.org/ftclib/features/hardware
    */

}
