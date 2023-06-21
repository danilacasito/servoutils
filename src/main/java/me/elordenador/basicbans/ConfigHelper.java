package me.elordenador.basicbans;

import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
public class ConfigHelper {
    Boolean mySQLEnabled;
    String JDBCString;
    public ConfigHelper(FileConfiguration config, File dataFolder) {
        mySQLEnabled = config.getBoolean("MYSQL");
        if (mySQLEnabled) {
            this.JDBCString = config.getString("MYSQL_JDBC");
        } else {
            this.JDBCString = "jdbc:sqlite:"+dataFolder.getAbsolutePath()+"/"+config.getString("SQLITE_FILE");
        }
        // no
    }
}