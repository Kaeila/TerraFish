package de.kaeila.terrafish;

import org.bukkit.plugin.java.JavaPlugin;

public final class TerraFish extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("TerraFish plugin has been enabled");
        getServer().getPluginManager().registerEvents(new FishingListener(this), this);
        getServer().getPluginManager().registerEvents(new CrateListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
