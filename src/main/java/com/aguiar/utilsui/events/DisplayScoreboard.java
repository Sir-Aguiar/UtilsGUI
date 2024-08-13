package com.aguiar.utilsui.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class DisplayScoreboard implements Listener {
  private final HashMap<UUID, Integer> blocksBroken = new HashMap<>();

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    this.blocksBroken.put(player.getUniqueId(), 0);
    String scoreboardTitle = String.format("%s%s%s", ChatColor.GREEN, ChatColor.BOLD, player.getName().toUpperCase());

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("player_scoreboard", "dummy");

    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(scoreboardTitle);

    Team blocksBroken = scoreboard.registerNewTeam("blockBroken");
    blocksBroken.addEntry(ChatColor.BOLD.toString());

    blocksBroken.setPrefix(String.format("%s%sBlocks broken: ", ChatColor.BOLD, ChatColor.AQUA));
    blocksBroken.setSuffix(ChatColor.YELLOW + this.blocksBroken.get(player.getUniqueId()).toString());
    objective.getScore(ChatColor.BOLD.toString()).setScore(1);

    player.setScoreboard(scoreboard);
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();

    // Integer amount = blocksBroken.get(player.getUniqueId(), );
  }

}
