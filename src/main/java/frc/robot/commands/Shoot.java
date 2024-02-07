
package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

//Shoot command that uses the Shooter subsystem
public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //Variable to hold our Shooter subsystem
  private final Shooter m_shooter;
  //Is either 1 or 2, determines if we want to shoot powerful or weak
  public int shotType;
  //Keeps track of when this command started
  public long startTime;

  //This command is constructed within Robot class, found in configAuxBindings() method
  public Shoot(Shooter subsystem, int shotType) {
    m_shooter = subsystem;
    this.shotType = shotType;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    if (shotType == Constants.speakerShot){
      //This sets the shooter motors to the specific speed we want
      m_shooter.setSpeed(Constants.speakerShotSpeed);
    }

    else if (shotType == Constants.ampShot){
      m_shooter.setSpeed(Constants.ampShotSpeed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the shooter motors
    m_shooter.setSpeed(0);
  }

  // Returns true to call end()
  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - startTime) >= Constants.spinShooterTimeMs;
  }
}
