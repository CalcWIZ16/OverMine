package net.dejavumc.overmine;

import net.dejavumc.overmine.heros.Soldier76;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
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
        Player p = event.getPlayer();

        p.setAllowFlight(true);
        p.setFlying(true);
        p.setSprinting(false);

        Location delta = event.getTo().subtract(event.getFrom());

        delta.setYaw(event.getTo().getYaw() - event.getFrom().getYaw());
        delta.setPitch(event.getTo().getPitch() - event.getFrom().getPitch());

        Hero hero = herosHashMap.get(event.getPlayer().getUniqueId());
        Location newLoc = hero.move(delta);

        event.setTo(newLoc);

        //ability 2
        if (event.getPlayer().isSneaking() == true) {
            event.getPlayer().setSneaking(false);
            Bukkit.broadcastMessage("Ability 2");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        herosHashMap.remove(event.getPlayer().getUniqueId());
    }

    //inputs
    //ultimate
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
        Bukkit.broadcastMessage("THE UNIVERSE IS SINGING TO ME");
    }

    //weapon scrolling
    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        if (event.getNewSlot()==0) {
            Bukkit.broadcastMessage("Ability 1");
            return;
        }
        if (event.getNewSlot()==1) {
            Bukkit.broadcastMessage("Reload");
            return;
        }
        if (event.getNewSlot()<5){
            event.getPlayer().getInventory().setHeldItemSlot(5);
            Bukkit.broadcastMessage("Previous Item");
        } else if (event.getNewSlot()>5) {
            event.getPlayer().getInventory().setHeldItemSlot(5);
            Bukkit.broadcastMessage("Next Item");
        }
    }

    //Interact
    @EventHandler
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Bukkit.broadcastMessage("Interact");
    }
}
