package com.aguiar.utilsui;

import com.aguiar.utilsui.commands.UtilsGUI;
import com.aguiar.utilsui.events.UtilsMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UtilsUI extends JavaPlugin {

  @Override
  public void onEnable() {
    getCommand("utils").setExecutor(new UtilsGUI());

    Bukkit.getPluginManager().registerEvents(new UtilsMenuListener(this), this);

    Bukkit.getConsoleSender().sendMessage("[UTILS-UI] Plugin enabled");
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage("[UTILS-UI] Plugin disabled");
  }
}
