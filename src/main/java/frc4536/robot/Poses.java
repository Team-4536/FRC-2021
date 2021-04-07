package frc4536.robot;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;

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
            SLALOM_START = new Pose2d(Units.feetToMeters(2.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(0)),
            SLALOM_WAYPOINT_ONE = new Pose2d(Units.feetToMeters(7.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(-45)),
            SLALOM_WAYPOINT_TWO = new Pose2d(Units.feetToMeters(10.0),Units.feetToMeters(7.5),Rotation2d.fromDegrees(0)),
            SLALOM_WAYPOINT_THREE = new Pose2d(Units.feetToMeters(20.0),Units.feetToMeters(7.5),Rotation2d.fromDegrees(45)),
            SLALOM_WAYPOINT_FOUR = new Pose2d(Units.feetToMeters(23.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(0)),
            SLALOM_WAYPOINT_FOURHALF = new Pose2d(Units.feetToMeters(27.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(90)),
            SLALOM_WAYPOINT_FIVE = new Pose2d(Units.feetToMeters(27.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(90)),
            SLALOM_WAYPOINT_SIX = new Pose2d(Units.feetToMeters(22.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(45)),
            SLALOM_WAYPOINT_SEVEN = new Pose2d(Units.feetToMeters(20.0),Units.feetToMeters(2.5),Rotation2d.fromDegrees(45)),
            SLALOM_WAYPOINT_EIGHT = new Pose2d(Units.feetToMeters(10.0),Units.feetToMeters(2.5),Rotation2d.fromDegrees(0)),
            SLALOM_WAYPOINT_NINE = new Pose2d(Units.feetToMeters(5.0),Units.feetToMeters(7.5),Rotation2d.fromDegrees(-90)),
            SLALOM_END = new Pose2d(Units.feetToMeters(2.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(0)),
            BOUNCE_START = new Pose2d(Units.feetToMeters(2.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(0)),
            BOUNCE_WAYPOINT_ONE = new Pose2d(Units.feetToMeters(5.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(45)),
            BOUNCE_WAYPOINT_TWO = new Pose2d(Units.feetToMeters(7.5),Units.feetToMeters(12.5),Rotation2d.fromDegrees(-270)),
            BOUNCE_WAYPOINT_THREE = new Pose2d(Units.feetToMeters(12.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(45)),
            BOUNCE_WAYPOINT_FOUR = new Pose2d(Units.feetToMeters(15.0),Units.feetToMeters(12.5),Rotation2d.fromDegrees(-270)),
            BOUNCE_WAYPOINT_FIVE = new Pose2d(Units.feetToMeters(16.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(45)),
            BOUNCE_WAYPOINT_SIX = new Pose2d(Units.feetToMeters(21.5),Units.feetToMeters(2.5),Rotation2d.fromDegrees(45)),
            BOUNCE_WAYPOINT_SEVEN = new Pose2d(Units.feetToMeters(22.5),Units.feetToMeters(12.5),Rotation2d.fromDegrees(-270)),
            BOUNCE_WAYPOINT_EIGHT = new Pose2d(Units.feetToMeters(24.0),Units.feetToMeters(7.5),Rotation2d.fromDegrees(45)),
            BOUNCE_END = new Pose2d(Units.feetToMeters(27.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(0)),
            BARREL_START = new Pose2d(Units.feetToMeters(27.5),Units.feetToMeters(7.5),Rotation2d.fromDegrees(0))
            ;

}
