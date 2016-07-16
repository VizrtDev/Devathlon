package com.github.vizrtdev.witchhunter.misc;

import com.github.vizrtdev.witchhunter.WitchHunter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class GameListener implements Listener {

    public GameListener() {
        WitchHunter witchHunter = WitchHunter.getInstance();
        Bukkit.getPluginManager().registerEvents( this, witchHunter );
    }

}
