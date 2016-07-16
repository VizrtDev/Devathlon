package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener extends GameListener {

    public FoodLevelChangeListener() {
        super();
    }

    @EventHandler
    public void onFoodLevelChange( FoodLevelChangeEvent e ) {
        e.setCancelled( true );
    }

}
