package frc4536.robot.commands.autos;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.Poses;
import frc4536.robot.commands.IntakeCommands;
import frc4536.robot.commands.ShootCommand;
import frc4536.robot.hardware.RobotConstants;
import frc4536.robot.subsystems.Conveyor;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Intake;
import frc4536.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.trajectory.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class GalacticSearchChallengeCommand extends ParallelCommandGroup {
    DriveTrain m_driveTrain;
    Intake m_intake;
    RobotConstants m_constants;
    Paths m_path;

    public GalacticSearchChallengeCommand(Intake intake, 
                                          DriveTrain driveTrain, 
                                          RobotConstants robotConstants, 
                                          GalacticSearchChallengeCommand.Paths path) {
        m_intake = intake;
        m_driveTrain = driveTrain;
        m_constants = robotConstants;
        m_path = path;
        var trajectory = GetTrajectory();
        addCommands(m_driveTrain.scurveTo(trajectory),m_intake.claw());
    }

    private Trajectory GetTrajectory()
    {                
        var config = new TrajectoryConfig(m_constants.kMaxSpeedMetersPerSecond, m_constants.kMaxAccelerationMetersPerSecondSquared);

        var wayPoints = new ArrayList<Pose2d>();
        wayPoints.add(GetStartingPose());
        wayPoints.addAll(GetPowerCellPoses());
        wayPoints.add(GetEndingPose());

        return TrajectoryGenerator.generateTrajectory(wayPoints, config);
    }

    private Pose2d GetStartingPose() throws IllegalArgumentException{
        switch(m_path){
            case BLUE_PATH_A:
                return Poses.GalacticSearchChallenge.BLUE_PATH_A_START;
            case BLUE_PATH_B:
                return Poses.GalacticSearchChallenge.BLUE_PATH_B_START;
            case RED_PATH_A:
                return Poses.GalacticSearchChallenge.RED_PATH_A_START;
            case RED_PATH_B:
                return Poses.GalacticSearchChallenge.RED_PATH_B_START;
        }
        throw new IllegalArgumentException("GetStartingPose in GalacticSearchChallengeCommand did not have a matching enum for Path");
    }

    private ArrayList<Pose2d> GetPowerCellPoses () throws IllegalArgumentException{
        var powerCellPoses = new ArrayList<Pose2d>();
        return powerCellPoses;
    }
    
    private Pose2d GetEndingPose(){
        switch(m_path){
            case BLUE_PATH_A:
                return Poses.GalacticSearchChallenge.BLUE_PATH_A_END;
            case BLUE_PATH_B:
                return Poses.GalacticSearchChallenge.BLUE_PATH_B_END;
            case RED_PATH_A:
                return Poses.GalacticSearchChallenge.RED_PATH_A_END;
            case RED_PATH_B:
                return Poses.GalacticSearchChallenge.RED_PATH_B_END;
        }
        throw new IllegalArgumentException("GetEndingPose in GalacticSearchChallengeCommand did not have a matching enum for Path");
    }

    public enum Paths{
        BLUE_PATH_A,
        RED_PATH_A,
        BLUE_PATH_B,
        RED_PATH_B
    }
}