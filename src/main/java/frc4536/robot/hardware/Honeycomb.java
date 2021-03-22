package frc4536.robot.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.util.Units;
import frc4536.lib.*;

public class Honeycomb implements RobotFrame {
    public static final double ksVolts = 0.235;
    public static final double kvVoltSecondsPerMeter = 0.277;
    public static final double kaVoltSecondsSquaredPerMeter = 0.3;
    public static final double kPDriveVel = 11.5 / 12;
    public static final double kTrackWidthMeters = Units.inchesToMeters(21.8685);
    public static final double kMaxSpeedMetersPerSecond = 1.2;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kRamseteB = 16;
    public static final double kRamseteZeta = 0.7;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(6);

    public RobotConstants m_constants = new RobotConstants(ksVolts, 
                                                           kvVoltSecondsPerMeter, 
                                                           kaVoltSecondsSquaredPerMeter, 
                                                           kPDriveVel, 
                                                           kTrackWidthMeters, 
                                                           kMaxSpeedMetersPerSecond, 
                                                           kMaxAccelerationMetersPerSecondSquared, 
                                                           kRamseteB, 
                                                           kRamseteZeta,
            kWheelDiameterMeters);

    IEncoderMotor m_topFlywheel = new BrushedMAX(1, false, 8192, 21);
    //new PIDBrushedMax(1, false, 8192, new PIDConstants(10.2/12,0,0), 21);
    IEncoderMotor m_bottomFlywheel = new BrushedMAX(1, true, 8192, 20);
    //new PIDBrushedMax(1, true, 8192, new PIDConstants(10.3/12,0,0), 20);

/* 
    The first thing I did was remove the two Pulley Motor variables and replace them with a single Pulley Motor Variable.

    The second thing I did was to remove the getRightIntakeArmPullyMotor and getLeftIntakeArmPullyMotor and replace them
       with getIntakeArmPullyMotor which returns the single Pulley Motor.
*/

    SpeedController m_intakeArmPulleyMotor = new WPI_VictorSPX(2);  // this will be the single pulley motor used by Intake;

//    SpeedController m_rightIntakeArmPullyMotor = new WPI_VictorSPX(2); //TODO: Verify Right Intake Arm Pully Motor Type

    SpeedController m_rightIntakeArmPositionMotor = new WPI_VictorSPX(1); //TODO: Verify Right Intake Arm Position Motor Type
    DigitalInput m_rightIntakeArmOutsideLimitSwitch = new DigitalInput(2); //TODO: Get DigitalInputI ID for Right Intake Arm Outside Limit Switch
    DigitalInput m_rightIntakeArmInsideLimitSwitch = new DigitalInput(3); //TODO: Get DigitalInputI ID for Right Intake Arm Inside Limit Switch

//    SpeedController m_leftIntakeArmPullyMotor = new WPI_VictorSPX(5); //TODO: Verify Left Intake Arm Pully Motor Type
    SpeedController m_leftIntakeArmPositionMotor = new WPI_VictorSPX(3); //TODO: Verify Left Intake Arm Position Motor Type
    DigitalInput m_leftIntakeArmOutsideLimitSwitch= new DigitalInput(4); //TODO: Get DigitalInputI ID for Left Intake Arm Outside Limit Switch
    DigitalInput m_leftIntakeArmInsideLimitSwitch = new DigitalInput(5); //TODO: Get DigitalInputI ID for Left Intake Arm Inside Limit Switch

    AHRS m_navx = new AHRS();   
    IEncoderMotor m_rightMotors = new Neo(10.75, 47, 48);
    IEncoderMotor m_leftMotors = new Neo(10.75, 49, 50);

    SpeedController m_beltMotor = new WPI_VictorSPX(4);
    DigitalInput m_conveyorBeam = new DigitalInput(1);
    DoubleSolenoid m_conveyorBlocker = new DoubleSolenoid(1,0);

    @Override
    public RobotConstants getConstants() {
        return m_constants;
    }

    @Override
    public IEncoderMotor getDrivetrainRightMotor() {
        return m_rightMotors;
    }

    @Override
    public IEncoderMotor getDrivetrainLeftMotor() {
        return m_leftMotors;
    }

    @Override
    public AHRS getDrivetrainNavX() {
        return m_navx;
    }

    @Override
    public DoubleSolenoid getConveyorBlocker() {
        return m_conveyorBlocker;
    }

    @Override
    public SpeedController getBeltMotor() {
        return m_beltMotor;
    }
  
    @Override
    public IEncoderMotor getTopShooterFlywheelMotor() {
        return m_topFlywheel;
    }

    @Override
    public IEncoderMotor getBottomShooterFlywheelMotor() {
        return m_bottomFlywheel;
    }

    @Override
    public DigitalInput getConveyorBeam() {
        return m_conveyorBeam;
    }

    /***    Intake Subsystem Hardware */
    @Override
    public SpeedController getRightIntakeArmPositionMotor() {
        return m_rightIntakeArmPositionMotor;
    }

// not needed - only one Pulley Motor
//    @Override
//    public SpeedController getRightIntakeArmPullyMotor(){
//      return m_rightIntakeArmPullyMotor;
//    }

    @Override
    public DigitalInput getRightIntakeArmOutsideLimitSwitch(){
        return m_rightIntakeArmOutsideLimitSwitch;
    }

    @Override
    public DigitalInput getRightIntakeArmInsideLimitSwitch(){
        return m_rightIntakeArmInsideLimitSwitch;
    }

    @Override
    public SpeedController getLeftIntakeArmPositionMotor() {
        return m_leftIntakeArmPositionMotor;
    }

// not needed - only one Pulley Motor
//    @Override
//    public SpeedController getLeftIntakeArmPullyMotor(){
//      return m_leftIntakeArmPullyMotor;
//    }

    @Override
    public DigitalInput getLeftIntakeArmOutsideLimitSwitch(){
        return m_leftIntakeArmOutsideLimitSwitch;
    }

    @Override
    public DigitalInput getLeftIntakeArmInsideLimitSwitch(){
        return m_leftIntakeArmInsideLimitSwitch;
    }

// this is the single Pulley Motor code
    @Override
    public SpeedController getIntakeArmPulleyMotor(){
      return m_intakeArmPulleyMotor;
    }
}