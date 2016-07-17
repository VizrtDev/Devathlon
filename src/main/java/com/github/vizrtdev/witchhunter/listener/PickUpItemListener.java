package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickUpItemListener extends GameListener {

    @EventHandler
    public void onPickUpItem( PlayerPickupItemEvent e ) {
        e.setCancelled( true );
    }

}
