package pw.hysteria.lobby.htlobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;
import pw.hysteria.lobby.htlobby.HTLobby;

import java.util.Objects;

public class SettingsListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(HTLobby.getInstance().getState("drop")){
            if(!e.getPlayer().hasPermission(Objects.requireNonNull(HTLobby.getInstance().getConfig().getString("permission")))){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if(HTLobby.getInstance().getState("pickup")){
            if(!e.getPlayer().hasPermission(Objects.requireNonNull(HTLobby.getInstance().getConfig().getString("permission")))){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e){
        if(HTLobby.getInstance().getState("weather")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeather(TimeSkipEvent e){
        if(HTLobby.getInstance().getState("day")){
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e){
        if(HTLobby.getInstance().getState("chat")){
            e.setCancelled(true);
        }
    }

}
