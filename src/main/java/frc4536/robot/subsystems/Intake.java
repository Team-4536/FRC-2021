package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 
public class Intake extends SubsystemBase {
    private IntakeArm m_rightIntakeArm, m_leftIntakeArm;

    /*
         The fourth thing I did was create a local variable in Intake for the Pulley Motor since there is only going to be for the Intake,
           not one per IntakeArm. As well as a local variable for the Pulley Motor speed.
    */

    private SpeedController m_intakeArmPulleyMotor;
    private double m_intakeArmPulleyMotorArmSpeed;
 
    /* 
         The fifth thing I did was remove the Constructor Intake with preexisting IntakeArms
           and replace it with a class that takes the components required to create the IntakeArms so that 
           their creation is now going to be internal to the Intake.
         Inside that Constructor I assigned a single Pulley Motor local from the SpeedController passed in
           and instantiated two IntakeArms as well.
    */

//    public Intake(Intake.IntakeArm rightIntakeArm, 
//                  Intake.IntakeArm leftIntakeArm){
//          m_rightIntakeArm = rightIntakeArm;
//          m_leftIntakeArm = leftIntakeArm;
//    }

    public Intake(SpeedController intakeArmPulleyMotor,
                  Double intakeArmPulleyMotorSpeed,
                  SpeedController rightIntakeArmPositionMotor, 
                  DigitalInput rightIntakeArmInsideLimitSwitch,
                  DigitalInput rightIntakeArmOutsideLimitSwitch,
                  boolean rightIntakeArmRotatesClockwiseWhenTriggered,
                  SpeedController leftIntakeArmPositionMotor, 
                  DigitalInput leftIntakeArmInsideLimitSwitch,
                  DigitalInput leftIntakeArmOutsideLimitSwitch,
                  boolean leftIntakeArmRotatesClockwiseWhenTriggered,
                  Double intakeArmPositionSpeed
                ){                
                    m_intakeArmPulleyMotor = intakeArmPulleyMotor;
                    m_intakeArmPulleyMotorArmSpeed = intakeArmPulleyMotorSpeed;

                    m_rightIntakeArm = new IntakeArm(
                      rightIntakeArmPositionMotor, 
                      rightIntakeArmOutsideLimitSwitch, 
                      rightIntakeArmInsideLimitSwitch, 
                      intakeArmPositionSpeed, 
                      rightIntakeArmRotatesClockwiseWhenTriggered);

                      m_leftIntakeArm = new IntakeArm(
                        leftIntakeArmPositionMotor, 
                        leftIntakeArmOutsideLimitSwitch, 
                        leftIntakeArmInsideLimitSwitch, 
                        intakeArmPositionSpeed, 
                        leftIntakeArmRotatesClockwiseWhenTriggered);
                  }

    /*
         the sixth thing I did was move all the Pulley Motor commands here from the IntakeArms, so it can be called once by Intake.

         the seventh thing I did was call the rotatePulleyMotor function from rotateArmInwardsAndRunPulleyMotor function.
    */

    private void rotatePulleyMotor(){
      m_intakeArmPulleyMotor.set(m_intakeArmPulleyMotorArmSpeed);
    }

    public void rotateArmInwardsAndRunPulleyMotor(){
      rotatePulleyMotor();                                      // this is now called here, rather than from IntakeArms
      m_rightIntakeArm.rotateArmInwardsAndRunPulleyMotor();
      m_leftIntakeArm.rotateArmInwardsAndRunPulleyMotor();
    }
    
    public void rotateArmOutwards(){
      m_rightIntakeArm.rotateArmOutwards();
      m_leftIntakeArm.rotateArmOutwards();
    }

    
    /*  
        The first thing I did was change the IntakeArm internal class to private, which restricts it to only the Intake seeing it. 
            I also removed the 'static' statement in front of the IntakeArm class. This is only needed if Intake was not instantiated
            before the IntakeArms were instantiated, which is an illogical thing to do. Since we are now instantiating (creating) 
            an Intake first it is useless.

        The second thing I did was remove the Pulley Motor code from the IntakeArm, since it is shared between them and therefore
          belongs more logically to the Intake, not to any IntakeArm
    */

    // public static class IntakeArm{
    private class IntakeArm {
//      private SpeedController m_pulleyMotor, m_intakeArmPositionMotor; // m_pulleyMotor not needed, since the Pulley Motor is now in Intake
//      private double m_intakeArmPositionSpeed, m_pulleyMotorSpeed; // m_pulleyMotorSpeed not needed, since the Pulley Motor is now in Intake
private DigitalInput m_outsideLimitSwitch, m_insideLimitSwitch;
private SpeedController m_intakeArmPositionMotor;
private double m_intakeArmPositionSpeed; 

      public IntakeArm(SpeedController intakeArmPositionMotor, 
//                    SpeedController pulleyMotor,               // not needed, since the Pulley Motor is now in Intake
                    DigitalInput outsideLimitSwitch, 
                    DigitalInput insideLimitSwitch,
                    double intakeArmPositionSpeed,
//                    double pulleyMotorSpeed,                   // not needed, since the Pulley Motor is now in Intake
                    boolean motorRotatesClockwiseWhenTriggered)  {
          m_insideLimitSwitch = insideLimitSwitch;
          m_outsideLimitSwitch = outsideLimitSwitch;
          m_intakeArmPositionMotor = intakeArmPositionMotor;
//          m_pulleyMotor = pulleyMotor;                          // not needed, since the Pulley Motor is now in Intake
          m_intakeArmPositionSpeed = intakeArmPositionSpeed;
//          m_pulleyMotorSpeed = pulleyMotorSpeed;                // not needed, since the Pulley Motor is now in Intake
   
          m_intakeArmPositionMotor.setInverted(motorRotatesClockwiseWhenTriggered);
//          m_pulleyMotor.setInverted(motorRotatesClockwiseWhenTriggered);     // not needed, since the Pulley Motor is now in Intake
      }
 
    /*
           The third thing I did was remove this Constructor for simplicity. It could be recreated if desired
    */
//      public IntakeArm(SpeedController intakeArmMotor, 
//                    SpeedController pulleyMotor, 
//                    DigitalInput outsideLimitSwitch, 
//                    DigitalInput insideLimitSwitch,
//                    boolean motorRotatesClockwiseWhenTriggered,
//                    double pulleyMotorSpeed)  {
//                    new Intake.IntakeArm(intakeArmMotor, pulleyMotor, outsideLimitSwitch,
//                               insideLimitSwitch, 0.5, pulleyMotorSpeed, motorRotatesClockwiseWhenTriggered);
//      }
  
      public void rotateArmInwardsAndRunPulleyMotor(){
//        rotatePulleyMotor();                               // not needed, since the Pulley Motor is now in Intake
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
  

    // not needed, since the Pulley Motor is now in Intake      
//      private void rotatePulleyMotor(){
//        m_pulleyMotor.set(m_pulleyMotorSpeed);
//      }
  
      private boolean insideLimitSwitchIsNotTriggered() {
        return !m_insideLimitSwitch.get();
      }
  
      private boolean outsideLimitSwitchIsNotTriggered() {
        return !m_outsideLimitSwitch.get();
      }
    }
}