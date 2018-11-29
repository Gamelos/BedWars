package de.gamelos.bedwars;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.gamelos.PermissionsAPI.MySQLRang;
import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.stats.MySQL;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public static String Prefix = "["+ChatColor.AQUA+"BedWars"+ChatColor.WHITE+"] "+ChatColor.GRAY;
	public static File locations;
	public static FileConfiguration loc;
	public static Boolean ingame = false;
	public static String mapname = "null";
	public static ArrayList<Player>playeringame = new ArrayList<>();
	public static ArrayList<Player>spectatorplayer = new ArrayList<>();
	public static HashMap<Player,String>teamname = new HashMap<>();
	
	
	public static int maxplayer = 0;
	public static MySQL mysql;
	private void ConnectMySQL(){
		mysql = new MySQL(JaylosAPI.gethost(), JaylosAPI.getuser(), JaylosAPI.getdatabase(), JaylosAPI.getpassword());
		mysql.update("CREATE TABLE IF NOT EXISTS Bedwars(UUID varchar(64), KILLS int, TODE int, WINS int, GAMES int, NAME varchar(64), BETTEN int);");
	}
	
	
	@Override
	public void onEnable() {
		Main.locations = new File("plugins/Bedwars", "data.yml");
		Main.loc = YamlConfiguration.loadConfiguration(Main.locations);
		getCommand("bw").setExecutor(new Commands());
//		====
		getCommand("promote").setExecutor(new Commands());
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("start").setExecutor(new Start());
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "info");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
//        ===
		Bukkit.getPluginManager().registerEvents(new Commands(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		System.out.println("[Bedwars] Das Plugin wurde geladen");
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new Shop(), this);
		Bukkit.getPluginManager().registerEvents(new Activate(), this);
		Bukkit.getPluginManager().registerEvents(new Deaktivieren(), this);
		Bukkit.getPluginManager().registerEvents(new Commands(), this);
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
//		
		Bukkit.getPluginManager().registerEvents(new de.gamelos.events.QuitEvent(), this);
		Bukkit.getPluginManager().registerEvents(new de.gamelos.events.JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new de.gamelos.events.TeamSelector(), this);
		Bukkit.getPluginManager().registerEvents(new de.gamelos.events.BettAbbau(), this);
		Bukkit.getPluginManager().registerEvents(new de.gamelos.events.RespawnEvent(), this);
		de.gamelos.nick.Main.nowtonick = true;
//		
		getCommand("bw").setExecutor(new Commands());
		Deaktivieren.setitems();
		Main.locations = new File("plugins/Bedwars", "data.yml");
		Main.loc = YamlConfiguration.loadConfiguration(Main.locations);
		mapname = getRandomMap();
//		
//		
		int i1 = Integer.parseInt(Main.loc.getString(Main.mapname + ".playerinteam"));
		int i2 = getteamanzahl();
		maxplayer = i1*i2;
		Events.startteamshower();
		ConnectMySQL();
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			@Override
			public void run() {
				CloudServer.getInstance().setMotd(mapname+" "+getteamanzahl()+"x"+Integer.parseInt(Main.loc.getString(Main.mapname + ".playerinteam")));
				CloudServer.getInstance().setMaxPlayersAndUpdate(maxplayer);
			}
		}, 3);
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){

			@Override
			public void run() {
				Ranking.updateranking();
			}
			
		}, 40);
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			@Override
			public void run() {
				restartcheck();
			}
		}, 20*60);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Bedwars] Das Plugin wurde deaktiviert");
		super.onDisable();
	}
	
	@EventHandler
	public void onspawn(EntitySpawnEvent e){
		if(e.getEntity() instanceof Player ||e.getEntity() instanceof Villager||e.getEntity() instanceof Item){
		}else{
			e.setCancelled(true);
		}
	}
	
	
	public static int getmapanzahl() {
		int i = 0;
		int ii = 1;
		while (loc.getString("Mapnames." + ii) != null) {
			i++;
			ii++;
		}
		return i;
	}
	
	public static String getRandomMap(){
		String mapname = "";
		int i;
		int ii = getmapanzahl()-1;
		
		Random random = new Random();
		i = random.nextInt(ii+(1))+1;
		mapname = Main.loc.getString("Mapnames."+i);
//		
//		
		
		return mapname;
	}
	
	public static Integer getteamanzahl(){
		int i = 0;
		for(@SuppressWarnings("unused") String ss:Events.getteams()){
			i++;
		}
		return i;
	}
	
	@SuppressWarnings("resource")
	@EventHandler
	public void onlog(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(ingame == false){
			int maxplayer = Main.maxplayer;
				if(Bukkit.getOnlinePlayers().size()>=maxplayer){
					if(MySQLRang.getRangname(p.getUniqueId().toString()).equals("Admin")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Mod")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Sup")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Youtuber")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Premium")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Builder")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Dev")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Contant")||MySQLRang.getRangname(p.getUniqueId().toString()).equals("Prem+")){
					for(Player pp:Bukkit.getOnlinePlayers()){
						if(!pp.hasPermission("cloudnet.fulljoin")){
							pp.sendMessage(ChatColor.GRAY+"Du wurdest gekickt um platz für ein "+ChatColor.GOLD+"Premium Spieler §7/ §5Youtuber §7/ §cTeammitglied §7zu machen! Kaufe dir Premium unter §eweb.jaylos.net §7 um nicht mehr gekickt zu werden!");
//							
							ByteArrayOutputStream b = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(b);
							try{
								out.writeUTF("Connect");
								out.writeUTF("Lobby-1");
							}catch(IOException ex){
								System.err.println("Es gab einen Fehler:");
								ex.printStackTrace();
							}
							Plugin pl = Bukkit.getPluginManager().getPlugin("Bedwars");
							pp.sendPluginMessage(pl, "BungeeCord", b.toByteArray());
//							
							e.allow();
							return;
						}
					}
					e.disallow(Result.KICK_OTHER, ChatColor.RED+"Dieser Server ist voll! Keiner kann den Server mehr betreten");
					}else{
						e.disallow(Result.KICK_FULL, ChatColor.RED+"Dieser Server ist voll!"+ChatColor.GRAY+" Kaufe dir Premium unter "+ChatColor.YELLOW+"web.jaylos.net"+ChatColor.GRAY+" um trotzdem Joinen zu können!");
					}
					}
		}
	}
	
	public void restartcheck(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				c.setTime(new Timestamp(System.currentTimeMillis()));
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.GERMAN);
				String s = sdf.format(c.getTime());
				if(s.equals("03:30")){
					Bukkit.shutdown();
				}
			}
		}, 0, 20*10);
	}
	
	public static String getrealname(Player p, Player reseaver){
		return p.getName();
	}
}
