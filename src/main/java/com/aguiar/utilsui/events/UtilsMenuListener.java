package com.aguiar.utilsui.events;

import com.aguiar.utilsui.utils.RandomTP;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class UtilsMenuListener implements Listener {
  Plugin plugin;

  public UtilsMenuListener(Plugin plugin) {
    this.plugin = plugin;

  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {

    if (!isUtilsGUI(event.getView().getTitle()) || event.getCurrentItem() == null) {
      return;
    }

    Player player = (Player) event.getWhoClicked();

    switch (event.getRawSlot()) {
      case 20:
        handleRandomTp(player);
        break;
      case 22:
        boostItem(player);
        break;
      case 24:
        player.setHealth(1);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 2.0f, 1.0f);
        player.sendMessage(ChatColor.RED + "Don't do that mate!");
        break;
      default:
        event.setCancelled(true);
        return;
    }

    player.closeInventory();
  }

  private void handleRandomTp(Player player) {
    World playersWorld = player.getWorld();
    RandomTP randomTp = new RandomTP(playersWorld);
    playersWorld.playSound(player.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 2.0f, 1.0f);

    plugin.getServer().getScheduler().runTaskLater(plugin, player::closeInventory, 10);

    String message = String.format(
            "%s%sWe're looking for safe positions to teleport you, this might take a few seconds",
            ChatColor.BOLD, ChatColor.GREEN
    );
    player.sendMessage(message);

    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
      int[] coordinates = randomTp.getRandomCoordinates();

      plugin.getServer().getScheduler().runTask(plugin, () -> {
        player.teleport(new Location(playersWorld, coordinates[0], coordinates[1], coordinates[2]));
        playersWorld.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);
        playersWorld.spawnParticle(Particle.PORTAL, player.getLocation(), 15);
      });
    });
  }

  private boolean isUtilsGUI(String inventoryTitle) {
    String guiTitle = String.format("%s%sUtils GUI", ChatColor.DARK_PURPLE, ChatColor.BOLD);

    return ChatColor.translateAlternateColorCodes('&', inventoryTitle).equals(guiTitle);
  }

  private void boostItem(Player player) {
    ItemStack itemInHand = player.getInventory().getItemInMainHand();

    if (itemInHand.getType() == Material.AIR) {
      player.sendMessage(ChatColor.RED  + "You must hold an item in hand and open the GUI");
      return;
    }

    ItemMeta itemMeta = itemInHand.getItemMeta();
    AttributeModifier modifier = new AttributeModifier("generic.attack_damage", 25, AttributeModifier.Operation.ADD_NUMBER);

    assert itemMeta != null;

    itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
    itemInHand.setItemMeta(itemMeta);
    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.5f, 1.0f);

  }

}
