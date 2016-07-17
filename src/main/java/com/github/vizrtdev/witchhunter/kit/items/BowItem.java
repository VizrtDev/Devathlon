package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BowItem extends Item implements EventExecutor {
    public BowItem( ) {
        super( Material.BOW, "§1Bow", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent( Player player ) {
        if(player.getInventory().all( Material.ARROW ).isEmpty())
            player.getInventory().addItem( new ItemStack( Material.ARROW ) );
    }
}
