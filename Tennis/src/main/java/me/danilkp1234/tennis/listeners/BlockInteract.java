package me.danilkp1234.tennis.listeners;

import me.danilkp1234.tennis.Tennis;
import me.danilkp1234.tennis.object.Game;
import me.danilkp1234.tennis.object.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
public class BlockInteract implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        handle(event, player);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        handle(event, player);
    }

    private void handle(Cancellable event, Player player) {
        Game game = Tennis.getInstance().getGame(player);
        if (game != null) {
            if (game.isState(Game.GameState.LOBBY) || game.isState(Game.GameState.PREPARATION) || game.isState(Game.GameState.ENDING) || game.isState(Game.GameState.STARTING)) {
                event.setCancelled(true); // Cancel, game isn't active
                return;
            }

            GamePlayer gamePlayer = game.getGamePlayer(player);
            if (gamePlayer != null) {
                if (gamePlayer.isTeamClass()) {
                    if (gamePlayer.getTeam().isPlayer(player)) {
                        if (!game.getPlayers().contains(gamePlayer)) {
                            event.setCancelled(true);
                        }
                    }
                } else {
                    if (gamePlayer.getPlayer() == player) {
                        if (!game.getPlayers().contains(gamePlayer)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        } else {
            if (Tennis.getInstance().isSingleServerMode()) {
                event.setCancelled(true);
            }
        }
    }

}
