package net.dejavumc.overmine;

import org.bukkit.Location;
import org.bukkit.entity.Player;

abstract public class Hero {
    public Player camera;
    public Mesh mesh;

    public Location location;

    public Perspective perspective;

    public Hero(Player p) {
        this.camera = p;
        perspective = Perspective.FIRST;
        location = p.getLocation().clone();
        mesh = new Mesh(location.clone());
    }

    public void updateCameraPos() {
        if (perspective == Perspective.FIRST) {
            camera.teleport(mesh.getHeadPos());
        }
    }

    public void setPerspective(Perspective pers) {
        if (pers == Perspective.FIRST) {
            mesh.unloadForCamera(camera);
        } else if (pers == Perspective.THIRD) {
            mesh.loadForCamera(camera);
        }
        updateCameraPos();
    }

    public void move(Location delta) {
        location.add(delta);
        mesh.moveToLocation(location);
        updateCameraPos();
    }
}

enum Perspective {
    FIRST,
    THIRD,
    FOURTH
}