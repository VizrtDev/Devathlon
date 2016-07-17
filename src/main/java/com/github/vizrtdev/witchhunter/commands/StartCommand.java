package com.github.vizrtdev.witchhunter.commands;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.interfaces.CommandExecutor;
import com.github.vizrtdev.witchhunter.misc.GameCommand;
import org.bukkit.entity.Player;

public class StartCommand extends GameCommand implements CommandExecutor {
    public StartCommand(  ) {
        super( "start", false, null );
        setCommandExecutor( this );
    }

    @Override
    public void execute( Player player ) {
        if(player.hasPermission( "youtube" ))
            if( WitchHunter.getCurrentGameState() == GameState.STARTING)
                WitchHunter.getCurrentCountdown().setInterval( 16 );
    }
}
