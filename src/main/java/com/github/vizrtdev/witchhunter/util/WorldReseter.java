package com.github.vizrtdev.witchhunter.util;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

public class WorldReseter {

    public static void resetWorld(File backup, File toReset, String worldname) {

        for(Player all : Bukkit.getOnlinePlayers()) {
            all.kickPlayer( "" );
        }

        Bukkit.getServer().unloadWorld(worldname, true);

        try {
            FileUtils.deleteDirectory(toReset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!toReset.exists()) {
            try {
                FileUtils.copyDirectory(backup, toReset);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        World w = Bukkit.createWorld(new WorldCreator(worldname));
    }

}
