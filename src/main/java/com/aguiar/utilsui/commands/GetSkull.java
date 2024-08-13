package com.aguiar.utilsui.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class GetSkull implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    Player player = (Player) sender;

    ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();

    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "PlayerName");
    gameProfile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQ2NDQ5MzczNjgyMzgxYTY1Y2FlNjVhMjI1M2Q4YjM2YjI5Mzc3NjQxMmM1ZGY4ZGVhNGQ5NjQzOTNhZjdhIn19fQ=="));

    Field field;

    try {
      field = playerHeadMeta.getClass().getDeclaredField("profile");
      field.setAccessible(true);
      field.set(playerHeadMeta, gameProfile);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
      return false;
    }

    playerHead.setItemMeta(playerHeadMeta);

    player.getInventory().addItem(playerHead);

    return true;
  }
}
