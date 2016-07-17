package com.github.vizrtdev.witchhunter.util;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.particle.Particle;
import net.minecraft.server.v1_10_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import java.util.Random;
public class Boarder {

    static Integer size = 24;

    private static int random(Integer d, Integer x) {
        Random random = new Random(  );
        return random.nextInt(x - d + 1)+d;
    }

    public static void border() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for( Player p : Bukkit.getOnlinePlayers() ) {
                    if(p.getLocation().distance( new Location( Bukkit.getWorld( "world" ), 1, 6, 0 )) >= size) {
                        Vector plV = p.getLocation().toVector();
                        Vector spV = new Location( Bukkit.getWorld( "world" ), 1, 6, 0 ).toVector();
                        Vector v = spV.clone().subtract( plV ).multiply( 1.0 / spV.distance( plV ) ).setY( 0.5 );
                        p.setVelocity( v );
                        p.getWorld().playSound( p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1F, 0.8F );
                    }
                }
            }
        }.runTaskTimer( WitchHunter.getInstance(), 0L, 1L );
        new BukkitRunnable() {

            @Override
            public void run() {
                for( Player p : Bukkit.getOnlinePlayers() ) {
                    if(p.getLocation().distance( new Location( Bukkit.getWorld( "world" ), 1, 6, 0 )) >= size - 15) {
                        Location min = p.getLocation().add( -10.0, -10.0, -10.0 );
                        Location max = p.getLocation().add( 10.0, 10.0, 10.0 );
                        for( int x = min.getBlockX(); x < max.getBlockX(); x++) {
                            for( int y = min.getBlockY(); y < max.getBlockY(); y++) {
                                for( int z = min.getBlockZ(); z < max.getBlockZ(); z++) {
                                    Location location = new Location( Bukkit.getWorld( "world" ), x, y, z );
                                    if(location.distance(
                                            new Location( Bukkit.getWorld( "world" ), 1, 6, 0 )) > size &&
                                            location.distance(
                                                    new Location( Bukkit.getWorld( "world" ), 1, 6, 0 )) < size + 1) {
                                            if(random( 0, 20 ) == 0) {
                                                Particle particle = new Particle( EnumParticle.SPELL_WITCH,
                                                        location,
                                                        0.001f,
                                                        1
                                                );
                                                particle.sendAllPlayer();
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer( WitchHunter.getInstance(), 0L, 15L );
    }

}
