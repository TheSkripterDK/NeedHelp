package me.danilkp1234.tennis.listeners;

import me.danilkp1234.tennis.Tennis;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        if (!Tennis.getInstance().isSingleServerMode()) {
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.setMaxHealth(20);
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(25);

            player.teleport(Tennis.getInstance().getLobbyPoint());
        }
    }

}
