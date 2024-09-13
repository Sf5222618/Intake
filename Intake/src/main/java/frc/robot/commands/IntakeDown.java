// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.GroundIntake;

public class IntakeDown extends Command {

  private GroundIntake intake;
  private PIDController pid;

  /** Creates a new IntakeDown. */
  public IntakeDown(GroundIntake gi) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(gi);
    intake = gi;

    //pid = new PIDController(Constants.kP,Constants.kI , Constants.kD);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      if(Constants.maxEncoderVal - intake.getEncoderValue() <= 0){
        intake.stopPivot();
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currVal = intake.getEncoderValue();
    double speed = pid.calculate(currVal,Constants.targetEncoderVal);
    //intake.pivotDown(speed);
    intake.pivotDown(0.05);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Constants.maxEncoderVal - intake.getEncoderValue() <= 0);
  }
}
