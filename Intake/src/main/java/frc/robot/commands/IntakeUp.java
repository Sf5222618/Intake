// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.GroundIntake;

public class IntakeUp extends Command {
  private GroundIntake intake;
  private PIDController pid;
  private double speed;

  /** Creates a new IntakeUp. */
  public IntakeUp(GroundIntake gi) {
    addRequirements(gi);
    intake = gi;
    pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double currVal = intake.getEncoderValue();
    if(currVal < 0 || intake.getLPivotlimitSwitch() == true || intake.getRPivotlimitSwitch() == true){
      
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currVal = intake.getEncoderValue();
    double speed = pid.calculate(currVal,0);
    //intake.pivotUp(speed);
    intake.pivotUp(0.05);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.resetEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (intake.getLPivotlimitSwitch() == true) && (intake.getRPivotlimitSwitch() == true);
  }
}
