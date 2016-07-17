package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * @author Leon
 */
public class ServerListPingListener extends GameListener {

    public ServerListPingListener() {
        super();
    }

    @EventHandler
    public void onServerPing( ServerListPingEvent e ) {
        e.setMaxPlayers( 2 );
        e.setMotd( WitchHunter.getCurrentGameState().getMotd() );
    }

}
