package com.github.vizrtdev.witchhunter.enums;

import lombok.Getter;

public enum  GameState {
    LOBBY(true, "§aLobby"),
    STARTING(false, "§eStarting"),
    INGAME(false, "§cIngame"),
    RESTARTING(false, "§cRestarting");

    @Getter private boolean joinable;
    @Getter private String motd;

    GameState(boolean joinable, String motd ) {
        this.joinable = joinable;
        this.motd = motd;
    }
}
