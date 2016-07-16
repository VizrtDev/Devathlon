package com.github.vizrtdev.witchhunter.database.model;

import com.github.vizrtdev.witchhunter.WitchHunter;
import com.google.common.base.MoreObjects;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * This class represents the User model. This model class can be used thoroughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Leon Merten
 */
public class User implements Serializable {

    private static final long serialVersionUID = -7564176422209182791L;
    private static Map<UUID, User> users = new HashMap<>(  );

    @Getter @Setter private UUID id;
    @Getter @Setter private String name;
    @Getter @Setter private int playedGames;
    @Getter @Setter private int recoveredGames;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper( this )
                .add( "id", id )
                .add( "name", name )
                .add( "playedGames", playedGames )
                .add( "recoveredGames", recoveredGames )
                .toString();
    }

    public static User getUser( UUID id) throws ExecutionException {
        if(users.containsKey( id ))
            return users.get( id );
        else
            return WitchHunter.getInstance().getUserDAO().find( id.toString() );
    }

    @Nullable
    public Player toPlayer( ) {
        return Bukkit.getPlayer( id );
    }
}