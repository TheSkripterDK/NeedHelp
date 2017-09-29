package me.danilkp1234.tennis.commands;

import me.danilkp1234.tennis.Tennis;
import me.danilkp1234.tennis.object.Game;
import me.danilkp1234.tennis.object.GamePlayer;
import me.danilkp1234.tennis.utility.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand extends SubCommand {
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 0) {
                player.sendMessage(ChatUtil.format("&9Skywars &7>> &c/skywars join <game name>"));
            } else {
                for (Game game : Tennis.getInstance().getGames()) {
                    for (GamePlayer gamePlayer : game.getPlayers()) {
                        if (gamePlayer.isTeamClass()) {
                            if (gamePlayer.getTeam().isPlayer(player)) {
                                player.sendMessage(ChatUtil.format("&9Skywars &7>> &cYou're in a game."));
                                return;
                            }
                        } else {
                            if (gamePlayer.getPlayer() == player) {
                                player.sendMessage(ChatUtil.format("&9Skywars &7>> &cYou're in a game."));
                                return;
                            }
                        }
                    }
                }

                Game game = Tennis.getInstance().getGame(args[0]);
                if (game == null) {
                    player.sendMessage(ChatUtil.format("&9Skywars &7>> &cThat game doesn't exist."));
                    return;
                }

                game.joinGame(new GamePlayer(player));
            }
        } else {
            commandSender.sendMessage(ChatUtil.format("&9Skywars &7>> &cYou're not a player!"));
        }
    }
}
