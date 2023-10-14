package net.dejavumc.overmine;

import net.dejavumc.overmine.heros.Soldier76;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class OverMine extends JavaPlugin implements Listener {

    public HashMap<UUID, Hero> herosHashMap = new HashMap<UUID, Hero>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Soldier76 s76 = new Soldier76(event.getPlayer());
        herosHashMap.put(event.getPlayer().getUniqueId(), s76);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        event.setCancelled(true);

        Location delta = event.getTo().subtract(event.getFrom());
        Hero hero = herosHashMap.get(event.getPlayer().getUniqueId());
        hero.move(delta);
    }

    //inputs
    //ultimate
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
        Bukkit.broadcastMessage("THE UNIVERSE IS SINGING TO ME");
    }

    //ability 1
    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        event.setCancelled(true);
        Bukkit.broadcastMessage("Ability 1");
    }

    //ability 2
    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        event.setCancelled(true);
        Bukkit.broadcastMessage("Ability 2");
    }
}
