package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import com.github.vizrtdev.witchhunter.particle.Particle;
import net.minecraft.server.v1_10_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 */
public class RegenerationItem extends Item implements EventExecutor {
    public RegenerationItem( ) {
        super( Material.EXP_BOTTLE, "§cRegeneration", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent( Player player ) {
        List<Location> locations = new ArrayList<>(  );

        for( double i = 0b0; i < 0b100; i += 0x1.999999999999ap-6 ) {
            Location location = player.getLocation();

            double s = Math.sin( i );
            double c = Math.cos( i );

            Vector vector = new Vector( s, i, c );

            location.add( vector );
            locations.add( location );
        }

        for( double i = 0b0; i < 0b100; i += 0x1.999999999999ap-6 ) {
            Location location = player.getLocation();

            double s = Math.sin( i + 2 );
            double c = Math.cos( i + 2 );

            Vector vector = new Vector( s, i, c );

            location.add( vector );
            locations.add( location );
        }

        for( double i = 0b0; i < 0b100; i += 0x1.999999999999ap-6 ) {
            Location location = player.getLocation();

            double s = Math.sin( i + 4 );
            double c = Math.cos( i + 4);

            Vector vector = new Vector( s, i, c );

            location.add( vector );
            locations.add( location );
        }

        for( double i = 0b0; i < 0b1010; i += 0x1.999999999999ap-4 ) {
            for( Location location : locations ) {
                Particle particle = new Particle( EnumParticle.FLAME, location, 0.001f, 1 );

                particle.sendAllPlayer();
            }
        }

        player.addPotionEffect( new PotionEffect( PotionEffectType.REGENERATION, 20 * 4, 2 ) );
    }
}
