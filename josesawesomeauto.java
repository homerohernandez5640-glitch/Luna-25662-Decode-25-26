package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptAprilTag;


@Autonomous
public class josesawesomeauto extends LinearOpMode {
    DcMotor lowerLeft;
    DcMotor upperLeft;
    DcMotor lowerRight;
    DcMotor upperRight;
    CRServo spinny1;
    CRServo spinny2;
    DcMotor Luna;


    @Override
    public void runOpMode() {
        lowerLeft = hardwareMap.dcMotor.get("lowerLeft");
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        lowerRight = hardwareMap.dcMotor.get("lowerRight");
        upperRight = hardwareMap.dcMotor.get("upperRight");

        spinny1 = hardwareMap.crservo.get("spinny1");
        spinny2 = hardwareMap.crservo.get("spinny2");
        Luna = hardwareMap.dcMotor.get("Luna");




        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);




        waitForStart();
        drive(.8, 500, 0,0);
        sleep(100);
        drive(.8, 0, 500,0);
        sleep(100);
        drive(.8, 0, 0,2000);
        sleep(30000);



    }


    public void drive(double power, int forward, int strafe, int turn) {
        lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        lowerRight.setTargetPosition(forward - strafe + turn);
        upperRight.setTargetPosition(forward + strafe + turn);
        lowerLeft.setTargetPosition(forward + strafe - turn);
        upperLeft.setTargetPosition(forward - strafe - turn);


        lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        lowerLeft.setPower(power);
        upperLeft.setPower(power);
        lowerRight.setPower(power);
        upperRight.setPower(power);


        while (lowerLeft.isBusy() && upperLeft.isBusy() && upperRight.isBusy() && lowerRight.isBusy()) {
        }




        sleep(100);
    }
}



