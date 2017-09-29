package me.danilkp1234.tennis.commands;

import me.danilkp1234.tennis.Tennis;
import me.danilkp1234.tennis.object.Game;
import me.danilkp1234.tennis.utility.ChatUtil;
import org.bukkit.command.CommandSender;
public class ListCommand extends SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        for (Game game : Tennis.getInstance().getGames()) {
            sender.sendMessage(ChatUtil.format("&9Skywars &7>> &f" + game.getDisplayName() + " - " + game.getPlayers().size() + " (alive) players"));
        }
    }
}
