package net.dejavumc.overmine.heros;

import net.dejavumc.overmine.Hero;
import org.bukkit.entity.Player;

public class Soldier76 extends Hero {

    private double BASE_SPEED = 0.4;
    private double SPRINT_FACTOR = 1.5;

    public boolean isEnablingSprinting = true;

    public Soldier76(Player p) {
        super(p);
        this.setSpeed(BASE_SPEED);
    }


    @Override
    public void ability2() {
        if (isEnablingSprinting) {
            this.setSpeed(BASE_SPEED * SPRINT_FACTOR);
            isEnablingSprinting = false;
        } else {
            this.setSpeed(BASE_SPEED);
            isEnablingSprinting = true;
        }
    }



}
