package me.elordenador.basicbans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.elordenador.basicbans.commands.CommandBan;
import me.elordenador.basicbans.commands.VanishManager;

public class Init extends JavaPlugin {
    public Logger logger;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
             e.printStackTrace(); // Imprime el rastreo de la excepción para depuración
};
        this.logger = this.getLogger();
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            System.out.println("config.yml doesn't exist, Creating one for you");

            saveResource("config.yml", false);
        }
        configFile = new File(this.getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
        ConfigHelper helper = new ConfigHelper(this.config, this.getDataFolder());
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(helper.JDBCString);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos SQLite: " + e.getMessage());
            this.getPluginLoader().disablePlugin(this);
        }
        DatabaseHelper dhelper = new DatabaseHelper(connection);
        if (!dhelper.createTables()) {
            System.err.println("There was an error while creating tables, contact developer");
            this.getPluginLoader().disablePlugin(this);
        }

        this.getCommand("ban").setExecutor(new CommandBan(connection));
        this.getCommand("vanish").setExecutor(new VanishManager());
        
    }

    @Override
    public void onDisable() {
        this.logger.info("Turning off...");
    }
}