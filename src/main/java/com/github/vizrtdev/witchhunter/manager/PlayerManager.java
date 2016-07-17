package com.github.vizrtdev.witchhunter.manager;

import com.github.vizrtdev.witchhunter.util.ListUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PlayerManager {
    @Getter private static Player hunter;
    @Getter private static Player witch;

    public static void randomGenerate() {
        List<Player> players = new ArrayList<>(  );
        for ( Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator(); iterator.hasNext(); ) {
            Player player = iterator.next();
            players.add( player );
        }

        hunter = ListUtil.getRandomItem( players );
        players.remove( hunter );
        witch = ListUtil.getRandomItem( players );

        hunter.sendMessage( "§aDu bist der Hunter!" );
        witch.sendMessage( "§aDu bist die Witch!" );
    }
}
