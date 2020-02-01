/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.OI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogInput;

public class TurretSubsystem extends SubsystemBase {
  public VictorSP m_turret;
  public AnalogInput e_turret;
   // public Encoder e_turret;
  // public Talon m_feeder;
  double turretVal;
  double angle;
  /**
   * Creates a new TurretSubsystem.
   */
  public TurretSubsystem() {
    m_turret = new VictorSP(0);
    e_turret = new AnalogInput(0);
    //e_turret = new AnalogEncoder(new AnalogInput(0));
    // e_turret.setDistancePerRotation(360/5);
    
    // m_feeder = new Talon(5);i
  }

  public void resetEncoder() {
    e_turret.resetAccumulator();
  }

  public double turretSpeed (double turretVal, double turretVal2){
    turretVal = turretVal/2+0.5;
    turretVal = turretVal*0.25;
    turretVal2 = turretVal2/2+0.5;
    turretVal2 = turretVal2*(0.25);
    turretVal2 = turretVal - turretVal2;
    return turretVal2;
  }

  public void turret(double axis){
    if (Math.abs(axis) >= .25 && axis >= 0) {
    m_turret.set(.25);
    }
    else if (Math.abs(axis) >= .25 && axis <= 0){
    m_turret.set(-.25);
    }
    else {
      m_turret.set(axis);
    }    
  }

  public void moveByDegrees (double angle, double s_angle, double d_angle, TurretSubsystem turret) {
    if (d_angle <= 0){
      m_turret.set(-.25);
    }
    else if (d_angle >= 0) {
      m_turret.set(.25);
    }
    else if (d_angle - angle >= s_angle){
      m_turret.set(0);
      s_angle = turret.getEncoderVal();
    }   
  }

    public double getEncoderVal(){
    turretVal = e_turret.getValue();
    turretVal = turretVal - 425;
    System.out.println(turretVal);
    return turretVal;
  }




  // public void feeder(double speed){
  //   m_feeder.set(speed);
  // }


}
