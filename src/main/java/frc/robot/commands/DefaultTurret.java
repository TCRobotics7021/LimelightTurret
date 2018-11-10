/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class DefaultTurret extends Command {
  public DefaultTurret() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.turretSubsystem);
  }

  NetworkTableInstance inst;
  NetworkTable table;
  boolean stepper_enable = false;
  boolean stepper_direction = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INITIALIZED TURRET");

    inst = NetworkTableInstance.getDefault();
    // table = inst.getTable("SmartDashboard/DB");

    setTimeout(5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double leftX = Robot.m_oi.getDriverLeftX();
    double rightY = Robot.m_oi.getDriverRightY();

    Robot.turretSubsystem.SetPanSpeed(leftX);
    Robot.turretSubsystem.SetTiltSpeed(rightY);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
