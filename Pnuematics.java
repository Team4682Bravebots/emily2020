package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Pnuematics {

    private Solenoid _sol_deploy;

    private static int kSolChannel_deploy = 2;

    public Pnuematics() {
        _sol_deploy = new Solenoid(kSolChannel_deploy);
    }

    public void pushitout() {
        _sol_deploy.set(true);
    }

    public void bringitin() {
        _sol_deploy.set(false);
    }

}