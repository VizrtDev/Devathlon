package com.github.vizrtdev.witchhunter;

import com.github.vizrtdev.witchhunter.commands.StartCommand;
import com.github.vizrtdev.witchhunter.database.DAOFactory;
import com.github.vizrtdev.witchhunter.database.UserDAO;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.kit.Item;
import com.github.vizrtdev.witchhunter.kit.KitManager;
import com.github.vizrtdev.witchhunter.kit.items.RandomItem;
import com.github.vizrtdev.witchhunter.kit.items.TornadoItem;
import com.github.vizrtdev.witchhunter.kit.kits.Witch;
import com.github.vizrtdev.witchhunter.listener.EntityDamageListener;
import com.github.vizrtdev.witchhunter.listener.FoodLevelChangeListener;
import com.github.vizrtdev.witchhunter.listener.PlayerDeathListener;
import com.github.vizrtdev.witchhunter.listener.PlayerJoinListener;
import com.github.vizrtdev.witchhunter.listener.PlayerLoginListener;
import com.github.vizrtdev.witchhunter.listener.PlayerQuitListener;
import com.github.vizrtdev.witchhunter.listener.ServerListPingListener;
import com.github.vizrtdev.witchhunter.model.Map;
import com.github.vizrtdev.witchhunter.util.Boarder;
import com.github.vizrtdev.witchhunter.util.Countdown;
import com.github.vizrtdev.witchhunter.util.MessageUtil;
import com.github.vizrtdev.witchhunter.util.WorldReseter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents the class that extends {@Link JavaPlugin} and
 * add all Listeners and Commands to the current plugin manager
 *
 * @author Leon Merten & Finn
 */
public class WitchHunter extends JavaPlugin {

    @Getter private static WitchHunter instance;
    @Getter @Setter private static GameState currentGameState;
    @Getter @Setter private static Countdown currentCountdown;
    @Getter @Setter private static Map currentMap;
    @Getter public static Collection<User> userCollection = new HashSet<>();
    @Getter public static Collection<Player> playerCollection = new HashSet<>(  );
    @Getter private DAOFactory javabase;
    @Getter private UserDAO userDAO;


    @Override
    public void onEnable() {
        instance = this;

        loadConfiguration();
        this.javabase = DAOFactory.getInstance( "javabase.jdbc",
                getConfig().getString( "url" ),
                getConfig().getString( "driverClassName" ),
                getConfig().getString( "password" ),
                getConfig().getString( "user" )
        );
        this.userDAO = this.javabase.getUserDAO();

        currentMap = new Map( new Location( Bukkit.getWorld( "world" ), 0b1, 0b1010, 0b11111111111111111111111111101011 ),
                new Location( Bukkit.getWorld( "world" ), 0b11111111111111111111111111101011, 0b110, 0b1 ),
                new Location( Bukkit.getWorld( "world" ), 0b11000, 0b110, 0b0 ));

        currentGameState = GameState.LOBBY;
        new PlayerJoinListener();
        new PlayerQuitListener();
        new PlayerLoginListener();
        new FoodLevelChangeListener();
        new EntityDamageListener();
        new PlayerDeathListener();
        new ServerListPingListener();
        new StartCommand();

        Boarder.border();
    }

    @Override
    public void onDisable() {
        //reset the world
        WorldReseter.resetWorld( new File( "world_backup" ), new File( "world" ), "world" );
    }

    public void loadConfiguration() {
        getConfig().addDefaults( new HashMap<String, Object>(  ) {{
                put( "url", "jdbc:mysql://localhost:3306/beta" );
                put( "driverClassName", "com.mysql.jdbc.Driver" );
                put( "password", "" );
                put( "user", "root" );
            }}
        );
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static void sendMessage(String message, String ... params) {
        MessageUtil.sendMessage( message, getPlayerCollection(), params );
    }

}
