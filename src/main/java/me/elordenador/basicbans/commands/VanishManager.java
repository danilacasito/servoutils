package me.elordenador.basicbans.commands;

import org.bukkit.entity.Player;
import java.util.HashMap;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;


public class VanishManager implements CommandExecutor {
    private HashMap<Player, Boolean> vanishMap;
    public VanishManager() {
        vanishMap = new HashMap<>();
    }

    public void toggleVanish(Player player) {
        boolean isInVanish = vanishMap.getOrDefault(player, false);
        
        if (isInVanish) {
            // Si el jugador está en vanish, se desactiva
            vanishMap.put(player, false);
            showPlayerToAll(player);
            player.sendMessage("You are no longer vanished!");
        } else {
            // Si el jugador no está en vanish, se activa
            vanishMap.put(player, true);
            hidePlayerFromAll(player);
            player.sendMessage("You are vanished!");
        }
    }
    private void hidePlayerFromAll(Player player) {
        for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            onlinePlayer.hidePlayer(player);
        }
    }

    private void showPlayerToAll(Player player) {
        for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            onlinePlayer.showPlayer(player);
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            
        } else {
            sender.sendMessage("Only players can run this");
            return false;
        }
        Player player = (Player) sender;
        this.toggleVanish(player);
        return true;
    }

}