package net.dejavumc.overmine.mesh;

import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Vector;

public class MeshDisplayEntity {
    public ItemDisplay display;
    public Vector offsetFromCenter;
    public String id;

    public MeshDisplayEntity(ItemDisplay display, Vector offsetFromCenter, String id) {
        this.display = display;
        this.offsetFromCenter = offsetFromCenter;
        this.id = id;
    }
}
