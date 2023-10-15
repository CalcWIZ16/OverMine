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
            double THIRD_PERSON_DISTANCE = 5;
            double yawRadians = toRadians(loc.getYaw());
            double xOffset = Math.sin(yawRadians) * -THIRD_PERSON_DISTANCE;
            double zOffset = Math.cos(yawRadians) * THIRD_PERSON_DISTANCE;
            loc = loc.subtract(new Vector(xOffset, 0, zOffset));
            loc.setPitch(0);
        }
        return loc;
    }

    public double toRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
        if (perspective == Perspective.FIRST) {
            camera.setAllowFlight(false);
            camera.setFlying(false);
            mesh.unloadForCamera(camera);
        } else if (perspective == Perspective.THIRD) {
            camera.setAllowFlight(true);
            camera.setFlying(true);
            mesh.loadForCamera(camera);
        }
        camera.teleport(mesh.getHeadPos());
    }

    public Perspective getPerspective() {
        return perspective;
    }

    public Location move(Location from, Location to) {
        if (getPerspective() != Perspective.FIRST) {
            Location delta = to.subtract(from);
            delta.setYaw(to.getYaw() - from.getYaw());
            delta.setPitch(to.getPitch() - from.getPitch());

            double farthest = Math.max(Math.abs(delta.getX()), Math.abs(delta.getZ()));
            if (farthest != 0) {
                delta.setX((delta.getX() / farthest) * SPEED);
                delta.setZ((delta.getZ() / farthest) * SPEED);
            }
            delta.setY(0);

            location = location.add(delta);
            location.setYaw(location.getYaw() + delta.getYaw());
            location.setPitch(location.getPitch() + delta.getPitch());
        } else {
            location = to;
        }

        mesh.moveToLocation(location);

        if (getPerspective() != Perspective.FIRST) {
            return updateCameraPos();
        } else {
            return null;
        }

    }

    public void setSpeed(double speed) {
        this.SPEED = speed;
    }

    // ABILITIES
    public void ability1() {
        if (getPerspective() == Perspective.FIRST) {
            setPerspective(Perspective.THIRD);
        } else {
            setPerspective(Perspective.FIRST);
        }
    }

    public void ability2() {
        return;
    }
}

enum Perspective {
    FIRST,
    THIRD,
    FOURTH
}