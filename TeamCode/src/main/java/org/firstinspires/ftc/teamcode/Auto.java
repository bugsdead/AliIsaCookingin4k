//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import java.util.Random;
//
//
//@Autonomous(name = "Auto", group = "Final")
//public class Auto extends LinearOpMode {
//
//    //TIME
//    private ElapsedTime totalGameTime = new ElapsedTime();
//    private ElapsedTime timer = new ElapsedTime();
//
//    //SPEED
//    double armSpeed = 0.5;
//    double driveSpeed = 0.5;
//    double turnSpeed = 0.55;
//
//    //TIME
//    double timeToRotate360 = 2.29;
//    double timeToTravel1Tile = 0.73;
//    double timeToLiftHopper = 1.8;
//    double timeArmtoGround = 0.5;
//    double timeArmtoHopper = 0.55;
//    double timeToIntakeBlock = 1.4;
//    double timeToSpitOutBlock = 1.5;
//
//    //HATCH
//    double open = 0.25;       // Open door position
//    double close = 0.6;// Close door position
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        initialize();
//        setWheelDirection();
//        setArmDirection();
//
//
//        while(opModeInInit()){
//            timer.reset();
//            telemetry.addLine(randomPun);
//        }
//        waitForStart();
//        while (opModeIsActive()) {
//            sendTelemetryData();
//            moveToHopper();
//            grabBlock(1.85);
////            extendClaw();
////            for(int i = 0; i < 8; i++){
////                wiggle();
////            }
//            break;
//        }
//    }
//
//    //Initializes the components
//    private void initialize() {
//        //Wheels
//        hardware.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
//        hardware.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
//        hardware.backLeft = hardwareMap.get(DcMotor.class, "backLeft");
//        hardware.backRight = hardwareMap.get(DcMotor.class, "backRight");
//
//        //Arms
//        hardware.mantis = hardwareMap.get(DcMotor.class, "mantis");
//        hardware.lift = hardwareMap.get(DcMotor.class, "lift");
//        hardware.hopper = hardwareMap.get(DcMotor.class, "hopper");
//
//        //Claws
//        hardware.bottomGrabber = hardwareMap.get(CRServo.class, "bottomGrabber");
//        hardware.topGrabber = hardwareMap.get(CRServo.class, "topGrabber");
//        hardware.door = hardwareMap.get(Servo.class, "door");
//    }
//
//    //Sets the directions of each component
//    private void setWheelDirection() {
//        hardware.frontLeft.setDirection(DcMotor.Direction.REVERSE);
//        hardware.frontRight.setDirection(DcMotor.Direction.FORWARD);
//        hardware.backLeft.setDirection(DcMotor.Direction.REVERSE);
//        hardware.backRight.setDirection(DcMotor.Direction.FORWARD);
//    }
//
//    private void setArmDirection() {
//        hardware.lift.setDirection(DcMotor.Direction.REVERSE);
//        hardware.mantis.setDirection(DcMotor.Direction.REVERSE);
//        hardware.hopper.setDirection(DcMotor.Direction.REVERSE);
//    }
//
//    // Function to send telemetry data
//    private void sendTelemetryData() {
//        // Display robot's current position, speed, and other relevant data
//        telemetry.addData("Time", totalGameTime.seconds());
//        telemetry.addData("Front Left Wheel Speed", hardware.frontLeft.getPower());
//        telemetry.addData("Front Right Wheel Speed", hardware.frontRight.getPower());
//        telemetry.addData("Back Left Wheel Speed", hardware.backLeft.getPower());
//        telemetry.addData("Back Right Wheel Speed", hardware.backRight.getPower());
//
//        telemetry.addData("Bottom Grabber Power", hardware.bottomGrabber.getPower());
//        telemetry.addData("Top Grabber Power", hardware.topGrabber.getPower());
//        telemetry.addData("Door Position", hardware.door.getPosition());
//
//        // Add any additional sensor or status information
//        telemetry.addData("Robot Status", "Active");
//
//        // Update the telemetry
//        telemetry.update();
//    }
//
//    //Set the components speed
//    private void setWheelSpeed(double flSpeed, double frSpeed, double blSpeed, double brSpeed) {
//        hardware.frontLeft.setPower(flSpeed);
//        hardware.frontRight.setPower(frSpeed);
//        hardware.backLeft.setPower(blSpeed);
//        hardware.backRight.setPower(brSpeed);
//    }
//
//    private void setArmSpeed(String armType, double speed) {
//        switch (armType) {
//            case "MANTIS":
//                hardware.mantis.setPower(speed);
//                break;
//            case "LIFT":
//                hardware.lift.setPower(speed);
//                break;
//            case "HOPPER":
//                hardware.hopper.setPower(speed);
//                break;
//        }
//    }
//
//    //Brake for the motors
//    private void wheelBrake() {
//        hardware.frontLeft.setPower(0.0);
//        hardware.frontRight.setPower(0.0);
//        hardware.backLeft.setPower(0.0);
//        hardware.backRight.setPower(0.0);
//    }
//
//    private void armBrake(String armType) {
//        switch (armType) {
//            case "MANTIS":
//                hardware.mantis.setPower(0.05);
//                break;
//            case "LIFT":
//                hardware.lift.setPower(0.0);
//                break;
//            case "HOPPER":
//                hardware.hopper.setPower(0.1);
//                break;
//        }
//    }
//
//    private void clawBrake(String clawType) {
//        switch (clawType) {
//            case "BOTTOM_GRABBER":
//                hardware.bottomGrabber.setPower(0.0);
//                break;
//            case "TOP_GRABBER":
//                hardware.topGrabber.setPower(0.0);
//                break;
//            case "DOOR":
//                hardware.door.setPosition(hardware.door.getPosition());
//                break;
//        }
//    }
//
//
//    //Movement for the wheels, arms, and claws
//    private void moveWheels(String direction, double speed) {
//        switch (direction) {
//            case "FORWARD":
//                setWheelSpeed(speed, speed, speed, speed);
//                break;
//            case "BACKWARD":
//                setWheelSpeed(-speed, -speed, -speed, -speed);
//                break;
//            case "TURN_RIGHT":
//                setWheelSpeed(speed, -speed, speed, -speed);
//                break;
//            case "TURN_LEFT":
//                setWheelSpeed(-speed, speed, -speed, speed);
//                break;
//            case "STOP":
//                wheelBrake();
//                break;
//            case "STRAFE_RIGHT":
//                setWheelSpeed(speed,-speed,-speed,speed);
//        }
//    }
//    private void setClawPosition(String clawType, double position) {
//        switch (clawType) {
//            case "BOTTOM_GRABBER":
//                hardware.bottomGrabber.setPower(position);
//                clawBrake(clawType);
//                break;
//            case "TOP_GRABBER":
//                hardware.topGrabber.setPower(position);
//                clawBrake(clawType);
//                break;
//            case "DOOR":
//                hardware.door.setPosition(position);
//                clawBrake(clawType);
//                break;
//
//        }
//    }
//
//    private void wiggle () {
//        timer.reset();
//        while(timer.seconds() <= 0.1){
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("FORWARD", driveSpeed);
//        }
//        timer.reset();
//        while(timer.seconds() <= 0.1){
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("BACKWARD", driveSpeed);
//        }
//    }
//
//    //Moves the preset block to the hopper
//    private void moveToHopper(){
//        armBrake("MANTIS");
//        while(timer.seconds() <= timeToTravel1Tile * 1.5){
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("BACKWARD", driveSpeed);
//        }
//        timer.reset();
//        while(timer.seconds() <= timeToRotate360 / 8) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("TURN_LEFT", driveSpeed);
//        }
//        timer.reset();
//        setWheelSpeed(0,0,0,0);
//        while(timer.seconds() <= timeToLiftHopper){
//            setArmSpeed("HOPPER", 1);
//        }
//        timer.reset();
//        armBrake("HOPPER");
//        while(timer.seconds() <= 0.35){
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("BACKWARD", driveSpeed);
//        }
//        setWheelSpeed(0,0,0,0);
//        timer.reset();
//        while(timer.seconds() <= 1) {
//            hardware.door.setPosition(open);
//        }
//        for(int i = 0; i < 4; i++){
//            wiggle();
//        }
//        setWheelSpeed(0,0,0,0);
//        timer.reset();
//        while(timer.seconds() <= timeToTravel1Tile * 0.5){
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("FORWARD", driveSpeed);
//        }
//        setWheelSpeed(0,0,0,0);
//        timer.reset();
//        while(timer.seconds() <= timeArmtoGround){
//            telemetry.addLine("Working");
//            telemetry.update();
//            setArmSpeed("MANTIS",-0.6);
//        }
//        timer.reset();
//        while(timer.seconds() <= 0.5) {
//            sleep(500);
//        }
//        timer.reset();
//        while(timer.seconds() <= timeToLiftHopper){
//            setArmSpeed("HOPPER", -1);
//        }
//        armBrake("HOPPER");
//        timer.reset();
//        while(timer.seconds() <= 1) {
//            hardware.door.setPosition(close);
//        }
//        timer.reset();
//        while(timer.seconds() <= timeArmtoHopper*1.9) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            setArmSpeed("MANTIS",0.65);
//        }
//        timer.reset();
//        armBrake("MANTIS");
//        while(timer.seconds() <= (timeToRotate360*1.35)/4) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("TURN_LEFT", driveSpeed);
//        }
//        timer.reset();
//        while(timer.seconds() <= 0.05) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("FORWARD", driveSpeed);
//        }
//        timer.reset();
//        setWheelSpeed(0,0,0,0);
//    }
//
//    private void grabBlock(double timetoin) {
//        timer.reset();
//        while(timer.seconds() <= timeArmtoGround*0.85){
//            telemetry.addLine("Working");
//            telemetry.update();
//            setArmSpeed("MANTIS",-0.35);
//        }
//        timer.reset();
//        armBrake("MANTIS");
//        while(timer.seconds() <= timeToIntakeBlock*timetoin) {
//            hardware.bottomGrabber.setPower(-1.0);
//            hardware.topGrabber.setPower(1.0);
//        }
//        clawBrake("BOTTOM_GRABBER");
//        clawBrake("TOP_GRABBER");
//        timer.reset();
//        while(timer.seconds() <= timeArmtoHopper*2.08) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            setArmSpeed("MANTIS",0.565);
//        }
//        timer.reset();
//        armBrake("MANTIS");
//        while(timer.seconds() <= timeToSpitOutBlock) {
//            hardware.bottomGrabber.setPower(-1.0);
//            hardware.topGrabber.setPower(1.0);
//        }
//        clawBrake("BOTTOM_GRABBER");
//        clawBrake("TOP_GRABBER");
//        timer.reset();
//    }
//
//    private void aftgotohopper() {
//        timer.reset();
//        while(timer.seconds() <= 0.5) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("BACKWARD", driveSpeed);
//        }
//        timer.reset();
//        while(timer.seconds() <= 0.5) {
//            telemetry.addLine("Working");
//            telemetry.update();
//            moveWheels("FORWARD", driveSpeed);
//        }
//        timer.reset();
//        setWheelSpeed(0,0,0,0);
//    }
//}
