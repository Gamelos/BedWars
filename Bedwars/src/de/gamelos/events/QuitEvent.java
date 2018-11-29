package de.gamelos.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.gamelos.bedwars.BedwarsStats;
import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;
import de.gamelos.jaylosapi.JaylosAPI;

public class QuitEvent implements Listener {

	@EventHandler
	public void onquit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		Main.playeringame.remove(p);
		
		if(Main.spectatorplayer.contains(p)){
		e.setQuitMessage(null);
		}else if(Main.ingame == false){
			e.setQuitMessage(null);
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(Main.Prefix+JaylosAPI.getchatname(p, pp)+ChatColor.GRAY+" hat das Spiel verlassen");
			}
			if(Bukkit.getOnlinePlayers().size() <= Integer.parseInt(Main.loc.getString(Main.mapname + ".minplayers"))){
				Bukkit.broadcastMessage(Main.Prefix+ChatColor.RED+"Der Countdown wurde abgebrochen");
				Bukkit.getScheduler().cancelTask(Events.count);
				Events.startcount = false;
			}
		}else{
		
		if(Main.teamname.containsKey(e.getPlayer())){
		if(Events.hi.containsKey(p)&&Bukkit.getPlayer(Events.hi.get(p))!=null){
			Player killer = Bukkit.getPlayer(Events.hi.get(p));
			e.setQuitMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe")))+p.getName()+ChatColor.GRAY+" wurde von "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Main.teamname.get(killer)+".fabe"))+killer.getName()+ChatColor.GRAY+" getötet!");
    		de.gamelos.bedwars.BedwarsStats.addKills(killer.getUniqueId().toString(), 1, killer.getName());		
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1F, 1F);
    				killer.sendMessage(Main.Prefix+ChatColor.YELLOW+"+1 "+ChatColor.GRAY+"Kills");
    				Main.teamname.remove(p);
    				Main.playeringame.remove(p);
    				Events.isgameend();
    				de.gamelos.scoreboard.IngameBoard.setscoreboard();
		}else{
		e.setQuitMessage(Main.Prefix+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe")))+p.getName()+ChatColor.GRAY+" hat das Spiel verlassen");
		Main.teamname.remove(p);
		Main.playeringame.remove(p);
		Events.isgameend();
		de.gamelos.scoreboard.IngameBoard.setscoreboard();
		}
		}else{
			e.setQuitMessage(null);
		}
		}
		if(Main.teamname.containsKey(p)){
			Main.teamname.remove(p);
		}
		if(!Main.spectatorplayer.contains(p)&&Events.isend == false &&Main.ingame == true) {
			BedwarsStats.addTode(p.getUniqueId().toString(), 1, p.getName());
		}
	}
	
}
