package com.github.vizrtdev.witchhunter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
@AllArgsConstructor
public class Map {

    private Location lobby;
    private Location spawn1;
    private Location spawn2;
}
