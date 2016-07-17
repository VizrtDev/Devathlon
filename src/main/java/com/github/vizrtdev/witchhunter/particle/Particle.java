package com.github.vizrtdev.witchhunter.particle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
@Setter
public class Particle {

    private EnumParticle particleType;
    private Location location;
    private float speed;
    private int amount;

    public void sendPlayer( Player player ) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                this.particleType,
                true,
                (float) this.location.getX(),
                (float) this.location.getY(),
                (float) this.location.getZ(),
                0,
                0,
                0,
                speed,
                amount,
                0
        );
        (( CraftPlayer )player).getHandle().playerConnection.sendPacket( packet );
    }
    public void sendAllPlayer( ) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                this.particleType,
                true,
                (float) this.location.getX(),
                (float) this.location.getY(),
                (float) this.location.getZ(),
                0,
                0,
                0,
                speed,
                amount,
                0
        );
        for( Player players : Bukkit.getOnlinePlayers() )
            (( CraftPlayer )players).getHandle().playerConnection.sendPacket( packet );
    }

}
