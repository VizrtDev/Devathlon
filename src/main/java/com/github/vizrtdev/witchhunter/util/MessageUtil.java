package com.github.vizrtdev.witchhunter.util;

import com.github.vizrtdev.witchhunter.database.model.User;
import org.bukkit.entity.Player;
import java.util.Collection;
import java.util.Iterator;

public class MessageUtil {
    public static void sendMessage( String message, Collection<Player> users, String ... params) {
        String result = message;
        int count = 1;
        for( String param : params )
            result = result.replaceFirst( "\\?", "§e" + param + "§7" );
        result = "§f[§aWitchHunter§f] §7" + result;
        for ( Iterator<Player> iterator = users.iterator(); iterator.hasNext(); ) {
            Player user = iterator.next();
            user.sendMessage( result );
        }
    }
}
