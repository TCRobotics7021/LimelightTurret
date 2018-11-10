/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DefaultTurret;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Add your docs here.
 */
public class Sub_turret extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultTurret());
  }

  StepperMotor panStepperMotor = new StepperMotor(0, 1, 2);
  StepperMotor tiltStepperMotor = new StepperMotor(3, 4, 5);

  public void SetPanSpeed(double speed) {
    panStepperMotor.setEnableGPIO(true);
    panStepperMotor.setSpeed(speed);
  }

  public void SetTiltSpeed(double speed) {
    tiltStepperMotor.setEnableGPIO(true);
    tiltStepperMotor.setSpeed(speed);
  }

}
