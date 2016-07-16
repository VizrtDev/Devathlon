package com.github.vizrtdev.witchhunter.util;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.countdowns.RestartCountdown;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.concurrent.ExecutionException;

public class GameUtil {
    public static void runIngame() {
        WitchHunter.setCurrentGameState( GameState.INGAME );
    }

    public static void runRestart(Player winner) {
        WitchHunter.sendMessage( "Der Spieler ? hat das Spiel ? gewonnen!", winner.getDisplayName(), "WitchHunter" );
        for( Player player : Bukkit.getOnlinePlayers() ) {
            try {
                User user = User.getUser( player.getUniqueId() );
                if(player == winner)
                    user.setRecoveredGames( user.getRecoveredGames() + 1 );
                user.setPlayedGames( user.getPlayedGames() + 1 );
            } catch ( ExecutionException e ) {
                e.printStackTrace();
            }
        }
        WitchHunter.setCurrentGameState( GameState.RESTARTING );
        RestartCountdown.begin();
    }
}
