package net.dejavumc.overmine;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.util.Vector;

public class Mesh {

    public Location location;

    public Villager villager;

    public Mesh(Location location) {
        this.location = location;

        Villager v = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        v.setAI(false);
        v.setInvulnerable(true);

        villager = v;
    }

    public void render() {
        villager.teleport(location);
    }

    public void loadForCamera(Player p) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getIntegers().write(0, villager.getEntityId());
        packet.getUUIDs().write(0, villager.getUniqueId());
        packet.getEntityTypeModifier().write(0, EntityType.VILLAGER);

        packet.getDoubles().write(0, location.getX());
        packet.getDoubles().write(1, location.getY());
        packet.getDoubles().write(2, location.getZ());

        int realPitch = (int) location.getPitch() * 256 / 360;
        int realYaw = (int) location.getYaw() * 256 / 360;
        packet.getIntegers().write(1, realPitch);
        packet.getIntegers().write(2, realYaw);
        packet.getIntegers().write(3, realYaw);

        packet.getIntegers().write(4, 0);
        packet.getIntegers().write(5, 0);
        packet.getIntegers().write(6, 0);
        packet.getIntegers().write(7, 0);

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
    }

    public void unloadForCamera(Player p) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);

        packet.getIntegers().write(0, 1);
        packet.getIntegerArrays().write(0, new int[]{villager.getEntityId()});

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
    }

    public Location getHeadPos() {
        return location.clone();
    }


    public void moveToLocation(Location loc) {
        this.location = loc;
    }
}
