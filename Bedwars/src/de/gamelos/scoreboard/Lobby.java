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

public class Lobby {
	@SuppressWarnings("deprecation")
	public static void createScoreboard(){
		for(Player p:Bukkit.getOnlinePlayers()){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "bbb");
		obj.setDisplayName(ChatColor.BOLD+"Jaylos.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
//		
		Team Spieler = board.registerNewTeam("Spieler");
		Spieler.setPrefix(ChatColor.GRAY+"");
		
		for(Player current: Bukkit.getOnlinePlayers()) {
			if(Main.teamname.containsKey(current)){
				if(board.getTeam(Main.teamname.get(current))!=null){
					board.getTeam(Main.teamname.get(current)).addPlayer(current);
				}else{
				Team team = board.registerNewTeam(Main.teamname.get(current));
				team.setPrefix(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(current))+".fabe"))+"");
						JaylosAPI.setteam(team, current, p);
				}
			}else{
				JaylosAPI.setteam(Spieler, current, p);
			}
		}
//	
//					
					int minplayer = Integer.parseInt(Main.loc.getString(Main.mapname+".minplayers"));
					
//					
					Score five = obj.getScore("§7Spielstart:");
					Score four = obj.getScore(ChatColor.RED+"ab "+ChatColor.YELLOW+minplayer+ChatColor.RED+" Spieler");
					Score space = obj.getScore(" ");
					Score tree = obj.getScore("§7Map:");
					Score two = obj.getScore("§c"+Main.mapname);
					
					tree.setScore(4);
					two.setScore(3);
					space.setScore(2);
					five.setScore(1);
					four.setScore(0);
					p.setScoreboard(board);
					}
//					
		}
}
