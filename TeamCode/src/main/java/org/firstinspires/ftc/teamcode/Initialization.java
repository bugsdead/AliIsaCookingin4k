//package org.firstinspires.ftc.teamcode;
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.DigitalChannel;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.hardware;
//
//public class Initialization {
//    public hardware hardware = new hardware();
//
//    public void init(HardwareMap hwMap) {
//        try {
//            hardware.frontLeft = hwMap.get(DcMotor.class, "frontLeft");
//            hardware.backLeft = hwMap.get(DcMotor.class, "backLeft");
//            hardware.frontRight = hwMap.get(DcMotor.class, "frontRight");
//            hardware.backRight = hwMap.get(DcMotor.class, "backRight");
//
//            hardware.arm = hwMap.get(DcMotor.class, "arm");
//            hardware.limitMagnet = hwMap.get(DigitalChannel.class, "limitMagnet");
//            hardware.uppergrabby = hwMap.get(DcMotor.class, "uppergrabby");
//            hardware.lowergrabby = hwMap.get(DcMotor.class, "lowergrabby");
//        } catch (Exception e) {
//            telemetry.addData("Hardware init error", e.getMessage());
//            telemetry.update();
//        }
//    }
//}