package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
public class PlayerLoginListener extends GameListener {

    public PlayerLoginListener() {
        super();
    }

    @EventHandler
    public void onLogin( PlayerLoginEvent e ) {
        if( !WitchHunter.getCurrentGameState().isJoinable() ) {
            e.disallow( PlayerLoginEvent.Result.KICK_OTHER, "§cDu kannst dieses Spiel in der Aktuellen " +
                    "Phase nicht betreten!" );
        }
    }

}
