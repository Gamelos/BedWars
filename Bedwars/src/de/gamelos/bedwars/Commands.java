package de.gamelos.bedwars;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(cmd.getName().equals("bw")){
			if(p.isOp()){
				if(args.length>= 1){
				if(args[0].equalsIgnoreCase("setspawn")){
					Location loc = p.getLocation();
					Main.loc.set("spawn.x", loc.getX());
					Main.loc.set("spawn.y", loc.getY());
					Main.loc.set("spawn.z", loc.getZ());
					Main.loc.set("spawn.yaw", loc.getYaw());
					Main.loc.set("spawn.pitch", loc.getPitch());
					Main.loc.set("spawn.world", loc.getWorld().getName());
					try {
						Main.loc.save(Main.locations);
						p.sendMessage(ChatColor.GREEN+"Der Spawn wurde erfolgreich gesetzt");	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(args[0].equalsIgnoreCase("addmap")){
					if(args.length == 4){
						int i = Main.getmapanzahl() + 1;
						Main.loc.set("Mapnames." + i, args[1]);
						Main.loc.set(args[1] + ".playerinteam", args[2]);
						Main.loc.set(args[1] + ".minplayers", args[3]);
						try {
							Main.loc.save(Main.locations);
							Bukkit.broadcastMessage(ChatColor.GREEN + "Die Map wurde erfolgreich gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
						}else{
							p.sendMessage(Main.Prefix+ChatColor.RED+"/bw addmap <mapname> <Spieler pro Team> <minplayers>");
						}
				}else if(args[0].equalsIgnoreCase("addteam")){
					if(args.length == 4){
						Main.loc.set(args[1] + "."+args[2]+".fabe", args[3]);
						Main.loc.set(args[1] + "."+args[2]+".spawnx", p.getLocation().getX());
						Main.loc.set(args[1] + "."+args[2]+".spawny", p.getLocation().getY());
						Main.loc.set(args[1] + "."+args[2]+".spawnz", p.getLocation().getZ());
						Main.loc.set("spawn.yaw", p.getLocation().getYaw());
						Main.loc.set("spawn.pitch", p.getLocation().getPitch());
//						
						List<String> liste = Main.loc.getStringList(Main.mapname+".teams");
						liste.add(args[2]);
						Main.loc.set(Main.mapname+".teams", liste);
//						
						try {
							Main.loc.save(Main.locations);
							Bukkit.broadcastMessage(ChatColor.GREEN + "Das Team wurde erfolgreich gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
						}else{
							p.sendMessage(Main.Prefix+ChatColor.RED+"/bw addteam <mapname> <Teamname> <Teamfabe>");
						}
				}else if(args[0].equalsIgnoreCase("setbed")){
					if(args.length==3){
						setbedloc1.add(p);
						mapname.put(p, args[1]);
						Teamname.put(p, args[2]);
					}else{
						p.sendMessage(Main.Prefix+ChatColor.RED+"/bw setbed <mapname> <Teamname>");
					}
				}else if(args[0].equalsIgnoreCase("setshop")){
					if(args.length==3){
						Main.loc.set(args[1] + ".shop."+args[2]+".x", p.getLocation().getX());
						Main.loc.set(args[1] + ".shop."+args[2]+".y", p.getLocation().getY());
						Main.loc.set(args[1] + ".shop."+args[2]+".z", p.getLocation().getZ());
						try {
							Main.loc.save(Main.locations);
							Bukkit.broadcastMessage(ChatColor.GREEN + "Der Shop wurde erfolgreich gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						p.sendMessage(Main.Prefix+ChatColor.RED+"/bw setshop <Mapname> <Teamname>");
					}
				}else if(args[0].equalsIgnoreCase("setspawner")){
					if(args.length==3){
						int i = getin(args[2], args[1]);
						Main.loc.set(args[1] + ".spawner."+args[2]+i+".x", p.getLocation().getX());
						Main.loc.set(args[1] + ".spawner."+args[2]+i+".y", p.getLocation().getY());
						Main.loc.set(args[1] + ".spawner."+args[2]+i+".z", p.getLocation().getZ());
						try {
							Main.loc.save(Main.locations);
							Bukkit.broadcastMessage(ChatColor.GREEN + "Der Spawner "+i+" wurde erfolgreich gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						p.sendMessage(Main.Prefix+ChatColor.RED+"/bw setspawner <Mapname> <Type>");
					}
				}else{
					p.sendMessage(Main.Prefix+"/bw setspawn");
					p.sendMessage(Main.Prefix+"/bw addmap <mapname> <Spieler pro Team> <minplayers>");
					p.sendMessage(Main.Prefix+"/bw addteam <mapname> <Teamname> <Teamfabe>");
					p.sendMessage(Main.Prefix+"/bw setbed <mapname> <Teamname>");
					p.sendMessage(Main.Prefix+"/bw setshop <Mapname> <Teamname>");
					p.sendMessage(Main.Prefix+"/bw setspawner <Mapname> <Bronze/Eisen/Gold>");
				}
				}else{
					p.sendMessage(Main.Prefix+"/bw setspawn");
					p.sendMessage(Main.Prefix+"/bw addmap <mapname> <Spieler pro Team> <minplayers>");
					p.sendMessage(Main.Prefix+"/bw addteam <mapname> <Teamname> <Teamfabe>");
					p.sendMessage(Main.Prefix+"/bw setbed <mapname> <Teamname>");
					p.sendMessage(Main.Prefix+"/bw setshop <Mapname> <Teamname>");
					p.sendMessage(Main.Prefix+"/bw setspawner <Mapname> <Bronze/Eisen/Gold>");
				}
			}else{
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du hast keine Rechte dazu");
			}
		}else if(cmd.getName().equals("promote")){
			if(p.hasPermission("rang.premium")){
				if(promote){
			p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du hast die Runde erfolgreich promotet");
			sendmsgtobungee("promote", p);
			promote = false;
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
				@Override
				public void run() {
					promote = true;
				}
			}, 6000);
				}else{
					p.sendMessage(Main.Prefix+ChatColor.RED+"Die Runde wurde erst vor kurzem promotet");
				}
			}else{
				p.sendMessage(Main.Prefix+ChatColor.RED+"Du brauchst Premium um dieses Feature nutzen zu können");
			}
		}
		return false;
	}
	
	public static Integer getin(String s, String Mapname){
		int i = 0;
		while(Main.loc.getString(Mapname+".spawner."+s+i+".x")!=null){
			i++;
		}
		return i;
	}
	
	public static ArrayList<Player>setbedloc1 = new ArrayList<>();
	public static ArrayList<Player>setbedloc2 = new ArrayList<>();
	public static HashMap<Player, String> mapname= new HashMap<>();
	public static HashMap<Player, String> Teamname= new HashMap<>();
	
	@EventHandler
	public void oni(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
		Player p = e.getPlayer();
		Location loc = e.getClickedBlock().getLocation();
		if(setbedloc1.contains(p)){
			setbedloc1.remove(p);
//			
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".x1", loc.getBlockX());
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".y1", loc.getBlockY());
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".z1", loc.getBlockZ());
			try {
				Main.loc.save(Main.locations);
				Bukkit.broadcastMessage(ChatColor.GREEN + "Das Bett 1 wurde erfolgreich gespeichert");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			
			setbedloc2.add(p);
		}else if(setbedloc2.contains(p)){
//			
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".x2", loc.getBlockX());
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".y2", loc.getBlockY());
			Main.loc.set(mapname.get(p)+"."+Teamname.get(p)+".z2", loc.getBlockZ());
			try {
				Main.loc.save(Main.locations);
				Bukkit.broadcastMessage(ChatColor.GREEN + "Das Bett 2 wurde erfolgreich gespeichert");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			
			Teamname.remove(p);
			mapname.remove(p);
			setbedloc2.remove(p);
		}
	}
	}
	
	boolean promote = true;
	
    public void sendmsgtobungee(String msg, Player p) {
    	ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("data");
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(Bukkit.getPluginManager().getPlugin("Bedwars"), "BungeeCord", b.toByteArray());
    }
	
}
