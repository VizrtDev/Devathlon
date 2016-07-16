package com.github.vizrtdev.witchhunter;

import com.github.vizrtdev.witchhunter.database.DAOFactory;
import com.github.vizrtdev.witchhunter.database.UserDAO;
import com.github.vizrtdev.witchhunter.database.model.User;
import com.github.vizrtdev.witchhunter.enums.GameState;
import com.github.vizrtdev.witchhunter.listener.EntityDamageListener;
import com.github.vizrtdev.witchhunter.listener.FoodLevelChangeListener;
import com.github.vizrtdev.witchhunter.listener.PlayerJoinListener;
import com.github.vizrtdev.witchhunter.listener.PlayerQuitListener;
import com.github.vizrtdev.witchhunter.model.Map;
import com.github.vizrtdev.witchhunter.util.Countdown;
import com.github.vizrtdev.witchhunter.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
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

        currentMap = new Map( Bukkit.getWorld( "world" ).getSpawnLocation(),
                new Location( Bukkit.getWorld( "world" ), 0b1, 0b1, 0b1 ),
                new Location( Bukkit.getWorld( "world" ), 0b10, 0b10, 0b10 ));

        currentGameState = GameState.LOBBY;
        new PlayerJoinListener();
        new PlayerQuitListener();
        new PlayerJoinListener();
        new FoodLevelChangeListener();
        new EntityDamageListener();
    }

    @Override
    public void onDisable() {
        //TODO: do something
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
