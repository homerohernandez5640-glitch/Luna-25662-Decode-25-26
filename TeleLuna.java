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


    public void init(){
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");


        lowerRight.setDirection(DcMotorSimple.Direction.REVERSE);
        upperRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void loop() {
        double forward = 0.8 *
                gamepad1.left_stick_y;
        double strafe = 0.8 *
                gamepad1.left_stick_x;
        double turn = 0.8 *
                gamepad1.right_stick_x;


        upperLeft.setPower
                (forward - strafe - turn);
        upperRight.setPower
                (forward + strafe + turn);
        lowerLeft.setPower
                (forward + strafe - turn);
        lowerRight.setPower
                (forward - strafe + turn);


    }


}



