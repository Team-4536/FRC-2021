/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.*;
import frc4536.lib.IEncoderMotor;
import frc4536.lib.IPIDMotor;
import frc4536.robot.Constants;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private IEncoderMotor m_shooterTop;
    private IEncoderMotor m_shooterBottom;
    private PIDController m_topPIDController = new PIDController(Constants.SHOOTER_P_TOP, 0, 0);
    private PIDController m_bottomPIDController = new PIDController(Constants.SHOOTER_P_BOTTOM, 0, 0);
    private SimpleMotorFeedforward k_top_feedForwards = new SimpleMotorFeedforward(Constants.SHOOTER_TOP_KS, Constants.SHOOTER_TOP_KV);
    private SimpleMotorFeedforward k_bottom_feedForwards = new SimpleMotorFeedforward(Constants.SHOOTER_BOTTOM_KS, Constants.SHOOTER_BOTTOM_KV);

    /**
     * Creates a new Shooter.
     */
    public Shooter(IEncoderMotor top, IEncoderMotor bottom) {
        m_shooterTop = top;
        m_shooterBottom = bottom;
        m_topPIDController.setTolerance(Constants.SHOOTER_TOLERANCE_TOP);
        m_bottomPIDController.setTolerance(Constants.SHOOTER_TOLERANCE_BOTTOM);
        ShuffleboardTab shooter_data = Shuffleboard.getTab("Shooter Data");
        shooter_data.addNumber("Top RPS", () -> m_shooterTop.getSpeed());
        shooter_data.addNumber("Bottom RPS", () -> m_shooterBottom.getSpeed());
        shooter_data.addBoolean("Top Target", () -> m_topPIDController.atSetpoint());
        shooter_data.addBoolean("Bottom Target", () -> m_bottomPIDController.atSetpoint());
    }

    public void setTopPower(double power) {
        m_shooterTop.setVoltage(power);
    }

    public void setBottomPower(double power) {
        m_shooterBottom.setVoltage(power);
    }

    public void stop(){
        m_topPIDController.reset();
        m_bottomPIDController.reset();
        m_topPIDController.setSetpoint(0);
        m_bottomPIDController.setSetpoint(0);
        m_shooterTop.set(0);
        m_shooterBottom.set(0);
    }

    public double getTopRate() {
        return m_shooterTop.getSpeed();
    }

    public double getBottomRate() {
        return m_shooterBottom.getSpeed();
    }

    public boolean ready() {
        return (Math.abs(m_bottomPIDController.getSetpoint() - getBottomRate()) < Constants.SHOOTER_TOLERANCE_TOP) &&
                (Math.abs(m_topPIDController.getSetpoint() - getTopRate()) < Constants.SHOOTER_TOLERANCE_BOTTOM);
    }

    public Command spinUp(DoubleSupplier topRPS, DoubleSupplier bottomRPS) {
        return new RunCommand(() -> setSetpoints(topRPS, bottomRPS), this);
    }

    public Command spinUp(){
        return this.spinUp(() -> Constants.SHOOTER_RPS_TOP, () -> Constants.SHOOTER_RPS_BOTTOM);
    }

    public void setSetpoints(DoubleSupplier topRPS, DoubleSupplier bottomRPS){
        m_shooterTop.setVoltage(
                m_topPIDController.calculate(getTopRate(), topRPS.getAsDouble())
                        + k_top_feedForwards.calculate(topRPS.getAsDouble())
        );
        m_shooterBottom.setVoltage(
                m_bottomPIDController.calculate(getBottomRate(), bottomRPS.getAsDouble())
                        + k_bottom_feedForwards.calculate(bottomRPS.getAsDouble())
        );
    }

    
}
