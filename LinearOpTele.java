


package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
 * This file contains an example of a Linear "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode is executed.
 *
 * This particular OpMode illustrates driving a 4-motor Omni-Directional (or Holonomic) robot.
 * This code will work with either a Mecanum-Drive or an X-Drive train.
 * Both of these drives are illustrated at https://gm0.org/en/latest/docs/robot-design/drivetrains/holonomic.html
 * Note that a Mecanum drive must display an X roller-pattern when viewed from above.
 *
 * Also note that it is critical to set the correct rotation direction for each motor.  See details below.
 *
 * Holonomic drives provide the ability for the robot to move in three axes (directions) simultaneously.
 * Each motion axis is controlled by one Joystick axis.
 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *
 * This code is written assuming that the right-side motors need to be reversed for the robot to drive forward.
 * When you first test your robot, if it moves backward when you push the left stick forward, then you must flip
 * the direction of all 4 motors (see code below).
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */


@TeleOp(name="Basic: Omni Linear OpMode")
public class LinearOpTele extends LinearOpMode {


    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor upperLeft = null;
    private DcMotor lowerLeft = null;
    private DcMotor upperRight = null;
    private DcMotor lowerRight = null;

    private DcMotor Luna = null;

    private DcMotor intake = null;
    private Servo Jesus = null;


    @Override
    public void runOpMode() {


        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        upperLeft = hardwareMap.get(DcMotor.class, "upperLeft");
        lowerLeft = hardwareMap.get(DcMotor.class, "lowerLeft");
        upperRight = hardwareMap.get(DcMotor.class, "upperRight");
        lowerRight = hardwareMap.get(DcMotor.class, "lowerRight");
        Jesus = hardwareMap.get(Servo.class, "Jesus");
       Luna = hardwareMap.get(DcMotor.class, "Luna");
        intake = hardwareMap.get(DcMotor.class, "intake");

        // ########################################################################################
        // !!!            IMPORTANT Drive Information. Test your motor directions.            !!!!!
        // ########################################################################################
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot (the wheels turn the same direction as the motor shaft)
        // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction.  So, start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
        // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward
        // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.
        upperLeft.setDirection(DcMotor.Direction.REVERSE);
        lowerLeft.setDirection(DcMotor.Direction.REVERSE);
        upperRight.setDirection(DcMotor.Direction.FORWARD);
        lowerRight.setDirection(DcMotor.Direction.FORWARD);


        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;


            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;


            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double upperLeftPower  = axial + lateral + yaw;
            double upperRightPower = axial - lateral - yaw;
            double lowerLeftPower   = axial - lateral + yaw;
            double lowerRightPower  = axial + lateral - yaw;


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(upperLeftPower), Math.abs(upperRightPower));
            max = Math.max(max, Math.abs(lowerLeftPower));
            max = Math.max(max, Math.abs(lowerRightPower));


            if (max > 1.0) {
                upperLeftPower  /= max;
                upperRightPower /= max;
                lowerLeftPower   /= max;
                lowerRightPower  /= max;
            }


            // This is test code:
            //
            // Uncomment the following code to test your motor directions.
            // Each button should make the corresponding motor run FORWARD.
            //   1) First get all the motors to take to correct positions on the robot
            //      by adjusting your Robot Configuration if necessary.
            //   2) Then make sure they run in the correct direction by modifying the
            //      the setDirection() calls above.
            // Once the correct motors move in the correct direction re-comment this code.


           /*
           upperLeftPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
           lowerLeftPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
           upperRightPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
           lowerRightPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
           */


            // Send calculated power to wheels
            upperLeft.setPower(upperLeftPower);
            upperRight.setPower(upperRightPower);
            lowerLeft.setPower(lowerLeftPower);
            lowerRight.setPower(lowerRightPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", upperLeftPower, upperRightPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", lowerLeftPower, lowerRightPower);
            telemetry.update();


            if(gamepad1.x){
                drive(0.5,100,0,0);
                upperLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                upperRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                lowerLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                lowerRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                gamepad1.reset();}




            if (gamepad2.x) {
                Jesus.setPosition(70);
            }
            else {
                Jesus.setPosition(0);}



            if (gamepad2.right_bumper) {
                Luna.setPower(1);}
            else {
                Luna.setPower(0);}






            if (gamepad2.a) {
                intake.setPower(-1);}
            else {
                intake.setPower(0);}

        }

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

