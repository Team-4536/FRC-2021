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
public class BounceAutoNav extends SequentialCommandGroup {
    public BounceAutoNav(DriveTrain driveTrain, Pose2d initialPose, Trajectory bounceOne, Trajectory bounceTwo, Trajectory bounceThree, Trajectory bounceFour, Trajectory bounceFive, Trajectory bounceSix, Trajectory bounceSeven, Trajectory bounceEight, Trajectory bounceNine) {
    
     
    
        addCommands(
            driveTrain.scurveTo(bounceOne),
            driveTrain.scurveTo(bounceTwo),
            driveTrain.scurveTo(bounceThree),
            driveTrain.scurveTo(bounceFour),
            driveTrain.scurveTo(bounceFive),
            driveTrain.scurveTo(bounceSix),
            driveTrain.scurveTo(bounceSeven),
            driveTrain.scurveTo(bounceEight),
            driveTrain.scurveTo(bounceNine)

        );
    }

    @Override
    public String getName() {
        return "Bounce Auto";
    }
    
}


