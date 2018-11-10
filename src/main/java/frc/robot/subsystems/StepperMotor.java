package frc.robot.subsystems;
/*----------------------------------------------------------------------------*/

/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Add your docs here.
 */
public class StepperMotor {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public StepperMotor(int EnableGPIO, int DirectionGPIO, int PulseGPIO) {
        ENABLE_CHANNEL = new DigitalOutput(EnableGPIO);
        DIRECTION_CHANNEL = new DigitalOutput(DirectionGPIO);
        PULSE_CHANNEL = new DigitalOutput(PulseGPIO);
    }

    private DigitalOutput ENABLE_CHANNEL;
    private DigitalOutput DIRECTION_CHANNEL;
    private DigitalOutput PULSE_CHANNEL;
    private int StepsPerRevolution = 3200;
    private double PulsePeriod = 1.0;

    public enum DIO_STATE {
        ON, OFF
    }

    public enum State {
        ENABLE, DISABLE
    }

    public enum Direction {
        CW, CCW;
    }

    public void setEnableGPIO(boolean state) {
        ENABLE_CHANNEL.set(!state);
    }

    public void setDirectionGPIO(boolean state) {
        DIRECTION_CHANNEL.set(!state);
    }

    public void setStepsPerRevolution(int steps) {
        StepsPerRevolution = steps;
    }

    public void rotate(double rotations) {
        int numOfsteps = (int) rotations * StepsPerRevolution;
        setDirection(numOfsteps);
        step(numOfsteps);
    }

    public void step(int numOfsteps) {
        for (int current_step = 0; current_step < numOfsteps; current_step++) {
            Pulse();
        }
    }

    public void setSpeed(double speed) {
        if (speed > 0.1 || speed < -0.1) {
            setDirection(speed);
            PulsePeriod = -1 * Math.abs(speed) + 1.1; // period = -speed + 1.1. Min 0.1mS, Max 1mS.
            step(10);
        }

    }

    private void setDirection(double val) {
        if (val > 0) {
            setDirectionGPIO(false);
        } else {
            setDirectionGPIO(true);
        }
    }

    double startTime;
    boolean STATE;

    private void Pulse() {
        PULSE_CHANNEL.set(true);
        delay(PulsePeriod);
        PULSE_CHANNEL.set(false);
        delay(PulsePeriod);
    }

    private void delay(double duration) {
        duration *= 10E6; // Convert milliseconds to nanoseconds
        long startTime = System.nanoTime();
        long currentTime = 0;
        while (currentTime - startTime < duration) {
            currentTime = System.nanoTime();
        }
        System.out.println(currentTime - startTime);
    }

    /*
     * // PULSE_CHANNEL.pulse(1); if (STATE == false) { PULSE_CHANNEL.set(true);
     * startTime = System.currentTimeMillis(); STATE = true; } else { if
     * ((System.currentTimeMillis() - startTime) > PulsePeriod) {
     * PULSE_CHANNEL.set(false); startTime = System.currentTimeMillis(); STATE =
     * false; } }
     */
    // private void delay(int time_ms) {
    // try {
    // Thread.sleep(time_ms);
    // } catch (InterruptedException e) {
    // System.out.println("Stepper Motor Thread Interuppted");
    // }
    // }

}
