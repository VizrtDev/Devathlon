package com.github.vizrtdev.witchhunter.kit;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import javax.lang.model.element.Name;

/**
 * ${description}
 *
 * @author Leon
 *
 * @since ${version}
 */
public abstract class Item implements Listener {

    private Material m;
    private String name;
    private boolean availabel

    public Item( Material m, String name ) {
        this.m = m;
        this.name = name;
    }

    @EventHandler public abstract void onInteract( PlayerInteractEvent e );



    public ItemStack toItemStack() {
        ItemStack i = new ItemStack( m );
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName( name );
        i.setItemMeta( meta );
        return i;
    }
}
