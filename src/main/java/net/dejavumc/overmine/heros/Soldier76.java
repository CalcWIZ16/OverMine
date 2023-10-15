package net.dejavumc.overmine.heros;

import net.dejavumc.overmine.Hero;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Soldier76 extends Hero {

    private double BASE_SPEED = 0.3;
    private double SPRINT_FACTOR = 3;

    public Soldier76(Player p) {
        super(p);
        this.setSpeed(BASE_SPEED);
    }


    public boolean isSprinting = false;
    @Override
    public void ability2() {
        isSprinting = !isSprinting;
        if (isSprinting) {
            this.setSpeed(Math.min(BASE_SPEED * SPRINT_FACTOR, 1));
            Bukkit.broadcastMessage("Sprinting");
        } else {
            this.setSpeed(BASE_SPEED);
            Bukkit.broadcastMessage("Not Sprinting");
        }
    }



}
