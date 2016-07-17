package com.github.vizrtdev.witchhunter.kit;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item extends GameListener {

    @Getter
    private Material m;
    @Getter
    private String name;
    @Getter
    private boolean usable = true;
    @Getter
    @Setter
    private EventExecutor eventExecutor;

    public Item( Material m, String name, EventExecutor eventExecutor ) {
        this.m = m;
        this.eventExecutor = eventExecutor;
        this.name = name;
    }

    @EventHandler
    public void onInteract( PlayerInteractEvent e ) {
        if ( e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
            if(e.getMaterial() == m ) {
                if(m == Material.EXP_BOTTLE || m == Material.DIRT)
                    e.setCancelled( true );
                if ( isUsable() ) {
                    doReload();
                    eventExecutor.doEvent(e.getPlayer());
                }
            }
        }
    }

    public void doReload() {
        usable = false;
        Bukkit.getScheduler().runTaskLater( WitchHunter.getInstance(), new Runnable() {
            @Override
            public void run() {
                usable = true;
            }
        }, 20L * 15 );
    }

    public ItemStack toItemStack() {
        ItemStack i = new ItemStack( m );
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName( name );
        i.setItemMeta( meta );
        return i;
    }
}
