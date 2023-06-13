package me.elordenador.basicbans;

import org.bukkit.plugin.java.JavaPlugin;

public class Init extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getLogger().info("Loading...");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Turning off...");
    }
}