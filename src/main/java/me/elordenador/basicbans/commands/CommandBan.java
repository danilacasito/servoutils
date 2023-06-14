package me.elordenador.basicbans.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;


public class CommandBan implements CommandExecutor {
    Connection conn = null;
    public CommandBan(Connection connection) {
        this.conn = connection;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Argumentos:
        // username (Public username)
        // Reason (all arguments)
        String modName = null;
        if (sender instanceof Player) {
            modName = Player.getName();
        } else {
            modName = "CONSOLE";
        }
        String player = args[0];
        String reason = String.join(" ", args, 1, args.length);      
        if (banPlayer(player, reason, modName)) {
            sender.sendMessage("The user "+username+" was banned from the server");
        } else {
            sender.sendMessage("We couldn't ban the player, see the console for more details");
        }
        return true;
    }

    private boolean banPlayer(String username, String reason, String modName) {
        try {
            String query = "INSERT INTO sanciones (Username, ModName, Time, Reason) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = this.conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, modName);
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.setString(4, reason);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}