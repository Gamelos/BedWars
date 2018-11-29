package de.gamelos.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.connorlinfoot.titleapi.TitleAPI;
import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;
import de.gamelos.jaylosapi.JaylosAPI;

public class JoinEvent implements Listener {
	@EventHandler
	public void onjoin(PlayerJoinEvent e){
		
		e.getPlayer().setFireTicks(0);
		if(Main.ingame == false){
		e.setJoinMessage(null);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
			@Override
			public void run() {
				for(Player pp:Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(e.getPlayer(), pp)+ChatColor.DARK_GRAY+" ist dem Spiel beigetreten!");
				}
			}
		}, 10);
//		
		
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setLevel(0);
		p.setExp(0);
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §bBedwars §8===================",
				ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
						+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
//		
		ItemStack item = new ItemStack(Material.BED);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Team Auswahl §8(§7Rechtsklick§8)");
		item.setItemMeta(meta);
		p.getInventory().setItem(0, item);
//		
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		Double x1 = Main.loc.getDouble("spawn.x");
		Double y1 = Main.loc.getDouble("spawn.y");
		Double z1 = Main.loc.getDouble("spawn.z");
		Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
		Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
		World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
		Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
        p.teleport(lobbyspawn);
//		
        de.gamelos.scoreboard.Lobby.createScoreboard();
		if(Bukkit.getOnlinePlayers().size() >= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
			Events.startcount();
		}
		
//		?????????????????????????????????????????
		Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Nick"), new Runnable(){
			@Override
			public void run() {
				for(Player pp:Bukkit.getOnlinePlayers()){
				de.gamelos.scoreboard.Lobby.createScoreboard();
				}
			}
		}, 22);
//		?????????????????????????????????????????
		
		if(JaylosAPI.ispartyleader(p)){
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
				@Override
				public void run() {
					if(Main.ingame == false){
					List<String> list = JaylosAPI.getparty(p);
					if(list.size()>=Integer.parseInt(Main.loc.getString(Main.mapname + ".playerinteam"))){
						p.sendMessage(ChatColor.RED+"Es konnte kein passendes Team gefunden werden!");
					}else{
						String s = Events.getemtyteam();
						if(s == null){
							p.sendMessage(ChatColor.RED+"Es konnte kein passendes Team gefunden werden!");
						}else{
							Main.teamname.put(p, s);
							for(String pp:list){
								if(Bukkit.getPlayer(pp)!=null){
							Main.teamname.put(Bukkit.getPlayer(pp), s);
								}
							}
							p.sendMessage(ChatColor.GREEN+"Deine Party ist nun in einem Team!");
							 de.gamelos.scoreboard.Lobby.createScoreboard();
						}
					}
					}else{
						p.sendMessage(ChatColor.RED+"Ihr konntet nicht in ein Team eingewiesen werden, da der Countdown zu weit fortgeschritten wahr!");
					}
				}
			}, 50);
		}
		
		}else{
			if(Main.teamname.containsKey(e.getPlayer())){
				Main.teamname.remove(e.getPlayer());
			}
			e.setJoinMessage(null);
			e.getPlayer().setGameMode(GameMode.SURVIVAL);
			e.getPlayer().setHealth(20);
			e.getPlayer().setFoodLevel(20);
			for(Player pp : Main.playeringame){
				pp.hidePlayer(e.getPlayer());
			}
			TitleAPI.sendTabTitle(e.getPlayer(), "§8===================§9 Jaylos.net §7- §bBedwars §8===================",
					ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
							+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
			Events.setspectator(e.getPlayer());
			de.gamelos.scoreboard.IngameBoard.setscoreboard();
		}
	}

	
	
	
public static void join(Player p){
	p.setFireTicks(0);
	if(Main.ingame == false){
	Bukkit.broadcastMessage(Main.Prefix+ChatColor.YELLOW+p.getName()+ChatColor.DARK_GRAY+" ist dem Spiel beigetreten!");
//	
	
	p.getInventory().clear();
	p.setGameMode(GameMode.SURVIVAL);
	p.setFoodLevel(20);
	p.setHealth(20);
	p.setLevel(0);
	p.setExp(0);
	p.getInventory().setHelmet(new ItemStack(Material.AIR));
	p.getInventory().setLeggings(new ItemStack(Material.AIR));
	p.getInventory().setBoots(new ItemStack(Material.AIR));
	p.getInventory().setChestplate(new ItemStack(Material.AIR));
	TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §bBedwars §8===================",
			ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
					+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
//	
	ItemStack item = new ItemStack(Material.BED);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.RED+"Team Auswahl §8(§7Rechtsklick§8)");
	item.setItemMeta(meta);
	p.getInventory().setItem(0, item);
//	
	for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
	}
	Double x1 = Main.loc.getDouble("spawn.x");
	Double y1 = Main.loc.getDouble("spawn.y");
	Double z1 = Main.loc.getDouble("spawn.z");
	Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
	Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
	World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
	Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
    p.teleport(lobbyspawn);
//	
    de.gamelos.scoreboard.Lobby.createScoreboard();
	if(Bukkit.getOnlinePlayers().size() >= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
		Events.startcount();
	}
	
	}else{
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
			@Override
			public void run() {
				for(Player pp:Bukkit.getOnlinePlayers()){
					if(!Main.spectatorplayer.contains(pp)){
						pp.hidePlayer(p);
					}
				}
			}
		},20);
		p.setHealth(20);
		p.setFoodLevel(20);
		for(Player pp : Main.playeringame){
			pp.hidePlayer(p);
		}
		TitleAPI.sendTabTitle(p, "§8===================§9 Jaylos.net §7- §bBedwars §8===================",
				ChatColor.GRAY + "Reporte Spieler mit " + ChatColor.RED + "/report " + ChatColor.GRAY
						+ "oder erstelle eine Party mit" + ChatColor.DARK_PURPLE + " /party");
		Events.setspectator(p);
		de.gamelos.scoreboard.IngameBoard.setscoreboard();
	}
}
}
