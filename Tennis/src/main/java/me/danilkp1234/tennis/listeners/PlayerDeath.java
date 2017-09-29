package me.danilkp1234.tennis.listeners;

import me.danilkp1234.tennis.Tennis;
import me.danilkp1234.tennis.object.Game;
import me.danilkp1234.tennis.object.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Game game = Tennis.getInstance().getGame(player);
        if (game != null && game.getGamePlayer(player) != null) {
            GamePlayer gamePlayer = game.getGamePlayer(player);

            if (gamePlayer.isTeamClass()) {
                if (gamePlayer.getTeam().isPlayer(player)) {
                    handle(event, game);
                }
            } else {
                if (gamePlayer.getPlayer() == player) {
                    handle(event, game);
                }
            }
        }
    }

    private void handle(PlayerDeathEvent event, Game game) {
        Player player = event.getEntity();

        if (!game.isState(Game.GameState.ACTIVE) && !game.isState(Game.GameState.DEATHMATCH)) {
            return;
        }

        event.setDeathMessage(null);
        game.activateSpectatorSettings(player);

        if (game.getPlayers().size() <= 1) {
            try {
                GamePlayer winner = game.getPlayers().get(0);
                if (winner.isTeamClass()) {
                    game.sendMessage("&a"); // Broadcast team win
                } else {
                    game.sendMessage("&a" + winner.getName() + " won the game!"); // Indiv win
                }

                game.setState(Game.GameState.ENDING);
            } catch (IndexOutOfBoundsException ignored) {}
        }
    }

}
