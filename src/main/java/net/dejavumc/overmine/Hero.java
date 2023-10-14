package net.dejavumc.overmine;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

abstract public class Hero {
    public Player camera;
    public Mesh mesh;

    public Location location;

    public Perspective perspective;

    public Hero(Player p) {
        this.camera = p;

        p.setAllowFlight(true);
        p.setFlying(true);

        perspective = Perspective.FIRST;
        location = p.getLocation().clone();
        mesh = new Mesh(location.clone());
    }

    public Location updateCameraPos() {
        if (perspective == Perspective.FIRST) {
            return mesh.getHeadPos();
        }
        return null;
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
        double SPEED = 0.1;

        Bukkit.broadcastMessage("DELTA YAW: " + delta.getYaw());

        if (farthest != 0) {
            delta.setX((delta.getX() / farthest) * SPEED);
            delta.setZ((delta.getZ() / farthest) * SPEED);
        }
        delta.setY(0);

        location = location.add(delta);

        Bukkit.broadcastMessage("LOC YAW: " + location.getYaw());

        mesh.moveToLocation(location);

        return location;

//        Location finalLoc = updateCameraPos();;
//        Bukkit.broadcastMessage("FINAL YAW: " + finalLoc.getYaw());
//        return finalLoc;
    }
}

enum Perspective {
    FIRST,
    THIRD,
    FOURTH
}