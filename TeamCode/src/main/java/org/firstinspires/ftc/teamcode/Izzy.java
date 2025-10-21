package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Izzy")
public class Izzy extends LinearOpMode {
    // Motors
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private DcMotor arm = null;
    private DigitalChannel limitMagnet = null;

    private DcMotor uppergrabby = null;
    private DcMotor lowergrabby = null;

//    private DcMotor arm = null;

    @Override
    public void runOpMode() {
        // Initializing
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        uppergrabby = hardwareMap.get(DcMotor.class, "uppergrabby");
        lowergrabby = hardwareMap.get(DcMotor.class, "lowergrabby");

        limitMagnet.setMode(DigitalChannel.Mode.INPUT);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        uppergrabby.setDirection(DcMotor.Direction.REVERSE);
        lowergrabby.setDirection(DcMotor.Direction.FORWARD);

//        arm.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        uppergrabby.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lowergrabby.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {
            // Code
            double frontRightPower;
            double frontLeftPower;
            double backRightPower;
            double backLeftPower;

            double armUp = 1;
            double armHold = 0;
            double armDown = -1;

            double threshold = 0.3;

            double strafing = 0.4 * gamepad1.left_stick_x;
            double fwdbkwd = -0.4 * gamepad1.left_stick_y;
            double turn = 0.35 * gamepad1.right_stick_x;

            if (gamepad1.x) {
                strafing = 0.3 * gamepad1.left_stick_x;
            } else if (gamepad1.y) {
                strafing = 0.5 * gamepad1.left_stick_x;
            }

            if (gamepad1.x) {
                fwdbkwd = -0.3 * gamepad1.left_stick_y;
            } else if (gamepad1.y) {
                fwdbkwd = -0.5 * gamepad1.left_stick_y;
            }

            if (gamepad1.x) {
                turn = 0.25 * gamepad1.right_stick_x;
            } else if (gamepad1.y) {
                turn = 0.45 * gamepad1.right_stick_x;
            }

//            boolean b = true;
            int bIsPressed = 0;




            if (gamepad1.b) {
                if (gamepad1.b && (!uppergrabby.isBusy() && !lowergrabby.isBusy()) && bIsPressed == 0) {
                    uppergrabby.setPower(3.2);
                    lowergrabby.setPower(3.2);
                    bIsPressed = 1;
//                    b = false;
                }
                if (gamepad1.b && (uppergrabby.isBusy() && lowergrabby.isBusy()) && bIsPressed == 1) {
                    uppergrabby.setPower(0);
                    lowergrabby.setPower(0);
                    bIsPressed = 0;
//                    b = true;
                }
            }

            boolean a = true;



            if ((Math.abs(gamepad1.left_trigger) > threshold) && (limitMagnet.getState())) {
                arm.setPower(armUp * gamepad1.left_trigger);
            } else if ((Math.abs(gamepad1.left_trigger) > threshold) && (!limitMagnet.getState())){
                arm.setPower(0.2);
            }


            frontRightPower = strafing + fwdbkwd + turn;
            frontLeftPower = strafing + fwdbkwd - turn;
            backRightPower = strafing + fwdbkwd - turn;
            backLeftPower = strafing + fwdbkwd + turn;

            frontRight.setPower(frontRightPower);
            frontLeft.setPower(frontLeftPower);
            backRight.setPower(backRightPower);
            backLeft.setPower(backLeftPower);
        }
    }

    private boolean isABoolean(double threshold) {
        return Math.abs(gamepad1.right_trigger) > threshold;
    }
}