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

        if (sender instanceof Player) {
            System.out.println("IS Player");
        }
        
        return false;
    }

}