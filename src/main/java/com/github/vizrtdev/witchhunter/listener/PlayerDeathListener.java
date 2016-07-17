package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import com.github.vizrtdev.witchhunter.util.GameUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends GameListener {

    public PlayerDeathListener() {
        super();
    }

    @EventHandler
    public void onDeath( PlayerDeathEvent e ) {
        e.setDeathMessage( null );
        WitchHunter.sendMessage( "Der Spieler ? wurde von ? getötet!", e.getEntity().getDisplayName(),
                e.getEntity().getKiller().getDisplayName() );
        GameUtil.runRestart( e.getEntity().getKiller() );
    }

}
