package net.dejavumc.overmine.mesh;

import net.dejavumc.overmine.mesh.ArmorStandTag;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

public class MeshArmorStand {
    public ArmorStandTag tag;
    public ArmorStand armorStand;
    public Vector offsetFromCenter;

    public MeshArmorStand(ArmorStandTag tag, ArmorStand armorStand, Vector offsetFromCenter) {
        this.tag = tag;
        this.armorStand = armorStand;
        this.offsetFromCenter = offsetFromCenter;
    }
}
