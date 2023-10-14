package net.dejavumc.overmine;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

abstract public class Hero {
    public Player camera;
    public Mesh mesh;

    public Location location;

    public Perspective perspective;

    private double SPEED;

    public Hero(Player p) {
        this.camera = p;

        perspective = Perspective.FIRST;
        location = p.getLocation().clone();
        mesh = new Mesh(location.clone());

        SPEED = 0.2f;
    }

    // CAMERA/MOVEMENT
    public Location updateCameraPos() {
        Location loc = mesh.getHeadPos();
        if (perspective == Perspective.THIRD) {
            double yawRadians = toRadians(loc.getYaw());
            double zOffset = Math.sin(yawRadians);
            double xOffset = Math.cos(yawRadians);
            loc = loc.subtract(new Vector(xOffset, 0, zOffset));
        }
        return loc;
    }

    public double toRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    public void setPerspective(Perspective perspective) {
        if (perspective == Perspective.FIRST) {
            mesh.unloadForCamera(camera);
        } else if (perspective == Perspective.THIRD) {
            mesh.loadForCamera(camera);
        }
        updateCameraPos();
    }

    public Location move(Location delta) {
        double farthest = Math.max(Math.abs(delta.getX()), Math.abs(delta.getZ()));

        if (farthest != 0) {
            delta.setX((delta.getX() / farthest) * SPEED);
            delta.setZ((delta.getZ() / farthest) * SPEED);
        }
        delta.setY(0);

        location = location.add(delta);
        location.setYaw(location.getYaw() + delta.getYaw());
        location.setPitch(location.getPitch() + delta.getPitch());

        mesh.moveToLocation(location);

        Location finalLoc = updateCameraPos();

        return finalLoc;
    }

    public void setSpeed(double speed) {
        this.SPEED = speed;
    }

    // ABILITIES
    public void ability1() {

    }

    public void ability2(boolean isEnabling) {
        return;
    }
}

enum Perspective {
    FIRST,
    THIRD,
    FOURTH
}