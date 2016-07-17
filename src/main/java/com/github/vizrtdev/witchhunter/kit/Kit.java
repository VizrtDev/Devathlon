package com.github.vizrtdev.witchhunter.kit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
@Setter
public class Kit {

    private Item firstItem;
    private Item secondItem;
    private Item thirdItem;

    public void run( Player player ) {
        player.getInventory().addItem( firstItem.toItemStack() );
        player.getInventory().addItem( secondItem.toItemStack() );
        player.getInventory().addItem( thirdItem.toItemStack() );
    }
}
