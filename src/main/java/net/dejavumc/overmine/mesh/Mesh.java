package net.dejavumc.overmine.mesh;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Mesh {

    public ArrayList<MeshArmorStand> armorStands = new ArrayList<MeshArmorStand>();
    private final long walkCycleDuration = 1000;

    private Location location;
    private BukkitTask renderTask;

    public Mesh(Location location) {
        this.location = location;
        this.movementDelta = new Location(location.getWorld(), 0, 0, 0);

        renderTask = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Overmine"), new Runnable() {
            @Override
            public void run() {
                render();
            }
        }, 0, 1);
    }

    private long lastWalkCycleInitiated = 0;
    private Location movementDelta;
    private boolean isWalking = false;

    public void render() {
        double maxMovement = Math.max(Math.abs(movementDelta.getX()), Math.abs(movementDelta.getZ()));
        isWalking = maxMovement > 0.1;

        double progressThroughWalkCycle = (System.currentTimeMillis() - lastWalkCycleInitiated) / (double) walkCycleDuration;

        if (progressThroughWalkCycle > 1) {
            if (isWalking) {
                lastWalkCycleInitiated = System.currentTimeMillis();
            }
        }

        if (progressThroughWalkCycle > 1 || Math.abs(0.5 - progressThroughWalkCycle) < 0.05) {
            if (!isWalking) {
                lastWalkCycleInitiated = 0;
                progressThroughWalkCycle = -1;
            }
        }

        Bukkit.broadcastMessage("progressThroughWalkCycle: " + progressThroughWalkCycle);

        for (MeshArmorStand armorStand : armorStands) {
            armorStand.armorStand.teleport(location.clone().add(armorStand.offsetFromCenter));
        }
    }

    public void loadForCamera(Player p) {
    }

    public void unloadForCamera(Player p) {

    }

    public Location getHeadPos() {
        return location.clone();
    }

    public void moveToLocation(Location loc, Location movementDelta) {
        this.location = loc;
        this.movementDelta = movementDelta;
    }
}