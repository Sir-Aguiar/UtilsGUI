package com.aguiar.utilsui.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class DisplayScoreboard implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    String scoreboardTitle = String.format("%s%s%s", ChatColor.GREEN, ChatColor.BOLD, player.getName().toUpperCase());

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("player_scoreboard", "dummy");

    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(scoreboardTitle);


    player.setScoreboard(scoreboard);
  }
}
