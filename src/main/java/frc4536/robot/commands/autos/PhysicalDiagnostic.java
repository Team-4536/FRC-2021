package frc4536.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.subsystems.Conveyor;
import frc4536.robot.subsystems.Intake;
import frc4536.robot.subsystems.Shooter;

public class PhysicalDiagnostic extends SequentialCommandGroup{
    public PhysicalDiagnostic(Shooter m_shooter, Conveyor m_conveyor, Intake m_intake ){
        super(m_shooter.spinUp().withTimeout(5),
        new RunCommand(m_conveyor::raiseTop, m_conveyor).withTimeout(1),
        new RunCommand(() -> m_conveyor.moveConveyor(Constants.CONVEYOR_SHOOT_SPEED, true), m_conveyor).withTimeout(5),
        new RunCommand(() -> m_conveyor.moveConveyor(Constants.CONVEYOR_INTAKE_SPEED, true), m_conveyor).withTimeout(5),
//        new RunCommand(m_intake::extendIntake, m_intake).withTimeout(1),
//        new RunCommand(() -> m_intake.intake(Constants.INTAKE_SPINSPEED), m_intake).withTimeout(5),
//        new RunCommand(m_intake::retractIntake, m_intake).withTimeout(1), //TODO: determine how to test Intake effectively
        new RunCommand(m_conveyor::lowerTop, m_conveyor).withTimeout(1));
    }

    @Override
    public String getName(){
        return "Physical Diagnostic Test";
    }
}