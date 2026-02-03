//This is just some practice for my programming in different situations.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "basicTankDrive/IntoDeep")
public class basicTankDriveIntoTheDeep extends OpMode{

    //This is an example from Gobilda's IntoTheDeep robot 
    private DcMotor Right;
    private DcMotor Left;

    private DcMotor Arm;
    private Servo Pivot;
    private CRServo intake;






    public void init(){
        Right =hardwareMap.dcMotor.get("Right");
        Left = hardwareMap.dcMotor.get("Left");
        Pivot = hardwareMap.servo.get("Pivot");
        intake = hardwareMap.crservo.get("intake");
        Arm = hardwareMap.dcMotor.get("Arm");

        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Left.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void loop(){



      //Motor variables
double forward = 0.8*gamepad1.left_stick_y;
double turn = 0.8*gamepad1.right_stick_x;
double arm = 1*gamepad2.left_stick_y;

Right.setPower(forward + turn);
Left.setPower(forward-turn);
Arm.setPower(arm);

//Intake
        if (gamepad2.a) {
            intake.setPower(1);
        }
        else{
            intake.setPower(0);
        }

        if(gamepad2.b){
            intake.setPower(-1);
        }
        else{
        intake.setPower(0);
        }


        //Pivot
        if(gamepad2.x){
            Pivot.setPosition(90);
        }
        if(gamepad2.y){
            Pivot.setPosition(-90);
        }
        if(gamepad2.right_bumper){
            Pivot.setPosition(0);
        }
    }



}
