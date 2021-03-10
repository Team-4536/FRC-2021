package frc4536.robot.hardware;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.util.Units;
import frc4536.lib.*;

public class TestRobot implements RobotFrame {
    private final double ksVolts = 2;
    private final double kvVoltSecondsPerMeter = 0.353;
    private final double kaVoltSecondsSquaredPerMeter = 0.00864;
    private final double kPDriveVel = 1.4;
    private final double kTrackWidthMeters = 0.7112;
    private final double kMaxSpeedMetersPerSecond = 2.3;
    private final double kMaxAccelerationMetersPerSecondSquared = 3;
    private final double kRamseteB = 8;
    private final double kRamseteZeta = 0.7;
    private final double kWheelDiameterInches = Units.inchesToMeters(6);
    public RobotConstants m_constants = new RobotConstants(ksVolts, 
                                                           kvVoltSecondsPerMeter, 
                                                           kaVoltSecondsSquaredPerMeter, 
                                                           kPDriveVel, 
                                                           kTrackWidthMeters, 
                                                           kMaxSpeedMetersPerSecond, 
                                                           kMaxAccelerationMetersPerSecondSquared, 
                                                           kRamseteB, 
                                                           kRamseteZeta,
                                                           kWheelDiameterInches);

   
  
    VirtualMotor m_beltMotor = new VirtualMotor("Belt Motor");
    VirtualSolenoid m_conveyorBlocker = new VirtualSolenoid(0,1);
    VirtualMotor m_rightIntakeArmPullyMotor = new VirtualMotor("Intake arm pully motor right");
    VirtualMotor m_rightIntakeArmPositionMotor = new VirtualMotor("Intake arm position motor right");
    VirtualMotor m_leftIntakeArmPullyMotor = new VirtualMotor("Intake arm pully motor left");
    VirtualMotor m_leftIntakeArmPositionMotor = new VirtualMotor("Intake arm position motor left");
   
    AHRS m_navx = new AHRS(){
        @Override
        public double getAngle(){
            return -super.getAngle();
        }
    };
    Encoder m_leftEncoder = new Encoder(0,1);
    Encoder m_rightEncoder = new Encoder(2,3);
    IEncoderMotor m_topFlywheel = new VirtualEncoderMotor("Top Flywheel",8.0*0.478779);
    IEncoderMotor m_bottomFlywheel = new VirtualEncoderMotor("Bottom Flywheel",8.0*0.478779);
    IEncoderMotor m_leftMotors = new PWMEncoderMotor(new SpeedControllerGroup(new Spark(0), new Spark(1)), m_leftEncoder, 2048);
    IEncoderMotor m_rightMotors = new PWMEncoderMotor(new SpeedControllerGroup(new Spark(2), new Spark(3)), m_rightEncoder, 2048);
    DigitalInput m_beamBreak = new DigitalInput(12);
    DigitalInput m_leftIntakeArmOutsideLimitSwitch= new DigitalInput(2);
    DigitalInput m_leftIntakeArmInsideLimitSwitch = new DigitalInput(3); 
    DigitalInput m_rightIntakeArmOutsideLimitSwitch = new DigitalInput(2);
    DigitalInput m_rightIntakeArmInsideLimitSwitch = new DigitalInput(3); 

   
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
    public DoubleSolenoid getConveyorBlocker() {
        return m_conveyorBlocker;
    }

    @Override
    public SpeedController getBeltMotor() {
        return m_beltMotor;
    }

    @Override
    public AHRS getDrivetrainNavX() {
        return m_navx;
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
        return m_beamBreak;
    }
    @Override
    public SpeedController getRightIntakeArmPositionMotor() {
        return m_rightIntakeArmPositionMotor;
    }

    @Override
    public SpeedController getRightIntakeArmPullyMotor(){
      return m_rightIntakeArmPullyMotor;
    }

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

    @Override
    public SpeedController getLeftIntakeArmPullyMotor(){
      return m_leftIntakeArmPullyMotor;
    }

    @Override
    public DigitalInput getLeftIntakeArmOutsideLimitSwitch(){
        return m_leftIntakeArmOutsideLimitSwitch;
    }

    @Override
    public DigitalInput getLeftIntakeArmInsideLimitSwitch(){
        return m_leftIntakeArmInsideLimitSwitch;
    }

   
}
