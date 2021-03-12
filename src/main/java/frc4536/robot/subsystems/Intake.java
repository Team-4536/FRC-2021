package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.hardware.RobotConstants;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
 
public class Intake extends SubsystemBase {
    private IntakeArm m_rightIntakeArm, m_leftIntakeArm;

    public Intake(){}
 
    public Intake(Intake.IntakeArm rightIntakeArm, 
                  Intake.IntakeArm leftIntakeArm){
          m_rightIntakeArm = rightIntakeArm;
          m_leftIntakeArm = leftIntakeArm;
    }

    public void rotateArmInwardsAndRunPulleyMotor(){
      m_rightIntakeArm.rotateArmInwardsAndRunPulleyMotor();
      m_leftIntakeArm.rotateArmInwardsAndRunPulleyMotor();
    }
    
    public void rotateArmOutwards(){
      m_rightIntakeArm.rotateArmOutwards();
      m_leftIntakeArm.rotateArmOutwards();
    }

    public Command claw(){
      return new RunCommand(() -> this.rotateArmInwardsAndRunPulleyMotor(), this);
    }


    public static class IntakeArm{
      private DigitalInput m_outsideLimitSwitch, m_insideLimitSwitch;
      private SpeedController m_pulleyMotor, m_intakeArmPositionMotor;
      private double m_intakeArmPositionSpeed, m_pulleyMotorSpeed;
   
      public IntakeArm(SpeedController intakeArmPositionMotor, 
                    SpeedController pulleyMotor, 
                    DigitalInput outsideLimitSwitch, 
                    DigitalInput insideLimitSwitch,
                    double intakeArmPositionSpeed,
                    double pulleyMotorSpeed,
                    boolean motorRotatesClockwiseWhenTriggered)  {
          m_insideLimitSwitch = insideLimitSwitch;
          m_outsideLimitSwitch = outsideLimitSwitch;
          m_intakeArmPositionMotor = intakeArmPositionMotor;
          m_pulleyMotor = pulleyMotor;
          m_intakeArmPositionSpeed = intakeArmPositionSpeed;
          m_pulleyMotorSpeed = pulleyMotorSpeed;
   
          m_intakeArmPositionMotor.setInverted(motorRotatesClockwiseWhenTriggered);
          m_pulleyMotor.setInverted(motorRotatesClockwiseWhenTriggered);
      }
  
      public IntakeArm(SpeedController intakeArmMotor, 
                    SpeedController pulleyMotor, 
                    DigitalInput outsideLimitSwitch, 
                    DigitalInput insideLimitSwitch,
                    boolean motorRotatesClockwiseWhenTriggered,
                    double pulleyMotorSpeed)  {
                    new Intake.IntakeArm(intakeArmMotor, pulleyMotor, outsideLimitSwitch,
                               insideLimitSwitch, 0.5, pulleyMotorSpeed, motorRotatesClockwiseWhenTriggered);
      }

      public void rotateArmInwardsAndRunPulleyMotor(){
        rotatePulleyMotor();
        while(insideLimitSwitchIsNotTriggered()){ 
          m_intakeArmPositionMotor.set(m_intakeArmPositionSpeed);
        }
        m_intakeArmPositionMotor.stopMotor();
      }
      
      public void rotateArmOutwards(){
        while(outsideLimitSwitchIsNotTriggered()){
          m_intakeArmPositionMotor.set(-m_intakeArmPositionSpeed);
        }
        m_intakeArmPositionMotor.stopMotor();
      }
  
      private void rotatePulleyMotor(){
        m_pulleyMotor.set(m_pulleyMotorSpeed);
      }
  
      private boolean insideLimitSwitchIsNotTriggered() {
        return !m_insideLimitSwitch.get();
      }
  
      private boolean outsideLimitSwitchIsNotTriggered() {
        return !m_outsideLimitSwitch.get();
      }
    }
}