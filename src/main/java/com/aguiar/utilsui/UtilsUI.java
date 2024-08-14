package com.aguiar.utilsui;

import com.aguiar.utilsui.commands.GetSkull;
import com.aguiar.utilsui.commands.UtilsGUI;
import com.aguiar.utilsui.events.DisplayScoreboard;
import com.aguiar.utilsui.events.UtilsMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class UtilsUI extends JavaPlugin {

  @Override
  public void onEnable() {
    try {
      initiateConfigurationFiles("utils-ui.yml");
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    getCommand("utils").setExecutor(new UtilsGUI());
    getCommand("skull").setExecutor(new GetSkull());

    Bukkit.getPluginManager().registerEvents(new UtilsMenuListener(this), this);
    Bukkit.getPluginManager().registerEvents(new DisplayScoreboard(), this);

    Bukkit.getConsoleSender().sendMessage("[UTILS-UI] Plugin enabled");
  }

  private void initiateConfigurationFiles(String fileName) throws Exception {
    File dataFolder = getDataFolder();

    if (dataFolder.exists() && !dataFolder.isDirectory()) {
      File backupFile = new File(dataFolder.getParent(), dataFolder.getName() + "_backup");

      if (!dataFolder.renameTo(backupFile)) {
        dataFolder.delete();
      }
    }

    if (!dataFolder.exists()) {
      dataFolder.mkdirs();
    }

    File file = new File(dataFolder, fileName);

    if (!file.exists()) {
      file.createNewFile();
    }
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage("[UTILS-UI] Plugin disabled");
  }
}
