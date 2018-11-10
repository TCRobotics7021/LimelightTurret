/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DefaultVision;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Sub_vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  // NetworkTableEntry pipeline = table.getEntry("pipeline"); // Sets
  // limelight’s current pipeline

  private static NetworkTableInstance table = null;

  /**
   * LED lighting modes.
   */
  public enum ledModes {
    ON, OFF, BLINK;
  }

  /**
   * Camera operating modes.
   */
  public enum camModes {
    VISION_PROCESSOR, DRIVER_CAMERA
  }

  /**
   * Vision thresholding pipeline.
   */
  public enum pipelines {
    PIPELINE0, PIPELINE1, PIPELINE2, PIPELINE3, PIPELINE4, PIPELINE5, PIPELINE6, PIPELINE7, PIPELINE8, PIPELINE9 // PIPELINES0..9
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultVision());
  }

  /**
   * Gets whether a target is detected by the Limelight.
   * 
   * @return true if a target is detected, false otherwise.
   */
  public boolean isTarget() {
    return getValue("tv").getDouble(0) == 1;
  }

  /**
   * Horizontal offset from crosshair to target (-27 degrees to 27 degrees).
   * 
   * @return tx as reported by the Limelight.
   */
  public double getTx() {
    return getValue("tx").getDouble(0.00);
  }

  /**
   * Vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees).
   * 
   * @return ty as reported by the Limelight.
   */
  public double getTy() {
    return getValue("ty").getDouble(0.00);
  }

  /**
   * Area that the detected target takes up in total camera FOV (0% to 100%).
   * 
   * @return Area of target.
   */
  public double getTa() {
    return getValue("ta").getDouble(0.00);
  }

  /**
   * Gets target skew or rotation (-90 degrees to 0 degrees).
   * 
   * @return Target skew.
   */
  public double getTs() {
    return getValue("ts").getDouble(0.00);
  }

  /**
   * Gets target latency (ms).
   * 
   * @return Target latency.
   */
  public double getTl() {
    return getValue("tl").getDouble(0.00);
  }

  /**
   * Sets the LED lighting mode.
   * 
   * @param mode LED Lighting mode.
   */
  public void setLedMode(ledModes mode) {
    getValue("ledMode").setNumber(mode.ordinal());
  }

  /**
   * Sets the camera operating mode.
   * 
   * @param mode Camera operating mode.
   */
  public void setCamMode(camModes mode) {
    getValue("camMode").setNumber(mode.ordinal());
  }

  /**
   * Sets the vision thresholding pipeline.
   * 
   * @param line Pipeline index
   */
  public void setPipeline(pipelines line) {
    getValue("pipeline").setNumber(line.ordinal());
  }

  /**
   * Helper method to get an entry from the Limelight NetworkTable.
   * 
   * @param key Key for entry.
   * @return NetworkTableEntry of given entry.
   */
  private NetworkTableEntry getValue(String key) {
    if (table == null) {
      table = NetworkTableInstance.getDefault();
    }
    return table.getTable("limelight").getEntry(key);
  }
}
