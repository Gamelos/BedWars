package de.gamelos.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;
import de.gamelos.jaylosapi.JaylosAPI;

public class IngameBoard {

	public static void setscoreboard(){
		for(Player p:Bukkit.getOnlinePlayers()){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("asf", "bbb");
		obj.setDisplayName(ChatColor.BOLD+"Jaylos.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Team Spectator = board.registerNewTeam("002Spectator");
		Spectator.setPrefix(ChatColor.GRAY+"");
		Spectator.setCanSeeFriendlyInvisibles(true);
		for(Player current: Bukkit.getOnlinePlayers()) {
			if(!Main.spectatorplayer.contains(current)){
			if(Main.teamname.containsKey(current)){
				if(board.getTeam("001"+Main.teamname.get(current))!=null){
					JaylosAPI.setteam(board.getTeam("001"+Main.teamname.get(current)), current, p);
				}else{
				Team team = board.registerNewTeam("001"+Main.teamname.get(current));
				team.setPrefix(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(current))+".fabe"))+"");
				team.setAllowFriendlyFire(false);
						JaylosAPI.setteam(team, current, p);
				}
			}else{
						JaylosAPI.setteam(Spectator, current, p);
			}
			}else{
						JaylosAPI.setteam(Spectator, current, p);
			}
		}
//		
		for(String ss:Events.getteams()){
			if(Events.teamswithbed.contains(Events.umlautübersetzer(ss))){
				String color = ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(ss)+".fabe"));
				Score five = obj.getScore(ChatColor.RED+"❤"+color+" Team "+Events.umlautübersetzer(ss));	
				five.setScore(Events.playerinteam(ss));
			}else{
			String color = ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(ss)+".fabe"));
			Score five = obj.getScore(ChatColor.GRAY+"❤"+color+" Team "+Events.umlautübersetzer(ss));	
			five.setScore(Events.playerinteam(ss));
			}
		}
		
		p.setScoreboard(board);
		}
		
	}
	
}
