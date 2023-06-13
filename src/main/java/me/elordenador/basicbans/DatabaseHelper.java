package me.elordenador.basicbans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    Connection conn = null;
    public DatabaseHelper(Connection conn) {
        this.conn = conn;
    }

    public Boolean createTables() {
        try {
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS sanciones (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Username TEXT," +
                    "ModName TEXT," +
                    "Time TIMESTAMP," +
                    "Expiry TIMESTAMP," +
                    "Reason TEXT" +
                    ")";
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            System.err.println("Error al crear o verificar la existencia de la tabla de sanciones: " + e.getMessage());
            return false;
        }
        return true;
    }
}