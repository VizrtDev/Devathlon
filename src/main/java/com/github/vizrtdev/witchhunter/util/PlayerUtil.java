package com.github.vizrtdev.witchhunter.util;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashSet;

public class PlayerUtil {

    public static Block staredAtBlock(Player player) {
        return player.getTargetBlock( ( HashSet<Byte> ) null, 100 );
    }

}
