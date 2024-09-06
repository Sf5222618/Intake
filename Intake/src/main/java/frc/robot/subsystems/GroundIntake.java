//ground intake :)
package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Constants;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.sim.TalonFXSimState;



public class GroundIntake {
    
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

        pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);

        LPivotlimitSwitch = new DigitalInput(0);
        RPivotlimitSwitch = new DigitalInput(0);

        LIntakelimitSwitch = new DigitalInput(0);
        RIntakelimitSwitch = new DigitalInput(0);

        var talonFXConfigs = new TalonFXConfiguration();

        // set slot 0 gains and leave every other config factory-default
        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kV = 0.12;
        slot0Configs.kP = 0.11;
        slot0Configs.kI = 0.5;
        slot0Configs.kD = 0.001;

        // apply all configs, 50 ms total timeout
        m_talonFX.getConfigurator().apply(talonFXConfigs, 0.050);
        
        pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);

            
    }
    @Override
    public void periodic(){
        
    }
        
    }   
    public void pivotIntake(double speed){
        pivotMotor.set(speed);
    }
    public void spinRoller(){
        intakeMotor.set(Constants.intakeSpeed);
    }
     



}

