package com.github.vizrtdev.witchhunter.countdowns;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.manager.PlayerManager;
import com.github.vizrtdev.witchhunter.util.Countdown;
import com.github.vizrtdev.witchhunter.util.GameUtil;
import com.github.vizrtdev.witchhunter.util.NumberToText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyCountdown extends Countdown {

    public LobbyCountdown( ) {
        super( 60 );
    }

    public void tick( int count ) {
        if( WitchHunter.getCurrentGameState() != GameState.STARTING) {
            WitchHunter.setCurrentGameState( GameState.LOBBY );
            reset();
            return;
        }
        switch ( count ) {
            case 0b111100:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b11110:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b1111:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b1010:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b101:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b100:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b11:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b10:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunden!", NumberToText.intToText( count ) );
                break;
            case 0b1:
                WitchHunter.sendMessage( "Das Spiel startet in ? Sekunde!", NumberToText.intToText( count ) );
                WitchHunter.setCurrentGameState( GameState.INGAME );
                PlayerManager.randomGenerate();
                GameUtil.runIngame();
                break;
        }
        for( Player players : Bukkit.getOnlinePlayers() ) {
            players.setLevel( count );
        }
    }

    public static void begin() {
        WitchHunter.setCurrentGameState( GameState.STARTING );
        WitchHunter.setCurrentCountdown( new LobbyCountdown() );
        WitchHunter.getCurrentCountdown().start();
    }
}
