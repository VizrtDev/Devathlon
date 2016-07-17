package com.github.vizrtdev.witchhunter.kit;

import com.github.vizrtdev.witchhunter.kit.kits.Hunter;
import com.github.vizrtdev.witchhunter.kit.kits.Witch;
import com.github.vizrtdev.witchhunter.manager.PlayerManager;
import lombok.Getter;

public class KitManager {

    @Getter private static Hunter hunter;
    @Getter private static Witch witch;

    public static void run() {
        witch = new Witch();
        witch.run( PlayerManager.getWitch() );
        hunter = new Hunter();
        hunter.run( PlayerManager.getHunter() );
    }

}
