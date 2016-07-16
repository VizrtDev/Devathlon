package com.github.vizrtdev.witchhunter;

import com.github.vizrtdev.witchhunter.database.DAOFactory;
import com.github.vizrtdev.witchhunter.database.UserDAO;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
/**
 * This class represents the class that extends {@Link JavaPlugin} and
 * add all Listeners and Commands to the current plugin manager
 *
 * @author Leon Merten & Finn
 */
public class WitchHunter extends JavaPlugin {

    @Getter private static WitchHunter instance;
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
    }

    @Override
    public void onDisable() {

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

}
