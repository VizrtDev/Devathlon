package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
public class EntityDamageListener extends GameListener {

    public EntityDamageListener() {
        super();
    }

    @EventHandler
    public void onEntityDamage( EntityDamageEvent e ) {
        if( WitchHunter.getCurrentGameState() == GameState.LOBBY ||
                WitchHunter.getCurrentGameState() == GameState.STARTING ||
                WitchHunter.getCurrentGameState() == GameState.RESTARTING ) {
            e.setCancelled( true );
        }
    }

}
