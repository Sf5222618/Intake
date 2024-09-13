//ground intake :)
package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.sim.TalonFXSimState;


public class GroundIntake extends SubsystemBase{
    
    //motors but i forgot variable name lmao
    private final TalonFX pivotMotor;
    private final TalonFX intakeMotor;

    private final PIDController pid;

    private final DigitalInput LPivotlimitSwitch;
    private final DigitalInput RPivotlimitSwitch;
    private final DigitalInput LIntakelimitSwitch;
    private final DigitalInput RIntakelimitSwitch;

    //private final Encoder = 

    public GroundIntake() {
        pivotMotor = new TalonFX(Constants.pivotMotorID);
        intakeMotor = new TalonFX(Constants.intakeMotorID);

        var slot0Configs = new Slot0Configs();
        slot0Configs.kP = Constants.kP; // An error of 1 rotation results in 2.4 V output
        slot0Configs.kI = Constants.kI; // no output for integrated error
        slot0Configs.kD = Constants.kD; // A velocity of 1 rps results in 0.1 V output

        pivotMotor.getConfigurator().apply(slot0Configs);

        pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);

        LPivotlimitSwitch = new DigitalInput(Constants.lPivotLSPort);
        RPivotlimitSwitch = new DigitalInput(Constants.rPivotLSPort);

        LIntakelimitSwitch = new DigitalInput(Constants.lIntakeLSPort);
        RIntakelimitSwitch = new DigitalInput(Constants.rIntakeLSPort);
        

        //var talonFXConfigs = new TalonFXConfiguration();

        // set slot 0 gains and leave every other config factory-default
        
        
        //pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);

            
    }
        
    public void periodic(){
        double currVal = getEncoderValue();
        if(currVal >= Constants.maxEncoderVal){
            stopPivot();
        }
    }
       
    public void pivotIntake(double speed){
        pivotMotor.set(speed);
    }

    public void spinRoller(){
        intakeMotor.set(Constants.intakeSpeed);
    }

    public void stopPivot(){
        pivotIntake(0);
    }

    public void stopRoller(){
        intakeMotor.set(0);
    }

    public double getEncoderValue(){
        
        return pivotMotor.getPosition().getValueAsDouble();
    }

    public void pivotDown(double speed){
        double currVal = getEncoderValue();
        if(currVal >= Constants.maxEncoderVal || currVal < 0){
          stopPivot();
        }
        else{
          pivotIntake(speed);
        }

    }
    public void pivotUp(double speed){
        
        if(getLIntakelimitSwitch() == true && getRIntakelimitSwitch() == true){
          stopPivot();
        }
        else{
          pivotIntake(speed);
        }
    }

    public void intakeNote(){
        while(LIntakelimitSwitch.get() == false || RIntakelimitSwitch.get() == false){
            spinRoller();
        }
        stopRoller();
    }

    public void resetEncoder(){
        intakeMotor.setPosition(0);
    }

    public boolean getLPivotlimitSwitch(){
        return LPivotlimitSwitch.get();
    }

    public boolean getRPivotlimitSwitch(){
        return RPivotlimitSwitch.get();
    }

    public boolean getLIntakelimitSwitch(){
        return LIntakelimitSwitch.get();
    }

    public boolean getRIntakelimitSwitch(){
        return RIntakelimitSwitch.get();
    }


}

