package frc4536.robot.commands.autos;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.Poses;
import frc4536.robot.commands.VisionToTargetCommand;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Intake;

import java.util.ArrayList;
public class SlalomAutoNav extends SequentialCommandGroup {
    public SlalomAutoNav(DriveTrain driveTrain, Pose2d initialPose, Trajectory slalomOne) {
    
        addCommands(
            driveTrain.scurveTo(slalomOne)
        );
    }

    @Override
    public String getName() {
        return "Slalom Auto";
    }
    
}
