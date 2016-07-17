package com.github.vizrtdev.witchhunter.util;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.countdowns.RestartCountdown;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.kit.KitManager;
import com.github.vizrtdev.witchhunter.kit.kits.Witch;
import com.github.vizrtdev.witchhunter.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.concurrent.ExecutionException;

public class GameUtil {
    public static void runIngame() {
        WitchHunter.setCurrentGameState( GameState.INGAME );
        PlayerManager.getHunter().teleport( WitchHunter.getCurrentMap().getSpawn1() );
        PlayerManager.getWitch().teleport( WitchHunter.getCurrentMap().getSpawn2() );
        KitManager.run();
        Bukkit.getPluginManager().registerEvents( KitManager.getHunter().getFirstItem(), WitchHunter.getInstance() );
        Bukkit.getPluginManager().registerEvents( KitManager.getHunter().getSecondItem(), WitchHunter.getInstance() );
        Bukkit.getPluginManager().registerEvents( KitManager.getHunter().getThirdItem(), WitchHunter.getInstance() );
        Bukkit.getPluginManager().registerEvents( KitManager.getWitch().getFirstItem(), WitchHunter.getInstance() );
        Bukkit.getPluginManager().registerEvents( KitManager.getWitch().getSecondItem(), WitchHunter.getInstance() );
        Bukkit.getPluginManager().registerEvents( KitManager.getWitch().getThirdItem(), WitchHunter.getInstance() );
        //Because of Asynchronus Task run these Methodes Later.
        Bukkit.getScheduler().runTaskLater( WitchHunter.getInstance(), new Runnable() {
            @Override
            public void run() {
                PlayerManager.getWitch().addPotionEffect( new PotionEffect( PotionEffectType.DAMAGE_RESISTANCE,
                        Integer.MAX_VALUE,
                        2 ) );
                PlayerManager.getHunter().addPotionEffect( new PotionEffect( PotionEffectType.DAMAGE_RESISTANCE,
                        Integer.MAX_VALUE,
                        1 ) );
                PlayerManager.getWitch().addPotionEffect( new PotionEffect( PotionEffectType.REGENERATION,
                        Integer.MAX_VALUE,
                        0 ) );
            }
        }, 40L );
    }

    public static void runRestart(Player winner) {
        if(PlayerManager.getHunter() == winner )
            WitchHunter.sendMessage( "Die ? wurde vom ? gefangen!", "Witch", "Hunter" );
        WitchHunter.sendMessage( "Der Spieler ? hat das Spiel ? gewonnen!", winner.getDisplayName(), "WitchHunter" );
        for( Player player : Bukkit.getOnlinePlayers() ) {
            try {
                User user = User.getUser( player.getUniqueId() );
                if(player == winner)
                    user.setRecoveredGames( user.getRecoveredGames() + 1 );
                user.setPlayedGames( user.getPlayedGames() + 1 );
                WitchHunter.getInstance().getUserDAO().update( user );
            } catch ( ExecutionException e ) {
                e.printStackTrace();
            }
        }
        WitchHunter.setCurrentGameState( GameState.RESTARTING );
        RestartCountdown.begin();
    }
}
