package com.github.vizrtdev.witchhunter.countdowns;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.util.Countdown;
import com.github.vizrtdev.witchhunter.util.NumberToText;
import org.bukkit.Bukkit;

public class RestartCountdown extends Countdown{

    public RestartCountdown(  ) {
        super( 15 );
    }

    @Override
    public void tick( int count ) {
        switch ( count ) {
            case 15:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunden neu!", NumberToText.intToText( count ) );
                break;
            case 10:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunden neu!", NumberToText.intToText( count ) );
                break;
            case 5:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunden neu!", NumberToText.intToText( count ) );
                break;
            case 3:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunden neu!", NumberToText.intToText( count ) );
                break;
            case 2:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunden neu!", NumberToText.intToText( count ) );
                break;
            case 1:
                WitchHunter.sendMessage( "Der Server startet in ? Sekunde neu!", NumberToText.intToText( count ) );
                Bukkit.shutdown();
                break;
        }
    }

    public static void begin() {
        WitchHunter.setCurrentGameState( GameState.RESTARTING );
        WitchHunter.setCurrentCountdown( new RestartCountdown() );
        WitchHunter.getCurrentCountdown().start();
    }
}
