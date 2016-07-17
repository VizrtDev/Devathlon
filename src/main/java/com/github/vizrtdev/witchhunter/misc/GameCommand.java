package com.github.vizrtdev.witchhunter.misc;

import com.github.vizrtdev.witchhunter.WitchHunter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    @Getter private String command;
    @Getter private boolean consoleable;
    @Getter @Setter private com.github.vizrtdev.witchhunter.interfaces.CommandExecutor commandExecutor;

    public GameCommand( String command,
                        boolean consoleable,
                        com.github.vizrtdev.witchhunter.interfaces.CommandExecutor commandExecutor) {
        this.command = command;
        this.consoleable = consoleable;
        this.commandExecutor = commandExecutor;
        WitchHunter.getInstance().getCommand( command ).setExecutor( this );
    }

    public GameCommand( String command,
                        com.github.vizrtdev.witchhunter.interfaces.CommandExecutor commandExecutor) {
        this.command = command;
        this.consoleable = false;
        this.commandExecutor = commandExecutor;
        WitchHunter.getInstance().getCommand( command ).setExecutor( this );
    }

    @Override
    public boolean onCommand( CommandSender commandSender, Command cmd, String s, String[] strings ) {
        if(s.equalsIgnoreCase( command )) {
            if(!consoleable) {
                if(!(commandSender instanceof Player))
                    return false;
                Player player = (Player) commandSender;
                commandExecutor.execute( player );
            }
            return false;
        }
        return false;
    }
}
