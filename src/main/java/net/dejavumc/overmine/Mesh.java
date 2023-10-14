package net.dejavumc.overmine;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Mesh {

    public Location location;

    public Mesh(Location location) {
        this.location = location;
    }

    public void render() {
        // TODO will teleport all armor stands to correct position
    }

    public void loadForCamera(Player p) {
        // TODO will send spawning packets for armor stands to player
    }

    public void unloadForCamera(Player p) {
        // TODO opposite of loadForCamera
    }

    public Location getHeadPos() {
        // TODO find hero head position, for now return location + 1;
        return location.clone().add(new Vector(0, 1, 0));
    }

    public void moveToLocation(Location loc) {
        this.location = loc;
    }
}
