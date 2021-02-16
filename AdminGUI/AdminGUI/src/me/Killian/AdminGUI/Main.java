package me.Killian.AdminGUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public Inventory inv;
	
	@Override 
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		createInv();
	}
	
	@Override 
	public void onDisable() { }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("admingui") || label.equalsIgnoreCase("ag")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You have to be a player to do this!");
				return true;
				
			}
			Player player = (Player) sender;
			// Open up the GUI
			player.openInventory(inv);
			return true;
			
		}
		return false;
	}
	
	@EventHandler()
	public void onClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(inv))
			return;
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		
		if (event.getSlot() == 11) {
			// Creative Mode
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now in &lCreative Mode"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 13) {
			// Survival Mode
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are now in &lSurvival Mode"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 15) {
			// Spectator Mode
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now in &lSpectator Mode"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 29) {
			// Heal
			player.setHealth(20);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have been &lHealed"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 31) {
			// Food
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are now &lFull"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 33) {
			// Nothing
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTold you it does &lNothing"));
			player.closeInventory();
		}
		
		if (event.getSlot() == 49) {
			// Close GUI
			player.closeInventory();
		}
		
		return;
	}
	
	public void createInv() {
		inv = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Admin Console");
		
		ItemStack item = new ItemStack(Material.LIME_WOOL);
		ItemMeta meta = item.getItemMeta();
		
		// Creative Mode
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCreative Mode"));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to Change to this gamemode!"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		// Survival Mode
		item.setType(Material.RED_WOOL);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSurvival Mode"));
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		// Spectator Mode
		item.setType(Material.LIGHT_BLUE_WOOL);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lSpectator Mode"));
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		// Heal
		item.setType(Material.GOLDEN_APPLE);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lHeal"));
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		// Feed
		item.setType(Material.GOLDEN_CARROT);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lFeed"));
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		// Nothing
		item.setType(Material.BEDROCK);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lThis Does Nothing"));
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(33, item);
		
		// Close Button
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lClose Console"));
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(49, item);
		
	}
}
