package frc4536.robot.hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.lib.*;
import frc4536.robot.Robot;

public class VirtualRobot extends SubsystemBase implements RobotFrame {
    public VirtualRobot(){
        if(Robot.isReal()) {
            System.err.println("Please use an actual robot frame.");
            System.exit(1);
        }

        m_timer.reset();
        m_timer.start();

        m_display.add("Virtual Drivetrain", builder -> {
            builder.setSmartDashboardType("DifferentialDrive");
            builder.setActuator(true);
            builder.addDoubleProperty("Left Motor Speed", m_leftMotors::get, m_leftMotors::set);
            builder.addDoubleProperty("Right Motor Speed",() -> -m_rightMotors.get(), a -> m_rightMotors.set(-a));
        });

        m_display.addString("Virtual Pose", () -> new Pose2d(x, y, new Rotation2d(theta)).toString());
    }

    private final Timer m_timer = new Timer();
    private final double kMaxSpeedMetersPerSecond = 3.830232;
    private final ShuffleboardTab m_display = Shuffleboard.getTab("Virtual Motors");
    public RobotConstants m_constants = new RobotConstants(0.00001,
                                                           1/kMaxSpeedMetersPerSecond,
                                0.00001,
                                                           0,
                                                            Units.inchesToMeters(21.8685),
                                                           kMaxSpeedMetersPerSecond, 
                                                           3,
                                                           2,
                                                           0.7,
                                                            1/Math.PI);

   
    
    SpeedController m_beltMotor = new Talon(6);
    SpeedController m_rightIntakeArmPullyMotor = new Talon(5); 
    SpeedController m_rightIntakeArmPositionMotor = new Talon(7); 
    DigitalInput m_rightIntakeArmOutsideLimitSwitch = new DigitalInput(2);
    DigitalInput m_rightIntakeArmInsideLimitSwitch = new DigitalInput(3); 

    SpeedController m_leftIntakeArmPullyMotor = new Talon(5); 
    SpeedController m_leftIntakeArmPositionMotor = new Talon(7); 
    DigitalInput m_leftIntakeArmOutsideLimitSwitch= new DigitalInput(2); 
    DigitalInput m_leftIntakeArmInsideLimitSwitch = new DigitalInput(3); 

    DoubleSolenoid m_conveyorBlocker = new DoubleSolenoid(0,1);
    DoubleSolenoid m_intakeExtender = new DoubleSolenoid(2,3);

    IEncoderMotor m_topFlywheel = new VirtualEncoderMotor(new Talon(3),90);
    IEncoderMotor m_bottomFlywheel = new VirtualEncoderMotor(new Talon(4),100);
    DigitalInput m_beamBreak = new DigitalInput(1);
    DigitalInput m_intakeLimitSwitchTop = new DigitalInput(2);
    DigitalInput m_intakeLimitSwitchBottom = new DigitalInput(3);


    AHRS m_navx = new AHRS(){
        @Override
        public double getAngle(){
            return -Math.toDegrees(theta);
        }

        @Override
        public void reset(){
            theta = 0;
        }
    };

    IEncoderMotor m_leftMotors = new VirtualEncoderMotor(new Talon(0),kMaxSpeedMetersPerSecond);
    IEncoderMotor m_rightMotors = new VirtualEncoderMotor(new Talon(1),-kMaxSpeedMetersPerSecond);

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(m_constants.kTrackWidthMeters);
    double x, y, theta, m_prevTime;

    @Override
    public void periodic(){
        ChassisSpeeds robotSpeed = kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(m_leftMotors.getSpeed(), m_rightMotors.getSpeed()));

        double curTime = m_timer.get();
        double dt = curTime - m_prevTime;
        x += robotSpeed.vxMetersPerSecond*dt*Math.cos(theta);
        y += robotSpeed.vxMetersPerSecond*dt*Math.sin(theta);
        theta += robotSpeed.omegaRadiansPerSecond*dt;
        m_prevTime = curTime;
    }

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