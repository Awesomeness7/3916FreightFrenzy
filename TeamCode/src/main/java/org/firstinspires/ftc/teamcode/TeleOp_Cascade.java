/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mechanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Cascade TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Cascade extends OpMode {

    private Robot bot = new Robot();

    @Override
    public void init() {
        bot.init(hardwareMap, Robot.DriveType.Mechanum);
    }

    @Override
    public void loop() {
        final double STICK_DEAD_ZONE = 0.1;

        double x = 0;
        double y = 0;
        if (gamepad1.left_bumper) {
            bot.mech_rotate(0);
            telemetry.addData("Status", "Rotating Counterclockwise");
            telemetry.update();
        } else if (gamepad1.right_bumper) {
            bot.mech_rotate(1);
            telemetry.addData("Status", "Rotating Clockwise");
            telemetry.update();
        } else if (Math.abs(gamepad1.right_trigger) > STICK_DEAD_ZONE) {
            bot.mech_rotate(0, 0.8);
            telemetry.addData("Status", "Rotating Counterclockwise Fast");
            telemetry.update();
        } else if (Math.abs(gamepad1.left_trigger) > STICK_DEAD_ZONE) {
            bot.mech_rotate(1, 0.8);
            telemetry.addData("Status", "Rotating Clockwise Fast");
            telemetry.update();
        } else {
//            if (Math.abs(gamepad1.left_stick_y) > STICK_DEAD_ZONE) {
//                y = gamepad1.left_stick_y;
//            }
//            if (Math.abs(gamepad1.left_stick_x) > STICK_DEAD_ZONE) {
//                x = gamepad1.left_stick_x;
//            }
//            MechPower pwr = bot.mech_drive(x, y);
//            telemetry.addData("Status", "power: x:" + x + " y:" + y + " =pwr:" + pwr.toString());
//            telemetry.update();
            double fx = gamepad1.left_stick_x;
            double fy = gamepad1.left_stick_y;
            double sx = gamepad1.right_stick_x;
            double sy = gamepad1.right_stick_y;
            String teleStr = "";
            double slowMult = 0.3;
            if (Math.abs(sx) > STICK_DEAD_ZONE || Math.abs(sy) > STICK_DEAD_ZONE) {
                if (Math.abs(sy) > STICK_DEAD_ZONE) {
                    y = sy * slowMult;
                }
                if (Math.abs(sx) > STICK_DEAD_ZONE) {
                    x = sx * slowMult;
                }
                teleStr = "SLOW";
            } else {
                if (Math.abs(fy) > STICK_DEAD_ZONE) {
                    y = fy;
                }
                if (Math.abs(fx) > STICK_DEAD_ZONE) {
                    x = fx;
                }
                teleStr = "FAST";
            }
            MechPower pwr = bot.mech_drive(x, y);
            telemetry.addData("Drive Stat", teleStr + pwr.toString());
            telemetry.update();
        }
        if (Math.abs(gamepad2.left_stick_y) > STICK_DEAD_ZONE) {
            bot.aux_lift(-gamepad2.left_stick_y);
        }
        else
        {
            bot.aux_lift(0);
        }

        //claw
        String message = "Nothing is happening";
        if (Math.abs(gamepad2.right_stick_y) > STICK_DEAD_ZONE) {
            bot.aux_claw3(gamepad2.right_stick_y);
            message = "CLAW LIFT POWER " + gamepad2.right_stick_y;
        } else {
            bot.aux_claw3(0);
            message = "CLAW LIFT POWER 0";
        }
        telemetry.addData("Claw", message);

        // grabber of the build plate
        if (gamepad2.left_bumper) {
            bot.aux_claw4(-1);
            telemetry.addData("Buildplate Grabber", "Power at -1");
            telemetry.update();
        } else if (gamepad2.right_bumper) {
            bot.aux_claw4(1);
            telemetry.addData("Buildplate Grabber", "Power at 1");
            telemetry.update();
        } else {
            bot.aux_claw4(0);
        }
    }
}