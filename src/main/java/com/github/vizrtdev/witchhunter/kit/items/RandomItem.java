package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RandomItem extends Item implements EventExecutor {
    public RandomItem(  ) {
        super( Material.REDSTONE, "§aRandomItem", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent(Player player) {
        player.sendMessage( "You did a Event!" );
    }
}
