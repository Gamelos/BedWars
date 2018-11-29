package de.gamelos.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;

public class RespawnEvent implements Listener{
	//=======================RESPAWN======================================
	@EventHandler
	public void onrespawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		if(Main.teamname.containsKey(p)){
			if(Events.teamswithbed.contains(Main.teamname.get(p))){
				double x = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawnx");
				double y = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawny");
				double z = Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".spawnz");
				float yaw = (float) Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".yaw");
				float pitch = (float) Main.loc.getDouble(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".pitch");
				Location loc = new Location(Bukkit.getWorld("gameworld"), x, y, z, yaw, pitch);
				e.setRespawnLocation(loc);
				p.sendMessage(Main.Prefix+"Du konntest Respawnen weil dein Bett noch nicht abgebaut wurde!");
			}else{
				Main.teamname.remove(p);
				Main.playeringame.remove(p);
				Events.isgameend();
				e.getPlayer().setHealth(20);
				e.getPlayer().setFoodLevel(20);
				if(Events.isend){
				for(Player pp : Main.playeringame){
					pp.hidePlayer(e.getPlayer());
				}
				Main.spectatorplayer.add(e.getPlayer());
				Events.setspectator(e.getPlayer());
					Double x1 = Main.loc.getDouble("spawn.x");
					Double y1 = Main.loc.getDouble("spawn.y");
					Double z1 = Main.loc.getDouble("spawn.z");
					Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
					Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
					World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
					Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
					e.setRespawnLocation(lobbyspawn);
				}else{
				e.setRespawnLocation(Bukkit.getWorld("gameworld").getSpawnLocation());
				for(Player pp : Main.playeringame){
					pp.hidePlayer(e.getPlayer());
				}
				Events.setspectator(e.getPlayer());
				}
			}
		}else{
			Events.isgameend();
			e.getPlayer().setHealth(20);
			e.getPlayer().setFoodLevel(20);
			if(Events.isend){
				Double x1 = Main.loc.getDouble("spawn.x");
				Double y1 = Main.loc.getDouble("spawn.y");
				Double z1 = Main.loc.getDouble("spawn.z");
				Float yaw1 = (float) Main.loc.getDouble("spawn.yaw");
				Float pitch1 = (float) Main.loc.getDouble("spawn.pitch");
				World w1 = Bukkit.getWorld(Main.loc.getString("spawn.world"));
				Location lobbyspawn = new Location(w1,x1,y1,z1,yaw1,pitch1);
				e.setRespawnLocation(lobbyspawn);
				e.getPlayer().setAllowFlight(false);
				e.getPlayer().getInventory().clear();
			}else{
			e.setRespawnLocation(Bukkit.getWorld("gameworld").getSpawnLocation());
			for(Player pp : Main.playeringame){
				pp.hidePlayer(e.getPlayer());
			}
			Events.setspectator(e.getPlayer());
			}
		}
	}
}
