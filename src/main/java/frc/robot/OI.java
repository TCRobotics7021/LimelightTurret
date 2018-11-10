/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	private static final int LEFT_HORIZ_AXIS = 0;
	private static final int LEFT_VERT_AXIS = 1;
	private static final int RIGHT_HORIZ_AXIS = 4;
	private static final int RIGHT_VERT_AXIS = 5;
	private static final int LEFT_Z_AXIS = 3;
	private static final int RIGHT_Z_AXIS = 2;

	private static final double STICK_DEADZONE = 0.3;
	private static final double STICK_MAX = 0.97;

	// driver controller setup
	private Joystick driverController = new Joystick(0);
	private Button driverButtonA = new JoystickButton(driverController, 1);
	private Button driverButtonB = new JoystickButton(driverController, 2);
	private Button driverButtonX = new JoystickButton(driverController, 3);
	private Button driverButtonY = new JoystickButton(driverController, 4);
	private Button driverButtonLeftBumper = new JoystickButton(driverController, 5);
	private Button driverButtonRightBumper = new JoystickButton(driverController, 6);
	private Button driverButtonBack = new JoystickButton(driverController, 7);
	private Button driverButtonStart = new JoystickButton(driverController, 8);
	private Button driverButtonLeftAxisPress = new JoystickButton(driverController, 9);
	private Button driverButtonRightAxisPress = new JoystickButton(driverController, 10);

	public OI() {
		//// TRIGGERING COMMANDS WITH BUTTONS
		// Once you have a button, it's trivial to bind it to a button in one of
		// three ways:

		// button.whenPressed(new ExampleCommand());
		// Start the command when the button is pressed and let it run the command
		// until it is finished as determined by it's isFinished method.

		// driverButtonX.whileHeld(new FindTarget());
		// Run the command while the button is being held down and interrupt it once
		// the button is released.

		// button.whenReleased(new ExampleCommand());
		// Start the command when the button is released and let it run the command
		// until it is finished as determined by it's isFinished method.

		// Add these commands here
		// driverButtonA.whenPressed(new DriveRotateToBoiler());
		// driverButtonA.whenReleased(new DriveStop());
		// driverButtonB.whenPressed(new DriveToggleAutoShift());
		// //driverButtonX.whenPressed(new DriveToggleFront());

		// driverButtonLeftBumper.whenPressed(new DriveRetrieveGear());
		// driverButtonLeftBumper.whenReleased(new DriveStop());
		// driverButtonRightBumper.whenPressed(new DriveDeliverGear());
		// driverButtonRightBumper.whenReleased(new DriveStop());
		// driverButtonX.whenPressed(new DriveDistance(0.05));
		// driverButtonY.whenPressed(new DriveDistance(0.02));
	}

	// Utility functions

	// driver controller

	public double getDriverRightY() {
		return -driverController.getRawAxis(RIGHT_VERT_AXIS);
	}

	public double getDriverRightX() {
		return driverController.getRawAxis(RIGHT_HORIZ_AXIS);
	}

	public double getDriverLeftY() {
		return -driverController.getRawAxis(LEFT_VERT_AXIS);
	}

	public double getDriverLeftX() {
		return driverController.getRawAxis(LEFT_HORIZ_AXIS);
	}

	public double getDriverLeftTrigger() {
		return driverController.getRawAxis(LEFT_Z_AXIS);
	}

	public double getDriverRightTrigger() {
		return driverController.getRawAxis(RIGHT_Z_AXIS);
	}

	public double getDriverZ() {
		return driverController.getRawAxis(LEFT_Z_AXIS) - driverController.getRawAxis(RIGHT_Z_AXIS);
	}

	public void rumbleDriver(double rumble) {
		driverController.setRumble(RumbleType.kLeftRumble, rumble);
		driverController.setRumble(RumbleType.kRightRumble, rumble);
	}

	public void rumbleDriverLeft(double rumble) {
		driverController.setRumble(RumbleType.kLeftRumble, rumble);
	}

	public void rumbleDriverRight(double rumble) {
		driverController.setRumble(RumbleType.kRightRumble, rumble);
	}
}
