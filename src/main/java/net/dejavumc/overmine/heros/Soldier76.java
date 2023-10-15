package net.dejavumc.overmine.heros;

import net.dejavumc.overmine.Hero;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Soldier76 extends Hero {

    private double BASE_SPEED = 0.3;
    private double SPRINT_FACTOR = 3;

    public Soldier76(Player p) {
        super(p);

        mesh.loadMesh("soldier76.json");

        this.setSpeed(BASE_SPEED);
    }

    @Override
    public void ability1() {

    }

    public boolean isSprinting = false;
    @Override
    public void ability2() {
        isSprinting = !isSprinting;
        if (isSprinting) {
            this.setSpeed(Math.min(BASE_SPEED * SPRINT_FACTOR, 1));
        } else {
            this.setSpeed(BASE_SPEED);
        }
    }

    @Override
    public void ultimate() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void togglePrimaryFire() {

    }


}
