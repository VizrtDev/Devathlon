package com.github.vizrtdev.witchhunter.kit.items;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.github.vizrtdev.witchhunter.interfaces.EventExecutor;
import com.github.vizrtdev.witchhunter.kit.Item;
import com.github.vizrtdev.witchhunter.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 */
public class WallItem extends Item implements EventExecutor {
    public WallItem() {
        super( Material.DIRT, "§eWall", null );
        setEventExecutor( this );
    }

    @Override
    public void doEvent( Player player ) {
        generateWall( player );
    }

    public void generateWall(Player player) {
        final List<BlockState> blockstates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(+ 2, + i, 0).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            //Location location = new Location(player.getWorld(), player.getLocation().getBlockX() + 2, player.getLocation().getBlockY() + i, player.getLocation().getBlockZ() + 1);
            Block block = player.getLocation().add(+ 2, + i, + 1).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(+ 2, i, - 1).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(- 2, + i, 0).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(- 2, + i, + 1).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(- 2, + i, - 1).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(0, + i, + 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(+ 1, + i, + 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(- 1, + i, + 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(0, + i, - 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(+ 1, + i, - 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            Block block = player.getLocation().add(- 1, i, - 2).getBlock();
            if(block.getType() == Material.AIR) {
                blockstates.add(block.getState());
                block.setType(Material.STONE);
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 1);
            }
        }

        new BukkitRunnable() {
            public void run() {
                for (BlockState state : blockstates) {
                    state.getBlock().setType(Material.AIR);
                    state.getBlock().getWorld().playEffect(state.getLocation(), Effect.STEP_SOUND, 1);
                    state.update();
                    blockstates.remove(state.getBlock());
                }
            }
        }.runTaskLater(WitchHunter.getInstance(), 20L);

        new BukkitRunnable() {
            public void run() {
                blockstates.clear();
            }
        }.runTaskLater(WitchHunter.getInstance(), 21L);
    }
}
