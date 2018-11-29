package de.gamelos.bedwars;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;

public class Shop implements Listener {
	
	public static void open(Player p){
		if(Main.teamname.containsKey(p)){
		Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//		
		inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
		inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
		inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
		inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
		inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
		inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
		inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
		inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
		inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//.........................................................................
		inv.setItem(13, createitem(Material.MAGMA_CREAM, ChatColor.YELLOW+"Zum alten Shop wechseln", null, 0, null, 0));
		p.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onklick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Shop")){
			if(e.getCurrentItem()!=null){
			if(e.getClickedInventory().getType() == InventoryType.CHEST){
				e.setCancelled(true);
			if(e.getCurrentItem().getType() == Material.GOLD_SWORD){
//	===============================================================================			
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
		//.........................................................................
				inv.setItem(11, createitemwithlore(Material.STICK, ChatColor.AQUA+"Knockback Stick", Enchantment.KNOCKBACK, 1, null, 0, ChatColor.DARK_RED+"7 Bronze"));
				inv.setItem(12, createitemwithlore(Material.WOOD_SWORD, ChatColor.AQUA+"Holzschwert I", Enchantment.DAMAGE_ALL, 1, null, 0, ChatColor.GRAY+"1 Eisen"));
				inv.setItem(13, createitemwithlore(Material.WOOD_SWORD, ChatColor.AQUA+"Holzschwert II", Enchantment.DAMAGE_ALL, 2, null, 0, ChatColor.GRAY+"3 Eisen"));	
				inv.setItem(14, createitemwithlore(Material.IRON_SWORD, ChatColor.AQUA+"Eisenschwert", Enchantment.DAMAGE_ALL, 2, Enchantment.KNOCKBACK, 1, ChatColor.GOLD+"5 Gold"));
				p.openInventory(inv);
//	===============================================================================
			}else if(e.getCurrentItem().getType() == Material.SANDSTONE){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
		//.........................................................................
				inv.setItem(11, createanzahlitemwithlore(Material.SANDSTONE, ChatColor.AQUA+"Sandstein", ChatColor.DARK_RED+"1 Bronze", 2));
				inv.setItem(12, createanzahlitemwithlore(Material.ENDER_STONE, ChatColor.YELLOW+"Endstein", ChatColor.DARK_RED+"10 Bronze", 1));
				inv.setItem(13, createanzahlitemwithlore(Material.IRON_BLOCK, ChatColor.GRAY+"Eisenblock", ChatColor.GRAY+"5 Eisen", 1));
				inv.setItem(14, createanzahlitemwithlore(Material.GLASS, ChatColor.AQUA+"Glas", ChatColor.DARK_RED+"10 Bronze", 1));
				inv.setItem(15, createanzahlitemwithlore(Material.PACKED_ICE, ChatColor.AQUA+"Eis", ChatColor.DARK_RED+"5 Bronze", 1));
//				===========================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
		//____________________________________________________________________________________________________
				inv.setItem(11, createrüstung(Material.LEATHER_BOOTS, cololorchanger(Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe"))), ChatColor.BLUE+"Lederschuhe", Enchantment.PROTECTION_ENVIRONMENTAL, ChatColor.DARK_RED+"1 Bronze"));
				inv.setItem(12, createrüstung(Material.LEATHER_HELMET, cololorchanger(Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe"))), ChatColor.BLUE+"Lederhelm", Enchantment.PROTECTION_ENVIRONMENTAL, ChatColor.DARK_RED+"1 Bronze"));
				inv.setItem(13, createrüstung(Material.LEATHER_LEGGINGS, cololorchanger(Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe"))), ChatColor.BLUE+"Lederhose", Enchantment.PROTECTION_ENVIRONMENTAL, ChatColor.DARK_RED+"3 Bronze"));
//				_______________________________________________________________________________________________		
				inv.setItem(14, createitemwithlore(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA+"Kettenbrust I", Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, ChatColor.GRAY+"1 Eisen"));
				inv.setItem(15, createitemwithlore(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA+"Kettenbrust II", Enchantment.PROTECTION_ENVIRONMENTAL, 2, null, 0, ChatColor.GRAY+"3 Eisen"));
				inv.setItem(16, createitemwithlore(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA+"Kettenbrust III", Enchantment.PROTECTION_ENVIRONMENTAL, 3, null, 0, ChatColor.GRAY+"7 Eisen"));
				inv.setItem(17, createitemwithlore(Material.GOLD_BOOTS, ChatColor.YELLOW+"Antiknockbackschuhe", Enchantment.PROTECTION_FALL, 1, null, 0, ChatColor.GOLD+"10 Gold"));
//				============================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.APPLE){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................			
				inv.setItem(11, createitemwithlore(Material.APPLE, ChatColor.RED+"Apfel", null, 0, null, 0, ChatColor.DARK_RED+"1 Bronze"));
				inv.setItem(12, createitemwithlore(Material.COOKED_BEEF, ChatColor.YELLOW+"Fleisch", null, 0, null, 0, ChatColor.DARK_RED+"2 Bronze"));
				inv.setItem(13, createitemwithlore(Material.CAKE, ChatColor.GRAY+"Kuchen", null, 0, null, 0, ChatColor.GRAY+"1 Eisen"));
//				...............................................................................			
//======================================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.STONE_PICKAXE){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................	
				inv.setItem(11, createitemwithlore(Material.WOOD_PICKAXE, ChatColor.DARK_RED+"Holzspitzhacke", Enchantment.DURABILITY, 1, null, 0, ChatColor.DARK_RED+"3 Bronze"));
				inv.setItem(12, createitemwithlore(Material.STONE_PICKAXE, ChatColor.GRAY+"Steinspitzhacke I", Enchantment.DURABILITY, 1, null, 0, ChatColor.GRAY+"1 Eisen"));
				inv.setItem(13, createitemwithlore(Material.STONE_PICKAXE, ChatColor.GRAY+"Steinspitzhacke II", Enchantment.DURABILITY, 1, Enchantment.DIG_SPEED, 1, ChatColor.GRAY+"7 Eisen"));
				inv.setItem(14, createitemwithlore(Material.IRON_PICKAXE, ChatColor.GOLD+"Eisenspitzhacke", Enchantment.DURABILITY, 1,  Enchantment.DIG_SPEED, 1, ChatColor.GOLD+"1 Gold"));
//				...............................................................................		
//				========================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.BOW){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................	
				inv.setItem(11, createitemwithlore(Material.BOW, ChatColor.YELLOW+"Bogen I", Enchantment.ARROW_INFINITE, 1, null, 0, ChatColor.GOLD+"3 Gold"));
				inv.setItem(12, createitemwithlore(Material.BOW, ChatColor.YELLOW+"Bogen II", Enchantment.ARROW_INFINITE, 1, Enchantment.ARROW_DAMAGE, 1, ChatColor.GOLD+"7 Gold"));
				inv.setItem(13, createitemwithlore2(Material.BOW, ChatColor.YELLOW+"Bogen III", Enchantment.ARROW_INFINITE, 1, Enchantment.ARROW_KNOCKBACK, 1,Enchantment.ARROW_DAMAGE, 1, ChatColor.GOLD+"13 Gold"));
				inv.setItem(14, createitemwithlore(Material.ARROW, ChatColor.YELLOW+"Pfeil", null, 0,  null, 0, ChatColor.GOLD+"1 Gold"));
//				...............................................................................		
//				========================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.EMERALD_BLOCK){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................	
				inv.setItem(12, createitemwithlore(Material.LAPIS_BLOCK, ChatColor.GRAY+"Starter - Pack", null, 1, null, 0, ChatColor.DARK_RED+"50 Bronze"));
				inv.setItem(13, createitemwithlore(Material.DIAMOND_BLOCK, ChatColor.GRAY+"Schwitzer - Pack", null, 1, null, 1, ChatColor.GRAY+"18 Eisen"));
				inv.setItem(14, createitemwithlore(Material.EMERALD_BLOCK, ChatColor.GRAY+"Bogen - Pack", null, 1, null, 1, ChatColor.GOLD+"5 Gold"));
//				...............................................................................		
//				========================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.GLASS_BOTTLE){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................	
				inv.setItem(11, createpotion(ChatColor.YELLOW+"Speed", (short)8194,ChatColor.GRAY+"11 Eisen"));
				inv.setItem(12, createpotion(ChatColor.YELLOW+"Heilung I", (short)8197,ChatColor.GRAY+"3 Eisen"));
				inv.setItem(13, createpotion(ChatColor.YELLOW+"Heilung II", (short)8229,ChatColor.GRAY+"7 Eisen"));
				inv.setItem(14, createpotion(ChatColor.YELLOW+"Stärke", (short)8201,ChatColor.GOLD+"7 Gold"));
//				...............................................................................		
//				========================================================================
				p.openInventory(inv);
			}else if(e.getCurrentItem().getType() == Material.TNT){
				Inventory inv = Bukkit.createInventory(null, 9*2, ChatColor.YELLOW+"Shop");
//				
				inv.setItem(2, createitem(Material.GOLD_SWORD, ChatColor.AQUA+"Waffen", null, 0, null, 0));
				inv.setItem(0, createitem(Material.SANDSTONE, ChatColor.YELLOW+"Blöcke", null, 0, null, 0));
				inv.setItem(3, createitem(Material.APPLE, ChatColor.RED+"Essen", null, 0, null, 0));
				inv.setItem(1, createitem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY+"Rüstung", null, 0, null, 0));
				inv.setItem(4, createitem(Material.STONE_PICKAXE, ChatColor.GREEN+"Spitzhacken", null, 0, null, 0));
				inv.setItem(5, createitem(Material.BOW, ChatColor.DARK_PURPLE+"Bögen", null, 0, null, 0));
				inv.setItem(6, createitem(Material.GLASS_BOTTLE, ChatColor.GOLD+"Tränke", null, 0, null, 0));
				inv.setItem(7, createitem(Material.TNT, ChatColor.DARK_PURPLE+"Spezial", null, 0, null, 0));
				inv.setItem(8, createitem(Material.EMERALD_BLOCK, ChatColor.DARK_PURPLE+"Packs", null, 0, null, 0));
//				...............................................................................	
				inv.setItem(11, createitemwithlore(Material.WEB, ChatColor.YELLOW+"Spinnenweben", null, 0, null, 0, ChatColor.DARK_RED+"10 Bronze"));
				inv.setItem(12, createitemwithlore(Material.TNT, ChatColor.YELLOW+"TNT", null, 0, null, 0, ChatColor.GRAY+"5 Eisen"));
				inv.setItem(13, createitemwithlore(Material.LADDER, ChatColor.YELLOW+"Leiter", null, 0, null, 0, ChatColor.DARK_RED+"10 Bronze"));
				inv.setItem(14, createitemwithlore(Material.SULPHUR, ChatColor.YELLOW+"Backteleporter", null, 0, null, 0, ChatColor.GRAY+"10 Eisen"));
				inv.setItem(15, createitemwithlore(Material.CHEST, ChatColor.GRAY+"Kiste", null, 1, null, 0, ChatColor.GRAY+"3 Eisen"));
				inv.setItem(16, createitemwithlore(Material.ENDER_CHEST, ChatColor.GRAY+"Enderchest", null, 1, null, 1, ChatColor.GOLD+"2 Gold"));
				inv.setItem(16, createitemwithlore(Material.BLAZE_ROD, ChatColor.GRAY+"Rettungsplattform", null, 1, null, 1, ChatColor.GOLD+"3 Gold"));
				inv.setItem(16, createitemwithlore(Material.ENDER_PEARL, ChatColor.YELLOW+"Enderperle", null, 1, null, 1, ChatColor.GOLD+"7 Gold"));
//				...............................................................................		
//				========================================================================
				p.openInventory(inv);
			}
			}
		}
		}
	}
//	__________________________________________________________________________________________________
	
	@EventHandler
	public void onklick1(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Shop")){
			if(e.getClickedInventory().getType() == InventoryType.CHEST){
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				if(!e.getCurrentItem().getType().equals(Material.AIR)){
					if(e.getCurrentItem().getItemMeta().getLore() !=null){
						String l = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(1));
						int i = 0;
						Material material = null;
						if(l.contains("Bronze")){
							material = Material.CLAY_BRICK;
							i = Integer.parseInt(l.substring(0, l.length()-7));
						}else if(l.contains("Eisen")){
							material = Material.IRON_INGOT;
							i = Integer.parseInt(l.substring(0, l.length()-6));
						}else if(l.contains("Gold")){
							material = Material.GOLD_INGOT;
							i = Integer.parseInt(l.substring(0, l.length()-5));
						}
						if(material!=null){
			if(e.isShiftClick()&&e.getCurrentItem().getType()!=Material.STICK&&e.getCurrentItem().getType()!=Material.WOOD_SWORD&&e.getCurrentItem().getType()!=Material.IRON_SWORD&&e.getCurrentItem().getType()!=Material.STONE_SWORD&&e.getCurrentItem().getType()!=Material.WOOD_PICKAXE&&e.getCurrentItem().getType()!=Material.STONE_PICKAXE&&e.getCurrentItem().getType()!=Material.IRON_PICKAXE&&e.getCurrentItem().getType()!=Material.LEATHER_BOOTS&&e.getCurrentItem().getType()!=Material.LEATHER_HELMET&&e.getCurrentItem().getType()!=Material.LEATHER_LEGGINGS&&e.getCurrentItem().getType()!=Material.CHAINMAIL_CHESTPLATE&&e.getCurrentItem().getType()!=Material.GOLD_BOOTS&&e.getCurrentItem().getType()!=Material.IRON_SWORD&&e.getCurrentItem().getType()!=Material.STONE_SWORD&&e.getCurrentItem().getType()!=Material.WOOD_PICKAXE&&e.getCurrentItem().getType()!=Material.STONE_PICKAXE&&e.getCurrentItem().getType()!=Material.IRON_PICKAXE&&e.getCurrentItem().getType()!=Material.LEATHER_BOOTS&&e.getCurrentItem().getType()!=Material.LEATHER_HELMET&&e.getCurrentItem().getType()!=Material.LEATHER_LEGGINGS&&e.getCurrentItem().getType()!=Material.CHAINMAIL_CHESTPLATE&&e.getCurrentItem().getType()!=Material.GOLD_BOOTS&&e.getCurrentItem().getType()!=Material.IRON_SWORD&&e.getCurrentItem().getType()!=Material.STONE_SWORD&&e.getCurrentItem().getType()!=Material.WOOD_PICKAXE&&e.getCurrentItem().getType()!=Material.STONE_PICKAXE&&e.getCurrentItem().getType()!=Material.IRON_PICKAXE&&e.getCurrentItem().getType()!=Material.LEATHER_BOOTS&&e.getCurrentItem().getType()!=Material.LEATHER_HELMET&&e.getCurrentItem().getType()!=Material.LEATHER_LEGGINGS&&e.getCurrentItem().getType()!=Material.CHAINMAIL_CHESTPLATE&&e.getCurrentItem().getType()!=Material.GOLD_BOOTS&&e.getCurrentItem().getType()!=Material.ARROW&&e.getCurrentItem().getType()!=Material.BOW){
				if(getitemammount(material, p) >= i){
//					
					int z = 64/i;
//				
				if(getitemammount(material, p) >= 64){
					
					int o = e.getCurrentItem().getAmount()*z;
					removeitem(z*i, material, p);
					ItemStack item = getitemwithoutdisplayname(e.getCurrentItem());
					int r = e.getCurrentItem().getAmount();
					ArrayList<String> lore = (ArrayList<String>) e.getCurrentItem().getItemMeta().getLore();
					ItemMeta meta = item.getItemMeta();
					item.setAmount(o);
					ArrayList<String>list = new ArrayList<>();
					list.add(" ");
					meta.setLore(list);
					item.setItemMeta(meta);
					p.getInventory().addItem(item);
					p.updateInventory();
					item.setAmount(r);
					meta.setLore(null);
					item.setItemMeta(meta);
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
					p.updateInventory();
				}else{
					int j = getitemammount(material, p);
					int v = j/i;
					int o = e.getCurrentItem().getAmount()*v;
					removeitem(o*i, material, p);
					ItemStack item = getitemwithoutdisplayname(e.getCurrentItem());
					int r = e.getCurrentItem().getAmount();
					ArrayList<String> lore = (ArrayList<String>) e.getCurrentItem().getItemMeta().getLore();
					ItemMeta meta = item.getItemMeta();
					item.setAmount(o);
					ArrayList<String>list = new ArrayList<>();
					list.add(" ");
					meta.setLore(list);
					item.setItemMeta(meta);
					p.getInventory().addItem(item);
					p.updateInventory();
					item.setAmount(r);
					meta.setLore(null);
					item.setItemMeta(meta);
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
					p.updateInventory();
				}
				}else{
					p.sendMessage(Main.Prefix+ChatColor.RED+"Du hast nicht genügend Ressourcen um dieses Item zu kaufen");
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1F, 1F);
				}
			}else{
//			======================================================================
				if(getitemammount(material, p) >= i){
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
					ItemStack item = getitemwithoutdisplayname(e.getCurrentItem());
					ItemMeta meta = item.getItemMeta();
					ArrayList<String> list = (ArrayList<String>) e.getCurrentItem().getItemMeta().getLore();
					ArrayList<String> lore = new ArrayList<>();
					lore.add(" ");
					meta.setLore(lore);
					item.setItemMeta(meta);
					p.updateInventory();
					p.getInventory().addItem(item);
					removeitem(i, material, p);
					meta.setLore(list);
					item.setItemMeta(meta);
					p.updateInventory();
				}else{
					p.sendMessage(Main.Prefix+ChatColor.RED+"Du hast nicht genügend Ressourcen um dieses Item zu kaufen");
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1F, 1F);
				}
				}
//				
				
		}
			}
		}
//			======================================================================
			}
			}
		}
	}
	
	public static Integer getitemammount(Material item, Player p){
		int i = 0;
		for(int b = 0;b<p.getInventory().getSize();b++){
			if(p.getInventory().getItem(b)!=null){
			if(p.getInventory().getItem(b).getType() == item){
				i = i+p.getInventory().getItem(b).getAmount();
			}
		}
		}
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public static void removeitem(Integer amount,Material m,Player p){
//==========================================================
boolean cc = false;
int i = 0;
for(ItemStack item : p.getInventory().getContents()){
	if(item !=null){
	if(item.getType() == m){
		if(item.getAmount() >= amount){
			
			if(item.getAmount() == amount){
				p.getInventory().setItem(i, new ItemStack(Material.AIR));
				p.getInventory().remove(i);
				cc = true;
				break;
			}else{
				int ii = item.getAmount() - amount;
				ItemStack item1 = new ItemStack(m,ii);
				ItemMeta mm = item1.getItemMeta();
				mm.setDisplayName(item.getItemMeta().getDisplayName());
				item1.setItemMeta(mm);
				p.getInventory().setItem(i, item1);
				cc = true;
				break;
			}
		}
	}
}
	i++;
}

if(cc == false){
	int b = 0;
	int y = 0;
	for(ItemStack item : p.getInventory()){
		if(item!=null){
			if(item.getType() == m){
				if(b < amount){
					if((b+p.getInventory().getItem(y).getAmount())>amount){
						int h = amount - b;
						int t = p.getInventory().getItem(y).getAmount() - h;
						p.getInventory().setItem(y, new ItemStack(m,t));
						break;
					}else{
						b = b+p.getInventory().getItem(y).getAmount();
						p.getInventory().setItem(y, new ItemStack(Material.AIR));
					}
				}
			}
		}
		y++;
	}
}

//		===================================================
	}
	
	public static ItemStack createitem(Material m, String displayname, Enchantment ench1,int ech1level, Enchantment ench2,int ech2level){
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		if(ench1!=null){
			meta.addEnchant(ench1, ech1level, true);
		}
		if(ench2!=null){
			meta.addEnchant(ench2, ech2level, true);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createitemwithlore(Material m, String displayname, Enchantment ench1,int ech1level, Enchantment ench2,int ech2level, String preis){
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		if(ench1!=null){
			meta.addEnchant(ench1, ech1level, true);
		}
		if(ench2!=null){
			meta.addEnchant(ench2, ech2level, true);
		}
		if(preis!=null){
		ArrayList<String> list = new ArrayList<>();
		list.add(" ");
		list.add(preis);
		list.add(" ");
		meta.setLore(list);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createitemwithlore2(Material m, String displayname, Enchantment ench1,int ech1level, Enchantment ench2,int ech2level,Enchantment ench3,int ech3level, String preis){
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		if(ench1!=null){
			meta.addEnchant(ench1, ech1level, true);
		}
		if(ench2!=null){
			meta.addEnchant(ench2, ech2level, true);
		}
		if(ench3!=null){
			meta.addEnchant(ench3, ech3level, true);
		}
		if(preis!=null){
		ArrayList<String> list = new ArrayList<>();
		list.add(" ");
		list.add(preis);
		list.add(" ");
		meta.setLore(list);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createanzahlitemwithlore(Material m, String displayname, String preis, int anzahl){
		ItemStack item = new ItemStack(m, anzahl);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		if(preis!=null){
		ArrayList<String> list = new ArrayList<>();
		list.add(" ");
		list.add(preis);
		list.add(" ");
		meta.setLore(list);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createrüstung(Material m, Color e, String displayname, Enchantment ench, String preis){
		ItemStack y = new ItemStack(m);
		LeatherArmorMeta  ymeta = (LeatherArmorMeta) y.getItemMeta();
		ymeta.setDisplayName(displayname);
		ymeta.addEnchant(ench, 1, true);
		ymeta.setColor(e);
		if(preis!=null){
		ArrayList<String>list = new ArrayList<>();
		list.add(" ");
		list.add(preis);
		list.add(" ");
		ymeta.setLore(list);
		}
		y.setItemMeta(ymeta);
		return y;
	}
	
	public static ItemStack createpotion(String displayname, short s, String preis){
		ItemStack item = new ItemStack(Material.POTION, 1, s);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> list = new ArrayList<>();
		list.add(" ");
		list.add(preis);
		list.add(" ");
		meta.setLore(list);
		item.setItemMeta(meta);
		return item;
	}
	
	
	HashMap<Player ,Integer> scheduler = new HashMap<>();
	HashMap<Player ,Integer> inte = new HashMap<>();
	int count;
	@EventHandler
	public void onplace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(e.getBlock().getType() == Material.LAPIS_BLOCK){
			if(scheduler.containsKey(p)){
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du kannst nur ein Pack gleichzeitig öffnen");
				e.setCancelled(true);
			}else{
			Block b = e.getBlock();
				inte.put(p, 0);
				count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
					@Override
					public void run() {
						if(inte.get(p)<= 9){
							b.getLocation().getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 22);		
//							
							sendBlockBreak(b.getLocation(), inte.get(p));
//							
							int j = inte.get(p);
							Hologram holo11 = new Hologram();
							holo11.setText(""+ChatColor.YELLOW+(9-j)+ChatColor.GRAY+" Sekunden");
							holo11.setLocation(b.getLocation());
							holo11.setHeight(0.5);
							holo11.spawntemp(10);
//							
//	...........................................................................
							int i = inte.get(p);
							i++;
							inte.remove(p);
							inte.put(p, i);						
//	.............................................................................
							if(i== 9){
								feuerwerk(b.getLocation().subtract(0, 1, 0), Color.BLUE);
							}
						}else{
						p.playSound(p.getLocation(), Sound.ANVIL_USE, 1F, 1F);
//						
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createanzahlitemwithlore(Material.SANDSTONE, ChatColor.AQUA+"Sandstein", ChatColor.DARK_RED+"", 64));
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createrüstung(Material.LEATHER_BOOTS, Color.BLUE, ChatColor.BLUE+"Lederschuhe", Enchantment.PROTECTION_ENVIRONMENTAL, null));
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createrüstung(Material.LEATHER_HELMET, Color.BLUE, ChatColor.BLUE+"Lederhelm", Enchantment.PROTECTION_ENVIRONMENTAL, null));
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createrüstung(Material.LEATHER_LEGGINGS, Color.BLUE, ChatColor.BLUE+"Lederhose", Enchantment.PROTECTION_ENVIRONMENTAL, null));
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.STICK, ChatColor.AQUA+"Knockback Stick", Enchantment.KNOCKBACK, 1, null, 0, null));
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.WOOD_PICKAXE, ChatColor.DARK_RED+"Holzspitzhack", Enchantment.DURABILITY, 1, null, 0, null));
//						
						inte.remove(p);
						Bukkit.getScheduler().cancelTask(scheduler.get(p));
						scheduler.remove(p);
						b.setType(Material.AIR);
						}
					}
				}, 0, 10);
				scheduler.put(p, count);
			}
			}else if(e.getBlock().getType() == Material.EMERALD_BLOCK){
				if(scheduler.containsKey(p)){
					p.sendMessage(Main.Prefix+ChatColor.RED+"Du kannst nur ein Pack gleichzeitig öffnen");
					e.setCancelled(true);
				}else{
				Block b = e.getBlock();
					inte.put(p, 0);
					count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
						@Override
						public void run() {
							if(inte.get(p)<= 9){
								b.getLocation().getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 133);		
//								
								sendBlockBreak(b.getLocation(), inte.get(p));
//								
								int j = inte.get(p);
								Hologram holo11 = new Hologram();
								holo11.setText(""+ChatColor.YELLOW+(9-j)+ChatColor.GRAY+" Sekunden");
								holo11.setLocation(b.getLocation());
								holo11.setHeight(0.5);
								holo11.spawntemp(10);
//								
//		...........................................................................
								int i = inte.get(p);
								i++;
								inte.remove(p);
								inte.put(p, i);						
//		.............................................................................
								if(i== 9){
									feuerwerk(b.getLocation().subtract(0, 1, 0), Color.GREEN);
								}
							}else{
							p.playSound(p.getLocation(), Sound.ANVIL_USE, 1F, 1F);
//							
							b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA+"Kettenbrust I", Enchantment.PROTECTION_ENVIRONMENTAL, 3, null, 0, null));
							b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.BOW, ChatColor.YELLOW+"Bogen I", Enchantment.ARROW_INFINITE, 1, null, 0, null));
							b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.ARROW, ChatColor.YELLOW+"Pfeil", null, 0,  null, 0, null));
//							
							inte.remove(p);
							Bukkit.getScheduler().cancelTask(scheduler.get(p));
							scheduler.remove(p);
							b.setType(Material.AIR);
							}
						}
					}, 0, 10);
					scheduler.put(p, count);
				}
				}else if(e.getBlock().getType() == Material.DIAMOND_BLOCK){
					if(scheduler.containsKey(p)){
						p.sendMessage(Main.Prefix+ChatColor.RED+"Du kannst nur ein Pack gleichzeitig öffnen");
						e.setCancelled(true);
					}else{
					Block b = e.getBlock();
						inte.put(p, 0);
						count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
							@Override
							public void run() {
								if(inte.get(p)<= 9){
									b.getLocation().getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 57);		
//									
									sendBlockBreak(b.getLocation(), inte.get(p));
//									
									int j = inte.get(p);
									Hologram holo11 = new Hologram();
									holo11.setText(""+ChatColor.YELLOW+(9-j)+ChatColor.GRAY+" Sekunden");
									holo11.setLocation(b.getLocation());
									holo11.setHeight(0.5);
									holo11.spawntemp(10);
//									
//			...........................................................................
									int i = inte.get(p);
									i++;
									inte.remove(p);
									inte.put(p, i);						
//			.............................................................................
									if(i== 9){
										feuerwerk(b.getLocation().subtract(0, 1, 0), Color.AQUA);
									}
								}else{
								p.playSound(p.getLocation(), Sound.ANVIL_USE, 1F, 1F);
//								
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createanzahlitemwithlore(Material.WEB, ChatColor.YELLOW+"Spinnenweben", null, 10));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA+"Kettenbrust I", Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, null));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.STICK, ChatColor.AQUA+"Knockback Stick", Enchantment.KNOCKBACK, 1, null, 0, null));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createanzahlitemwithlore(Material.LADDER, ChatColor.YELLOW+"Leiter", null, 5));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.WOOD_PICKAXE, ChatColor.DARK_RED+"Holzspitzhacke", Enchantment.DURABILITY, 1, null, 0, null));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createanzahlitemwithlore(Material.SANDSTONE, ChatColor.AQUA+"Sandstein", null, 64));
								b.getLocation().getWorld().dropItemNaturally(b.getLocation(),createitemwithlore(Material.BLAZE_ROD, ChatColor.GRAY+"Rettungsplattform", null, 1, null, 1, null));
//								
								inte.remove(p);
								Bukkit.getScheduler().cancelTask(scheduler.get(p));
								scheduler.remove(p);
								b.setType(Material.AIR);
								}
							}
						}, 0, 10);
						scheduler.put(p, count);
					}
					}
	}
	
	public static void sendBlockBreak(Location loc,Integer level){
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(loc.getX(),loc.getY(),loc.getZ()), level);
		((CraftServer)Bukkit.getServer()).getHandle().sendPacketNearby(loc.getX(),loc.getY(),loc.getZ(),30, ((CraftWorld)loc.getWorld()).getHandle().dimension, packet);
	}
	
	public static void feuerwerk(Location loc, Color c){
		Firework firework = loc.getWorld().spawn(loc, Firework.class);
		FireworkEffect effect = FireworkEffect.builder()
				.withColor(c)
				.flicker(true)
				.trail(false)
				.withFade(Color.WHITE)
				.with(FireworkEffect.Type.BALL_LARGE)
				.build();
		FireworkMeta meta = firework.getFireworkMeta();
		meta.addEffect(effect);
		meta.setPower(0);
		firework.setFireworkMeta(meta);
	}
	
	
	HashMap<Player,Integer>sc = new HashMap<>();
	HashMap<Player,Integer>scint = new HashMap<>();
	HashMap<Player, Double> effdouble = new HashMap<>();
	int c;
	@EventHandler
	public void onkli(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = e.getPlayer();
			if(p.getItemInHand().getType() == Material.SULPHUR){
				scint.put(p, 0);
				effdouble.put(p, 0.1);
				p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du wirst wenn du dich nicht bewegst in 5 Sekunden Teleportiert");
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1F, 1F);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
					@Override
					public void run() {
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
				}, 33);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
					@Override
					public void run() {
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
				}, 66);
				
				c = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						if(scint.get(p)<=20){
							
//.............................................................
							
							
							
							Location l1 = p.getLocation();
//							
							double b = effdouble.get(p)+0.1;
							effdouble.remove(p);
							effdouble.put(p, b);
//							
							
							l1.setY(p.getLocation().getY()+b);
							
							p.playEffect(l1, Effect.INSTANT_SPELL, 1);
							p.playEffect(l1, Effect.INSTANT_SPELL, 1);
							p.playEffect(l1, Effect.INSTANT_SPELL, 1);
							p.playEffect(l1, Effect.INSTANT_SPELL, 1);
//.............................................................
							
//						
						int i = scint.get(p);
						i++;
						scint.remove(p);
						scint.put(p, i);
//						
						sc.put(p, c);
						}else{
							scint.remove(p);
//							
							double x = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawnx");
							double y = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawny");
							double z = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawnz");
							float yaw = (float) Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".yaw");
							float pitch = (float) Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".pitch");
							Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z, yaw, pitch);
							p.teleport(loc);
							Bukkit.getScheduler().cancelTask(sc.get(p));
							sc.remove(p);
							p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du wurdest nach Hause Teleportiert");
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
							int i = 0;
							for(ItemStack item : p.getInventory()){
								if(item!=null){
								if(item.getType() == Material.SULPHUR){
									if(item.getAmount() == 1){
										p.getInventory().setItem(i, new ItemStack(Material.AIR));
									}else{
										item.setAmount(item.getAmount()-1);
									}
								}
							}
								i++;
							}
						}
					}
				}, 0, 5);
			}
		}
	}
	
			
	@EventHandler
	public void onmove(PlayerMoveEvent e){
		if(sc.containsKey(e.getPlayer())){
			
			
			if(e.getFrom().getBlock().getX()!=e.getTo().getBlock().getX()||e.getFrom().getBlock().getZ()!=e.getTo().getBlock().getZ()){
			e.getPlayer().sendMessage(Main.Prefix+ChatColor.RED+"Die Teleportation wurde abgebrochen");
			Bukkit.getScheduler().cancelTask(sc.get(e.getPlayer()));
			sc.remove(e.getPlayer());
			}
		}
	}
	@EventHandler
	public void noUproot(PlayerInteractEvent event)
	{
	    if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
	        event.setCancelled(true);
	}
	
    public static Color cololorchanger(String s){
    	Color color = null;
    	if(s.equals("&4")){
    		color = Color.RED;
    	}else if(s.equals("&c")){
    		color = Color.RED;
    	}else if(s.equals("&e")){
    		color = Color.YELLOW;
    	}else if(s.equals("&6")){
    		color = Color.YELLOW;
    	}else if(s.equals("&2")){
    		color = Color.GREEN;
    	}else if(s.equals("&a")){
    		color = Color.GREEN;
    	}else if(s.equals("&b")){
    		color = Color.BLUE;
    	}else if(s.equals("&3")){
    		color = Color.BLUE;
    	}else if(s.equals("&1")){
    		color = Color.BLUE;
    	}else if(s.equals("&9")){
    		color = Color.PURPLE;
    	}else if(s.equals("&d")){
    		color = Color.PURPLE;
    	}else if(s.equals("&5")){
    		color = Color.PURPLE;
    	}else if(s.equals("&f")){
    		color = Color.WHITE;
    	}else if(s.equals("&7")){
    		color = Color.GRAY;
    	}else if(s.equals("&8")){
    		color = Color.GRAY;
    	}else if(s.equals("&0")){
    		color = Color.BLACK;
    	}
    	return color;
    }
    
    public static ItemStack getitemwithoutdisplayname(ItemStack item){
    	return item;
    }
    
    @EventHandler
    public void onbrak(BlockBreakEvent e){
    	if(Main.ingame){
    		if(e.getBlock().getType() == Material.SANDSTONE){
    			e.getBlock().getDrops().clear();
    			e.getBlock().setType(Material.AIR);
    			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), createanzahlitemwithlore(Material.SANDSTONE, ChatColor.AQUA+"Sandstein", null, 1));
    		}else if(e.getBlock().getType() == Material.IRON_BLOCK){
    			e.getBlock().getDrops().clear();
    			e.getBlock().setType(Material.AIR);
    			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), createanzahlitemwithlore(Material.IRON_BLOCK, ChatColor.GRAY+"Eisenblock", null, 1));
    		}else if(e.getBlock().getType() == Material.ICE){
    			e.getBlock().getDrops().clear();
    			e.getBlock().setType(Material.AIR);
    			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), createanzahlitemwithlore(Material.PACKED_ICE, ChatColor.AQUA+"Eis", null, 1));
    		}else if(e.getBlock().getType() == Material.ENDER_STONE){
    			e.getBlock().getDrops().clear();
    			e.getBlock().setType(Material.AIR);
    			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), createanzahlitemwithlore(Material.ENDER_STONE, ChatColor.YELLOW+"Endstein", null, 1));
    		}
    	}
    }
}
