package com.aguiar.utilsui.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class UtilsGUI implements CommandExecutor {


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    Player player = (Player) sender;

    String inventoryTitle = String.format("%s%sUtils GUI", ChatColor.DARK_PURPLE, ChatColor.BOLD);
    Inventory inventory = Bukkit.createInventory(player, 45, inventoryTitle);

    inventory.setItem(20, randomTeleportElement());
    inventory.setItem(22, boostItem());
    inventory.setItem(24, easyWayOutElement());
    decorateInventory(inventory);

    player.openInventory(inventory);

    return true;
  }

  private ItemStack randomTeleportElement() {
    ItemStack randomTeleport = new ItemStack(Material.ENDER_PEARL);
    ItemMeta itemMeta = randomTeleport.getItemMeta();

    // IntelliJ for some reason keeps warning if no assert is made
    assert itemMeta != null;

    itemMeta.setDisplayName(String.format("%s%sRandom Teleport", ChatColor.ITALIC, ChatColor.AQUA));
    itemMeta.setLore(Arrays.asList("Random teleports to a safe location", "We find you a safe location to teleport via algorithms"));

    randomTeleport.setItemMeta(itemMeta);

    return randomTeleport;
  }

  private ItemStack boostItem() {
    ItemStack boostItem = new ItemStack(Material.ENCHANTING_TABLE);
    ItemMeta itemMeta = boostItem.getItemMeta();

    // IntelliJ for some reason keeps warning if no assert is made
    assert itemMeta != null;

    itemMeta.setDisplayName(String.format("%s%sItem Boost", ChatColor.ITALIC, ChatColor.AQUA));
    itemMeta.setLore(Collections.singletonList(ChatColor.LIGHT_PURPLE + "Add secret properties to the item in your hand"));

    boostItem.setItemMeta(itemMeta);
    return boostItem;
  }

  private ItemStack easyWayOutElement() {
    ItemStack easyWayOut = new ItemStack(Material.DIAMOND_SHOVEL);
    ItemMeta itemMeta = easyWayOut.getItemMeta();

    // IntelliJ for some reason keeps warning if no assert is made
    assert itemMeta != null;

    itemMeta.setDisplayName(String.format("%s%sEasy Way Out", ChatColor.ITALIC, ChatColor.AQUA));
    itemMeta.setLore(Collections.singletonList(ChatColor.RED + "Same as run /kill"));

    easyWayOut.setItemMeta(itemMeta);
    return easyWayOut;
  }

  private void decorateInventory(Inventory inventory) {
    ItemStack inventoryWrap = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
    Integer[] wrappedSlots = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};

    for (Integer slot : wrappedSlots) {
      inventory.setItem(slot, inventoryWrap);
    }
  }

}
