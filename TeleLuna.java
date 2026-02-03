package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "TeleLuna")
public class TeleLuna extends OpMode{




    DcMotor upperLeft;
    DcMotor upperRight;
    DcMotor lowerLeft;
    DcMotor lowerRight;
    DcMotor Luna;
    Servo Jesus;
    DcMotor intake;


    public void init(){
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");
        intake = hardwareMap.dcMotor.get("intake");
        Luna = hardwareMap.dcMotor.get("Luna");
        Jesus = hardwareMap.servo.get("Jesus");




        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);


    }
    public void loop() {
        double forward = -0.8 *
                gamepad1.left_stick_y;
        double strafe = -0.8 *
                gamepad1.left_stick_x;
        double turn = -0.8 *
                gamepad1.right_stick_x;
        double spin = 1 *
                gamepad2.left_stick_y;


        upperLeft.setPower
                (forward - strafe - turn);
        upperRight.setPower
                (forward + strafe + turn);
        lowerLeft.setPower
                (forward + strafe - turn);
        lowerRight.setPower
                (forward - strafe + turn);
        Luna.setPower(spin);


        if (gamepad2.x) {
            Jesus.setPosition(70);
        }


        else {
            Jesus.setPosition(0);
        }


        if (gamepad2.right_bumper) {
            Luna.setPower(1);
        }


        else {
            Luna.setPower(0);
        }






        if (gamepad2.a) {
            intake.setPower(-1);
        }


        else {
            intake.setPower(0);


        }


    }


}
