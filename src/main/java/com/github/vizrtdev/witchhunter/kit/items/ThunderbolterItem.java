package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import com.github.vizrtdev.witchhunter.util.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * ${description}
 *
 * @author Leon
 *
 * @since ${version}
 */
public class ThunderbolterItem extends Item implements EventExecutor {

    public ThunderbolterItem(  ) {
        super( Material.BLAZE_ROD, "§5Thunderer", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent( Player player ) {
        player.getWorld().strikeLightning( PlayerUtil.staredAtBlock( player ).getLocation() );
    }
}
