package com.github.vizrtdev.witchhunter.listener;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.countdowns.LobbyCountdown;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.misc.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import java.util.concurrent.ExecutionException;

public class PlayerJoinListener extends GameListener {

    public PlayerJoinListener() {
        super();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if( !WitchHunter.getInstance().getUserDAO().exists( e.getPlayer().getUniqueId().toString() )) {
            User user = new User();
            user.setId( player.getUniqueId() );
            user.setName( player.getName() );
            user.setPlayedGames( 0 );
            user.setRecoveredGames( 0 );
            WitchHunter.getInstance().getUserDAO().create(user);
        }
        try {
            User user = User.getUser( player.getUniqueId() );
            if(!WitchHunter.playerCollection.contains( e.getPlayer() )) {
                WitchHunter.userCollection.add( user );
                WitchHunter.playerCollection.add( player );
            }
        } catch ( ExecutionException e1 ) {
            e1.printStackTrace();
        }
        e.setJoinMessage( null );
        readyPlayer( player );
        WitchHunter.sendMessage( "Der Spieler ? hat das Spiel betreten!", player.getDisplayName() );

        if( WitchHunter.getCurrentGameState() == GameState.LOBBY )
            if( Bukkit.getOnlinePlayers().size() == 2)
                LobbyCountdown.begin();
        player.teleport( WitchHunter.getCurrentMap().getLobby() );
    }

    public void readyPlayer(Player player) {
        player.setHealth( 20D );
        player.setFoodLevel( 20 );
        player.getInventory().clear();
        player.getInventory().setArmorContents( null );
        player.setFireTicks( 0 );
        player.setAllowFlight( false );
        player.setFlying( false );
        player.setGameMode( GameMode.ADVENTURE );
        for( PotionEffect potionEffect : player.getActivePotionEffects() ) {
            player.removePotionEffect( potionEffect.getType() );
        }
    }

}
