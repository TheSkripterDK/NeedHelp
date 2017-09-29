package me.danilkp1234.tennis.listeners;

import me.danilkp1234.tennis.Tennis;
import me.danilkp1234.tennis.object.Game;
import me.danilkp1234.tennis.object.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
public class FoodLevel implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            Game game = Tennis.getInstance().getGame(player);
            if (game != null && game.getGamePlayer(player) != null) {
                GamePlayer gamePlayer = game.getGamePlayer(player);

                if (!(game.isState(Game.GameState.ACTIVE) || game.isState(Game.GameState.DEATHMATCH))) {
                    if (gamePlayer.isTeamClass()) {
                        if (gamePlayer.getTeam().isPlayer(player)) {
                            event.setFoodLevel(25);
                            event.setCancelled(true);
                        }
                    } else {
                        if (gamePlayer.getPlayer() == player) {
                            event.setFoodLevel(25);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
