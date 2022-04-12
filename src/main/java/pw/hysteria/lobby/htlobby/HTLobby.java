package pw.hysteria.lobby.htlobby;

import org.bukkit.plugin.java.JavaPlugin;
import pw.hysteria.lobby.htlobby.listener.SettingsListener;

public final class HTLobby extends JavaPlugin {

    private static HTLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new SettingsListener(), this);
        getServer().getPluginManager().registerEvents(new NotPatchDetector(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HTLobby getInstance() {
        return instance;
    }

    public boolean getState(String setting){
        return getConfig().getBoolean("settings.cancel-" + setting);
    }

}
