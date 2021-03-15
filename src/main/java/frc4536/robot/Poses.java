package frc4536.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import java.lang.annotation.Target;

public class Poses {
    public static final Pose2d
            TRENCH_START = new Pose2d(3.1, -0.75, new Rotation2d(1.0, 0.0)),
            AUTO_TRENCH_SHOOT = new Pose2d(5.0, -0.75, new Rotation2d(4.8, 0.8)),
            TRENCH_SHOOT = new Pose2d(6.5, -0.75, new Rotation2d(6.9, 1.7)),
            TRENCH_END = new Pose2d(8.0, -1.0, new Rotation2d(1.4, -0.7)),
            RENDEZ_SHOOT = new Pose2d(5.5, -1.6, new Rotation2d(5.3, 0.8)),
            RENDEZ_SWEEP = new Pose2d(6.2, -2.9, new Rotation2d(0.3, -0.9)),
            LOADING_STATION = new Pose2d(15.6, -2.5, new Rotation2d(1.0, 0.0)),
            HARD_RESET = new Pose2d(15.6, -2.1, new Rotation2d(1.0, 0.0)),
            TARGET = new Pose2d(0.0, -2.45, new Rotation2d(1.0, 0.0)),
            CENTER_AUTO_START = new Pose2d(3.1, -2.45, new Rotation2d(1.0, 0.0)),
            CENTER_AUTO_END = new Pose2d(6, -2.45, new Rotation2d(1.0, 0.0)),
            INNER_AUTO_WAYPOINT = new Pose2d(4.08, -4.0, new Rotation2d(-1.0, 0.0)),
            INNER_AUTO_END = new Pose2d(5.4, -4, new Rotation2d(-6.2, 1.6)),
            SLALOM_START = new Pose2d(0.762, 0.762, new Rotation2d(0.0)),
            SLALOM_WAYPOINT_ONE = new Pose2d(2.286, 0.762, new Rotation2d(-0.785398)),
            SLALOM_WAYPOINT_TWO = new Pose2d(3.048, 2.286, new Rotation2d(0.0)),
            SLALOM_WAYPOINT_THREE = new Pose2d(6.35, 2.286, new Rotation2d(0.785398)),
            SLALOM_WAYPOINT_FOUR = new Pose2d(7.62, 0.762, new Rotation2d(0.0)),
            SLALOM_WAYPOINT_FIVE = new Pose2d(8.89, 1.524, new Rotation2d(-1.5708)),
            SLALOM_WAYPOINT_SIX = new Pose2d(7.62, 2.286, new Rotation2d(-1.5708)),
            SLALOM_WAYPOINT_SEVEN = new Pose2d(6.096, 0.762, new Rotation2d(0.785398)),
            SLALOM_WAYPOINT_EIGHT = new Pose2d(3.048, 0.762, new Rotation2d(0.0)),
            SLALOM_WAYPOINT_NINE = new Pose2d(1.524, 2.286, new Rotation2d(-1.5708)),
            SLALOM_END = new Pose2d(0.762, 2.286, new Rotation2d(0.0))
            ;

}
