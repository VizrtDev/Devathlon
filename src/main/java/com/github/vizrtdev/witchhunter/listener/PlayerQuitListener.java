package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import com.github.vizrtdev.witchhunter.util.GameUtil;
import com.github.vizrtdev.witchhunter.util.ListUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

public class PlayerQuitListener extends GameListener {

    public PlayerQuitListener() {
        super();
    }

    @EventHandler
    public void onQuit( PlayerQuitEvent e ) {
        try {
            User user = User.getUser( e.getPlayer().getUniqueId() );
            WitchHunter.getInstance().getUserDAO().update(user);


            if( WitchHunter.getCurrentGameState() == GameState.STARTING  ) {
                WitchHunter.setCurrentGameState( GameState.LOBBY );
            }


            e.setQuitMessage( null );
            WitchHunter.sendMessage( "Der Spieler ? hat das Spiel verlassen!", e.getPlayer().getDisplayName() );

            if( WitchHunter.getCurrentGameState() == GameState.INGAME ) {
                List<Player> players = new ArrayList<>(  );
                for( Player all : Bukkit.getOnlinePlayers() ) players.add( all );
                GameUtil.runRestart(ListUtil.getRandomItem( players ));
            }
        } catch ( ExecutionException e1 ) {
            e1.printStackTrace();
        }
    }

}
