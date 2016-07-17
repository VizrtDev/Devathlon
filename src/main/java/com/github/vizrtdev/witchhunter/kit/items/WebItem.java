package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import com.github.vizrtdev.witchhunter.util.GameUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Leon
 */
public class WebItem extends Item implements EventExecutor {
    public WebItem(  ) {
        super( Material.WEB, "§4Cobweb!", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent( final Player player ) {
        final org.bukkit.entity.Item item = player.getWorld().dropItemNaturally( player.getEyeLocation(),
                player.getItemInHand() );
        item.setVelocity( player.getLocation().getDirection().multiply( 1.0D ) );

        new BukkitRunnable() {

            @Override
            public void run() {

                if(item.isOnGround()) {
                    item.remove();
                } else {
                    for(Player a : Bukkit.getOnlinePlayers()) {
                        if(a.getLocation().distance( item.getLocation() ) <2 ) {
                            if(a != player ) {
                                GameUtil.runRestart(player);
                                item.remove();
                                cancel();
                            }
                        }
                    }
                }
            }
        }.runTaskTimer( WitchHunter.getInstance(), 1, 1 );
    }
}
