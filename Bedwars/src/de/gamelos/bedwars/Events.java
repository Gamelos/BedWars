package de.gamelos.bedwars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.titleapi.TitleAPI;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.gamelos.jaylosapi.JaylosAPI;
import de.gamelos.nick.PlayerNickEvent;
import de.gamelos.nick.unNickEvent;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class Events implements Listener {

	public static org.bukkit.plugin.Plugin pl = Bukkit.getPluginManager().getPlugin("Bedwars");
	
	public static int count;
	public static int num = 30;
	public static boolean startcount = false;
	public static int icount = 30;
	
	
@SuppressWarnings("deprecation")
public static void startcount(){
		
		
		if(Main.ingame == false){
			if(startcount == false){
				startcount = true;
		Bukkit.broadcastMessage(Main.Prefix+"Der Countdown wurde gestartet");
		
		count = Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
			@Override
			public void run() {
				if(icount>1){
					
					icount--;
					
					float exp = (float) ((double) icount / (double) num);
					
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.setLevel(icount);
						pp.setExp(exp);
					}
					
					
					
					if(icount==50){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde startet in "+ChatColor.YELLOW+icount+ChatColor.GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==40){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde startet in "+ChatColor.YELLOW+icount+ChatColor.GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==30){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde startet in "+ChatColor.YELLOW+icount+ChatColor.GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					if(icount==20){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde startet in "+ChatColor.YELLOW+icount+ChatColor.GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					
					if(icount==5){
						for(Player pp:Bukkit.getOnlinePlayers()){
						TitleAPI.sendFullTitle(pp, 20, 60, 20, ChatColor.AQUA+"BedWars", Main.mapname);
						}
					}
					
					if(icount<=10){
						Bukkit.broadcastMessage(Main.Prefix+"Die Runde startet in "+ChatColor.YELLOW+icount+ChatColor.GRAY+" Sekunden");
						for(Player pp:Bukkit.getOnlinePlayers()){
							pp.playSound(pp.getLocation(), Sound.NOTE_BASS, 1F, 1F);
						}
					}
					
					if(icount==15){
						String deletworld = "gameworld";
						Bukkit.getServer().unloadWorld(deletworld, true);
						try {
						FileUtils.deleteDirectory(new File(deletworld));
						}catch (IOException e){
							e.printStackTrace();
						}
					}
					
					
					if(icount==5){
						kopyWorld("Maps/Bedwars/"+Main.mapname, "gameworld");
					}
					if(icount==2){
						Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
							@Override
							public void run() {
								for(org.bukkit.entity.Entity ent : Bukkit.getWorld("gameworld").getEntities()){
									if(!(ent instanceof Player)){
										if(ent.getType() !=EntityType.VILLAGER){
										ent.remove();
										}
									}
								}
							}
						}, 0);
					}
				}else{
					Bukkit.getScheduler().cancelTask(count);
					CloudServer.getInstance().changeToIngame();
					CloudServer.getInstance().setMotdAndUpdate("ingame");
					setrandomteam();
					Bukkit.getWorld("gameworld").setTime(1000);
					Bukkit.getWorld("gameworld").setGameRuleValue("doDaylightCycle", "false");
					Bukkit.broadcastMessage(Main.Prefix+ChatColor.GREEN+"Die Runde startet");
					for(Player pp:Bukkit.getOnlinePlayers()){
						BedwarsStats.addGames(pp.getUniqueId().toString(), 1, pp.getName());
						pp.playSound(pp.getLocation(), Sound.LEVEL_UP, 1F, 1F);
						Main.playeringame.add(pp);
						pp.setLevel(0);
						pp.setExp(0);
						pp.getInventory().clear();
					}
					Main.ingame = true;
					de.gamelos.nick.Main.nowtonick = false;
//					
					startbronzespawner();
					starteisenspawner();
					startgoldspawner();
					for(String ss:getteams()){
						teamswithbed.add(umlautübersetzer(ss));
					}
					
//					TELEPORT
					Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
						@Override
						public void run() {
							for(Player pp : Bukkit.getOnlinePlayers()){
								if(Main.teamname.containsKey(pp)){
								double x = Main.loc.getDouble(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".spawnx");
								double y = Main.loc.getDouble(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".spawny");
								double z = Main.loc.getDouble(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".spawnz");
								float yaw = (float) Main.loc.getDouble(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".yaw");
								float pitch = (float) Main.loc.getDouble(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".pitch");
								Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z, yaw, pitch);
								pp.teleport(loc);
							}
							}
						}
					}, 0);
							Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
								@Override
								public void run() {
									de.gamelos.scoreboard.IngameBoard.setscoreboard();
								}
							},20);
//					
				}
			}
		}, 20, 20);
		}
		}
		
	}
	
public static void kopyWorld(String worldtocopyname, String newname){
	
	Bukkit.getServer().unloadWorld(worldtocopyname, true);
	
	try {
		FileUtils.copyDirectory(new File(worldtocopyname), new File(newname));
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	Bukkit.createWorld(new WorldCreator(newname));
	
}

public static Boolean isend = false;

@EventHandler
public void onbrak(BlockBreakEvent e){
	if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
		e.setCancelled(true);
	}
}

@EventHandler
public void onplace(BlockPlaceEvent e){
	if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
		e.setCancelled(true);
	}
}

@EventHandler
public void onfood(FoodLevelChangeEvent e){
	if(Main.ingame == false||Main.spectatorplayer.contains(e.getEntity())||isend){
		e.setCancelled(true);
	}
}

@EventHandler
public void onhealth(EntityDamageEvent e){
	if(e.getEntity() instanceof Player){
	if(Main.ingame == false||Main.spectatorplayer.contains(e.getEntity())||isend){
		e.setCancelled(true);
	}
	}
}

@EventHandler
public void ondrop(PlayerDropItemEvent e){
	if(Main.ingame == false||Main.spectatorplayer.contains(e.getPlayer())||isend){
		e.setCancelled(true);
	}
}

@EventHandler
public void oninv(InventoryClickEvent e){
	if(Main.ingame == false){
		e.setCancelled(true);
	}
}


public static void setspectator(Player p){
	if(Main.teamname.containsKey(p)){
		Main.teamname.remove(p);
	}
	Main.teamname.put(p, "Spectator");
	p.setGameMode(GameMode.SURVIVAL);
	p.getInventory().clear();
	p.getInventory().setHelmet(new ItemStack(Material.AIR));
	p.getInventory().setLeggings(new ItemStack(Material.AIR));
	p.getInventory().setBoots(new ItemStack(Material.AIR));
	p.getInventory().setChestplate(new ItemStack(Material.AIR));
	p.teleport(Bukkit.getWorld("gameworld").getSpawnLocation());
	p.spigot().setCollidesWithEntities(false);
	
	for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
	}
	
	Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
		@Override
		public void run() {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		}
	}, 0);
	
	ItemStack item = new ItemStack(Material.COMPASS);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.YELLOW+"Spieler Teleporter "+ChatColor.GRAY+"(Rechtsklick)");
	item.setItemMeta(meta);
	p.getInventory().setItem(0, item);
	Main.spectatorplayer.add(p);
	p.setAllowFlight(true);
}

@EventHandler
public void blockdamage(BlockDamageEvent e){
	if(Main.spectatorplayer.contains(e.getPlayer())){
		e.setCancelled(true);
	}
}

@EventHandler
public void onplace1(BlockPlaceEvent e){
	Location loc = e.getBlock().getLocation();
	for(Player pp :Main.spectatorplayer){
		if(e.getPlayer()!=pp){
		if(pp.getLocation().getBlock().getLocation().equals(loc)||pp.getLocation().getBlock().getLocation().subtract(0, 1, 0).equals(loc)||pp.getLocation().getBlock().getLocation().add(0, 1, 0).equals(loc)){
			pp.teleport(pp.getLocation().add(2, 0, 0));
		}
		}
	}
}

@EventHandler
public void onklick1(PlayerInteractEvent e){
	if(e.getAction()==Action.RIGHT_CLICK_BLOCK){
		for(Player pp :Main.spectatorplayer){
			if(e.getPlayer()!=pp){
			if(e.getClickedBlock().getLocation().distance(pp.getLocation().getBlock().getLocation())<=1.5){
				pp.teleport(e.getPlayer().getLocation());	
			}
			}
		}
	}
}

@EventHandler
public void onSpecSpeed(PlayerItemHeldEvent e){
	Player p = e.getPlayer();
	if(Main.spectatorplayer.contains(p)){
		int slot = e.getNewSlot()+1;
		float speed = (float)slot/10;
		p.setLevel(slot);
		p.setFlySpeed(speed);
	}
}

	
@EventHandler
public void onkl(PlayerInteractEvent e){
	if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
		if(e.getPlayer().getItemInHand().getType() == Material.BED){
		if(Main.ingame == false){
			Player p = e.getPlayer();
//			
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED+"Teamauswahl");
			for(String ss : getteams()){
				inv.addItem(createwool(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+ss+".fabe"))+umlautübersetzer(ss), colortranslator(Main.loc.getString(Main.mapname+"."+ss+".fabe")),playerinteam(ss)+"/"+Main.loc.getString(Main.mapname+".playerinteam")));
			}
			p.openInventory(inv);
//			
		}
		}
	}
}

public static ItemStack createwool(String title, DyeColor color, String lore){
	@SuppressWarnings("deprecation")
	ItemStack item = new ItemStack(Material.WOOL, 1, color.getData());
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(title);
	ArrayList<String> list = new ArrayList<>();
	list.add(lore);
	meta.setLore(list);
	item.setItemMeta(meta);
	return item;
}

public static List<String> getteams(){
	List<String> list = Main.loc.getStringList(Main.mapname+".teams");
	return list;
}

public static String umlautübersetzer(String s){
	String r = s;
	if(s.contains("ue")){
	r = s.replace("ue", "ü");
	}
	return r;
}

public static DyeColor colortranslator(String s){
	DyeColor color = null;
	if(s.equals("&4")){
		color = DyeColor.RED;
	}else if(s.equals("&c")){
		color = DyeColor.RED;
	}else if(s.equals("&e")){
		color = DyeColor.YELLOW;
	}else if(s.equals("&6")){
		color = DyeColor.YELLOW;
	}else if(s.equals("&2")){
		color = DyeColor.GREEN;
	}else if(s.equals("&a")){
		color = DyeColor.GREEN;
	}else if(s.equals("&b")){
		color = DyeColor.LIGHT_BLUE;
	}else if(s.equals("&3")){
		color = DyeColor.LIGHT_BLUE;
	}else if(s.equals("&1")){
		color = DyeColor.BLUE;
	}else if(s.equals("&9")){
		color = DyeColor.PURPLE;
	}else if(s.equals("&d")){
		color = DyeColor.PINK;
	}else if(s.equals("&5")){
		color = DyeColor.PURPLE;
	}else if(s.equals("&f")){
		color = DyeColor.WHITE;
	}else if(s.equals("&7")){
		color = DyeColor.GRAY;
	}else if(s.equals("&8")){
		color = DyeColor.GRAY;
	}else if(s.equals("&0")){
		color = DyeColor.BLACK;
	}
	return color;
}


static int teams;
public static void startteamshower(){
	teams = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
		@Override
		public void run() {
			for(Player pp: Bukkit.getOnlinePlayers()){
				if(Main.teamname.containsKey(pp)){
					if(Main.teamname.get(pp).equals("Spectator")){
					ActionBar.sendActionBar(pp, ChatColor.GRAY+"Spectator");
					}else{
					ActionBar.sendActionBar(pp, ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".fabe")+"Team "+Main.teamname.get(pp)));
					}
				}else{
					ActionBar.sendActionBar(pp, "Wähle ein Team aus");
				}
			}
		}
	}, 20, 20);
}

public static void stopteamshower(){
	Bukkit.getScheduler().cancelTask(teams);
}


public static String umlautentferner(String s){
		String r = s;
		if(s.contains("ü")){
		r = s.replace("ü", "ue");
		}
		return r;
}
//=====================================================================================================

public static HashMap<Player,String> bedwarsteam = new HashMap<>();

public static void setrandomteam(){
	for(Player pp: Bukkit.getOnlinePlayers()){
		if(!Main.teamname.containsKey(pp)){
			String s = umlautübersetzer(getfreeteam());
			Main.teamname.put(pp, s);
			bedwarsteam.put(pp, s);
		}
	}
}

public static String getfreeteam(){
	String teamname = "";
	Map<String, Integer> startMap = getammountlist();
    Set<String> keys = startMap.keySet();
    TreeMap<Integer, Set<String>> treeMap = new TreeMap<Integer, Set<String>>();
    for (String key : keys) {
        int value = startMap.get(key);
        Set<String> values;
        if(treeMap.containsKey(value)){
            values = treeMap.get(value);
            values.add(key);
        } else {
            values = new HashSet<String>();
            values.add(key);
        }
        treeMap.put(value, values);
    }
    List<String> list = new ArrayList<>();
    Set<Integer> treeValues = treeMap.keySet();
    for (Integer integer : treeValues) {
        Set<String> values = treeMap.get(integer);
        for (String string : values) {
            list.add(string);
        }
        System.out.println();
    }
	teamname = list.get(0);
	return teamname;
}

public static String getemtyteam(){
	String teamname = "";
	Map<String, Integer> startMap = getammountlist();
    Set<String> keys = startMap.keySet();
    TreeMap<Integer, Set<String>> treeMap = new TreeMap<Integer, Set<String>>();
    for (String key : keys) {
        int value = startMap.get(key);
        Set<String> values;
        if(treeMap.containsKey(value)){
            values = treeMap.get(value);
            values.add(key);
        } else {
            values = new HashSet<String>();
            values.add(key);
        }
        treeMap.put(value, values);
    }
    List<String> list = new ArrayList<>();
    Set<Integer> treeValues = treeMap.keySet();
    for (Integer integer : treeValues) {
        Set<String> values = treeMap.get(integer);
        for (String string : values) {
            list.add(string);
        }
        System.out.println();
    }
    
    for(String ss:list){
    	if(playerinteam(ss)==0){
    		teamname = ss;
    		break;
    	}else{
    		return null;
    	}
    }
	return teamname;
}

public static HashMap<String, Integer> getammountlist(){
	HashMap<String, Integer> list = new HashMap<>();
	List<String> teams = getteams();
	for(String s : teams){
		list.put(umlautentferner(s), playerinteam(umlautübersetzer(s)));
	}
	return list;
}

public static Integer playerinteam(String teamname){
	int i = 0;
	for(Player pp:Bukkit.getOnlinePlayers()){
		if(Main.teamname.containsKey(pp)){
			if(Main.teamname.get(pp).equals(umlautübersetzer(teamname))){
				i++;
			}
		}
	}
	return i;
}


@EventHandler
public void onchange(WeatherChangeEvent e){
	e.setCancelled(true);
}
@EventHandler
public void onTNT(EntityExplodeEvent e){

if(e.getEntityType().equals(EntityType.PRIMED_TNT)){ //Wenn TNT
//Block soll NICHT kaputt gehen
e.blockList().clear();
}
}
public static ArrayList<String>teamswithbed = new ArrayList<>();

@EventHandler
public void onda(EntityDamageEvent e){
	if(e.getEntity().getType() == EntityType.VILLAGER){
		e.setCancelled(true);
	}
}

public static void startbronzespawner(){
	Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
		@Override
		public void run() {
			for(int i = 0;Main.loc.getString(Main.mapname+".spawner."+"Bronze"+i+".x")!=null;i++){
				Double x = Main.loc.getDouble(Main.mapname+".spawner."+"Bronze"+i+".x");
				Double y = Main.loc.getDouble(Main.mapname+".spawner."+"Bronze"+i+".y");
				Double z = Main.loc.getDouble(Main.mapname+".spawner."+"Bronze"+i+".z");
				Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z);
				ItemStack item = new ItemStack(Material.CLAY_BRICK);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_RED+"Bronze");
				item.setItemMeta(meta);
				Bukkit.getWorld("gameworld").dropItemNaturally(loc, item);
			}
		}
	}, 10, 10);
}

public static void starteisenspawner(){
	Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
		@Override
		public void run() {
			for(int i = 0;Main.loc.getString(Main.mapname+".spawner."+"Eisen"+i+".x")!=null;i++){
				Double x = Main.loc.getDouble(Main.mapname+".spawner."+"Eisen"+i+".x");
				Double y = Main.loc.getDouble(Main.mapname+".spawner."+"Eisen"+i+".y");
				Double z = Main.loc.getDouble(Main.mapname+".spawner."+"Eisen"+i+".z");
				Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z);
				ItemStack item = new ItemStack(Material.IRON_INGOT);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GRAY+"Eisen");
				item.setItemMeta(meta);
				Bukkit.getWorld("gameworld").dropItemNaturally(loc, item);
			}
		}
	}, 20*10, 20*10);
}
	
	public static void startgoldspawner(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			@Override
			public void run() {
				for(int i = 0;Main.loc.getString(Main.mapname+".spawner."+"Gold"+i+".x")!=null;i++){
					Double x = Main.loc.getDouble(Main.mapname+".spawner."+"Gold"+i+".x");
					Double y = Main.loc.getDouble(Main.mapname+".spawner."+"Gold"+i+".y");
					Double z = Main.loc.getDouble(Main.mapname+".spawner."+"Gold"+i+".z");
					Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z);
					ItemStack item = new ItemStack(Material.GOLD_INGOT);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD+"Gold");
					item.setItemMeta(meta);
					Bukkit.getWorld("gameworld").dropItemNaturally(loc, item);
				}
			}
		}, 20*30, 20*30);
}
    public void Respawn(final Player player,int Time){
    	Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
			
			@Override
			public void run() {
				((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			}
		},Time);
    }
	
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
    	Player player = (Player)event.getEntity();
    	Respawn(player,20);
    }
    
    public static void canclemove(){
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			@Override
			public void run() {
				for(World w : Bukkit.getWorlds()){
					for(Entity villager : w.getEntities()){
						if(villager.getType() == EntityType.VILLAGER){
							villager.teleport(villager);
						}
					}
				}
			}
			
		}, 0L, 1L);
    }
    
    @EventHandler
    public void klick(PlayerInteractEvent e){
    	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
    		if(e.getClickedBlock().getType() == Material.WORKBENCH||e.getClickedBlock().getType() == Material.FURNACE){
    			e.setCancelled(true);
    		}
    	}
    }
    
    @SuppressWarnings("null")
	@EventHandler
    public void ondeath(PlayerDeathEvent e){
    	Player p = e.getEntity();
    	Player killer = p.getKiller();
    	e.getDrops().clear();
    	if(killer!=null){
    		e.setDeathMessage(null);
    		for(Player pp:Bukkit.getOnlinePlayers()){
    			pp.sendMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(p))+".fabe"))+Main.getrealname(p, pp)+ChatColor.GRAY+" wurde von "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(killer))+".fabe"))+Main.getrealname(killer, pp)+ChatColor.GRAY+" getötet!");
    		}
    		if(Main.teamname.containsKey(p)){
    			if(!teamswithbed.contains(Main.teamname.get(p))){
    				killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1F, 1F);
    				killer.sendMessage(Main.Prefix+ChatColor.YELLOW+"+1 "+ChatColor.GRAY+"Kills");
    				BedwarsStats.addKills(killer.getUniqueId().toString(), 1, killer.getName());
    				Main.playeringame.remove(p);
    				Main.teamname.remove(p);
    				de.gamelos.scoreboard.IngameBoard.setscoreboard();
    				isgameend();
    			}
    		}
    	}else{
    		if(hi.containsKey(p)&&Bukkit.getPlayer(hi.get(p))!=null){
    			if(!teamswithbed.contains(Main.teamname.get(p))){
    			Player killer1 = Bukkit.getPlayer(hi.get(p));
    			for(Player pp:Bukkit.getOnlinePlayers()){
    				pp.sendMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(p))+".fabe"))+Main.getrealname(p, pp)+ChatColor.GRAY+" wurde von "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(killer1))+".fabe"))+Main.getrealname(killer1, pp)+ChatColor.GRAY+" getötet!");
    			}
    			e.setDeathMessage(null);
    				killer1.playSound(killer1.getLocation(), Sound.LEVEL_UP, 1F, 1F);
        				killer1.sendMessage(Main.Prefix+ChatColor.YELLOW+"+1 "+ChatColor.GRAY+"Kills");
        				BedwarsStats.addKills(killer1.getUniqueId().toString(), 1, killer1.getName());
        				if(Main.playeringame.contains(p)){
        				Main.playeringame.remove(p);
        				}
        				if(Main.teamname.containsKey(p)){
        					Main.teamname.remove(p);	
        				}
        				de.gamelos.scoreboard.IngameBoard.setscoreboard();
        				isgameend();
    			}else{
    				Player killer1 = Bukkit.getPlayer(hi.get(p));
    				e.setDeathMessage(null);
    				for(Player pp:Bukkit.getOnlinePlayers()){
    					pp.sendMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(p))+".fabe"))+Main.getrealname(p, pp)+ChatColor.GRAY+" wurde von "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(killer1))+".fabe"))+Main.getrealname(killer1, pp)+ChatColor.GRAY+" getötet!");
    				}
    				de.gamelos.scoreboard.IngameBoard.setscoreboard();
    				isgameend();
    			}
    		}else{
    		e.setDeathMessage(null);
    		for(Player pp:Bukkit.getOnlinePlayers()){
    			pp.sendMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(p))+".fabe"))+Main.getrealname(p, pp)+ChatColor.GRAY+" ist gestorben");
    		}
    		if(Main.teamname.containsKey(p)){
    			if(!teamswithbed.contains(Main.teamname.get(p))){
    				Main.teamname.remove(p);
    				Main.playeringame.remove(p);
    				isgameend();
    			}
    		}
    		de.gamelos.scoreboard.IngameBoard.setscoreboard();
    		}
    	}
		if(Events.hi.containsKey(p)){
			Events.hi.remove(p);
		}
		if(!Main.spectatorplayer.contains(p)&&Events.isend == false &&Main.ingame == true) {
			BedwarsStats.addTode(p.getUniqueId().toString(), 1, p.getName());
		}
    }
 
//    ================================================================================
//    Check gameend
    @SuppressWarnings("deprecation")
	public static void isgameend(){
    	if(getactiveTeams()<=1){
    		Double x1 = Main.loc.getDouble("spawn.x");
    		Double y1 = Main.loc.getDouble("spawn.y");
    		Double z1 = Main.loc.getDouble("spawn.z");
    		Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
    		Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
    		World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
    		Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
    		String winner = getWinner();
    		isend = true;
    		endgame();
    		JaylosAPI.endunnick();
    		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable() {	
    			@Override
    			public void run() {
    					de.gamelos.scoreboard.IngameBoard.setscoreboard();
    			}
    		}, 20);
    		for(Player pp:Bukkit.getOnlinePlayers()){
    	    pp.teleport(lobbyspawn);
    	    pp.getInventory().clear();
    	    for(Player p: Bukkit.getOnlinePlayers()){
    	    	if(p!=pp){
    	    		p.showPlayer(pp);
    	    	}
    	    }
    	    
    	    for (PotionEffect effect : pp.getActivePotionEffects()) {
    			pp.removePotionEffect(effect.getType());
    		}
    	    
    	    if(Main.spectatorplayer.contains(pp)){
    	    	Main.spectatorplayer.remove(pp);
    	    	pp.setAllowFlight(false);
    	    }
    		TitleAPI.sendFullTitle(pp, 20, 50, 20, ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+winner+".fabe"))+"Team "+winner, "hat das Spiel gewonnen");
    		if(bedwarsteam.containsKey(pp)){
    			if(bedwarsteam.get(pp).equals(winner)){
    				BedwarsStats.addWins(pp.getUniqueId().toString(), 1, pp.getName());
    			}
    		}
    		}
				playendmusik();
    	Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
			@Override
			public void run() {
				feuerwerk(lobbyspawn, cololorchanger(Main.loc.getString(Main.mapname+"."+winner+".fabe")));
			}
    	},50);
    	}
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
    
    public static Integer getactiveTeams(){
    	int i = 0;
    	ArrayList<String>teams = new ArrayList<>();
    	for(Player pp:Bukkit.getOnlinePlayers()){
    		if(Main.teamname.containsKey(pp)&&(!Main.spectatorplayer.contains(pp))){
    		if(!teams.contains(Main.teamname.get(pp))){
    			teams.add(Main.teamname.get(pp));
    			i++;
    		}
    		}
    	}
    	return i;
    }
    
    public static String getWinner(){
    	String teamname = "";
    	for(Player pp:Bukkit.getOnlinePlayers()){
    		if(Main.teamname.containsKey(pp)&&(!Main.spectatorplayer.contains(pp))){
    		teamname = Main.teamname.get(pp);
    		}
    	}
    	return teamname;
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
    static SongPlayer sss;
	static Song ggg;
    public static void playendmusik(){
		Song s1 = NBSDecoder.parse(new File("plugins/songs/endsong.nbs"));
		SongPlayer sp = new com.xxmicloxx.NoteBlockAPI.RadioSongPlayer(s1);
		sp.setAutoDestroy(true);
		for(Player current : Bukkit.getOnlinePlayers()){
		sp.addPlayer(current);
		}
		sp.setPlaying(true);
		sss=sp;
		ggg=s1;
    }
    
	@EventHandler
	public void oninteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getPlayer().getItemInHand()!=null){
			if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD){
				
				if(p.getItemInHand().getAmount() == 1){
					p.getInventory().remove(Material.BLAZE_ROD);
				}else{
					p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
				}
				p.setFallDistance(0);
				int x = p.getLocation().getBlockX();
				int y = p.getLocation().getBlockY() - 1;
				int z = p.getLocation().getBlockZ();
				World world = p.getWorld();
				world.getBlockAt(x, y, z).setType(Material.GLASS);
				world.getBlockAt(x - 1, y, z).setType(Material.SANDSTONE);
				world.getBlockAt(x + 1, y, z).setType(Material.SANDSTONE);
				world.getBlockAt(x, y, z - 1).setType(Material.SANDSTONE);
				world.getBlockAt(x, y, z + 1).setType(Material.SANDSTONE);
			} 
		}
		}
	}
    public static HashMap<Player,String> hi= new HashMap<>();
	@EventHandler
	public void onhit(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
		Player p = (Player) e.getEntity();
		Player killer = (Player) e.getDamager();
		if(hi.containsKey(p)){
		hi.remove(p);
		hi.put(p, killer.getName());
		}else{
			hi.put(p, killer.getName());
		}
		

		}
	}
	
public static boolean onendgame = false;
	
	public static void endgame(){
		
		if(onendgame == false){
			onendgame = true;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			int i = 30;
			@Override
			public void run() {
				
				if(i>=-3){
					
					if(i == 30){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.GRAY+" Sekunden neu");
					}
					
					if(i==20){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.GRAY+" Sekunden neu");
					}
					
					if(i<=10){
						Bukkit.broadcastMessage(Main.Prefix+"Der Server startet in "+ChatColor.YELLOW+i+ChatColor.GRAY+" Sekunden neu");
					}
					
					if(i==0){
						for(Player pp : Bukkit.getOnlinePlayers()){
							pp.kickPlayer("");
						}
					}
					
				}else{
					Bukkit.shutdown();
				}
				
				
				i--;
			}
		}, 20, 20);
		}
	}
	
	@EventHandler
	public void onklick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
			if(Main.spectatorplayer.contains(p)){
				Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.YELLOW+"Teleporter");
				
				for(Player pp : Bukkit.getOnlinePlayers()){
					if(!Main.spectatorplayer.contains(pp)){
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
					skullmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+umlautentferner(Main.teamname.get(pp))+".fabe")+Main.getrealname(pp, p)));
					skullmeta.setOwner(pp.getName());
					skull.setItemMeta(skullmeta);
					inv.addItem(skull);
					
				}
				}
				p.openInventory(inv);
			}
			}
		}
	}
	@EventHandler
	public void onklick1(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.YELLOW+"Teleporter")){
			if(e.getCurrentItem().getItemMeta().getDisplayName()!=null){
			String s = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			Player pp = Bukkit.getPlayer(s);
			p.teleport(pp);
			p.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onbow(EntityShootBowEvent e){
			Player p = (Player) e.getEntity();
			ItemStack item = p.getItemInHand();
			Short s = item.getDurability();
			Short add = (short)8;
			Short end = (short) (s+add);
			item.setDurability(end);
	}
	
	@EventHandler
	public void od(EntityDamageByEntityEvent e){
		if(Main.spectatorplayer.contains(e.getDamager())){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void od(InventoryClickEvent e){
		if(Main.spectatorplayer.contains(e.getWhoClicked())){
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onhit1(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Arrow){
			if(e.getEntity() instanceof Player){
				Player p = (Player) e.getEntity();
				if(p.getInventory().getBoots()!=null){
					if(p.getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.YELLOW+"Antiknockbackschuhe")){
					Random r = new Random();
					int i = r.nextInt(2)+1;
					if(i==2){
						e.setCancelled(true);
					}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onfire(PlayerInteractEvent e){
		if(e.getClickedBlock()!=null){
		Location loc = e.getClickedBlock().getLocation();
		loc.add(0, 1, 0);
		if(loc.getBlock().getType()==Material.FIRE){
			e.setCancelled(true);
			loc.getBlock().setType(Material.FIRE);
		}
		}
	}
	
	
	@EventHandler
	public void onbuild(BlockPlaceEvent e){
		if(!Main.spectatorplayer.contains(e.getPlayer())){
			e.setBuild(true);
		}
	}
	
	@EventHandler
	public void onddd(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player p = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
			if(Main.teamname.containsKey(p)&&Main.teamname.containsKey(damager)){
				if(Main.teamname.get(p).equals(Main.teamname.get(damager))){
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onintera(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK||e.getAction() == Action.PHYSICAL){
		if(Main.spectatorplayer.contains(e.getPlayer())){
			e.setCancelled(true);
		}
		}
	}
	
	@EventHandler
	public void oninter(PlayerInteractEvent e){
		if(Main.ingame == false){
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getClickedBlock().getType() !=Material.WOOD_BUTTON &&e.getClickedBlock().getType() !=Material.STONE_BUTTON){
					e.setCancelled(true);
				}
			}else{
			if(e.getAction() != Action.PHYSICAL) {
				e.setCancelled(true);		
			}
			}
		}
	}

	 @EventHandler
	 public void oni(PlayerInteractAtEntityEvent e) {
		 if(e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			 e.setCancelled(true);
		 }
	 }
	
	@EventHandler
	public void onunnick(unNickEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable() {
			@Override
				public void run() {
				if(Main.ingame==false) {
					de.gamelos.scoreboard.Lobby.createScoreboard();
				}else {
					de.gamelos.scoreboard.IngameBoard.setscoreboard();
				}
				}
			}, 20);
	}
	
	@EventHandler
	public void onnick(PlayerNickEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable() {
			@Override
				public void run() {
				if(Main.ingame==false) {
					de.gamelos.scoreboard.Lobby.createScoreboard();
				}else {
					de.gamelos.scoreboard.IngameBoard.setscoreboard();
				}
				}
			}, 8);
	}
	
}
