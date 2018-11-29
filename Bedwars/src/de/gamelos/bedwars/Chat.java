package de.gamelos.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import de.gamelos.jaylosapi.JaylosAPI;

@SuppressWarnings("deprecation")
public class Chat implements Listener {
	
	
	
	@EventHandler
	public void onchat(PlayerChatEvent e){
		Player p = e.getPlayer();
		e.setCancelled(true);
//		
		String msg = e.getMessage();
//		
		String s;
		if(msg.length() >3){
		s = msg.substring(0, 4);
		}else{
			s=msg;
		}
		
		if(Main.ingame==false){
			if(Main.teamname.containsKey(p)){
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".fabe"))+p.getName()+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+msg);
			}else{
					for(Player pp:Bukkit.getOnlinePlayers()){
						pp.sendMessage(JaylosAPI.getchatname(p, pp)+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+msg);
					}
			}
		}else if(Events.isend == true){
			for(Player pp:Bukkit.getOnlinePlayers()){
				pp.sendMessage(JaylosAPI.getchatname(p, pp)+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+msg);
			}
		}else{
		if(Main.teamname.containsKey(p)){
		if(!s.equalsIgnoreCase("@all")){
			for(Player pp : Main.playeringame){
				if(Main.teamname.containsKey(pp)){
					if(Main.teamname.get(pp).equals(Main.teamname.get(p))){
						pp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".fabe"))+p.getName()+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+msg);
					}
				}
			}
		}else{
			String sz = ChatColor.DARK_GRAY+"["+ChatColor.GRAY+"Global"+ChatColor.DARK_GRAY+"] ";
			String mmsg = msg.substring(4, msg.length());
			Bukkit.broadcastMessage(sz+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p))+".fabe"))+p.getName()+ChatColor.DARK_GRAY+" >>"+ChatColor.GRAY+mmsg);
		}
		}else{
			for(Player pp:Main.spectatorplayer){
				String speczeichen = ChatColor.GRAY+"["+ChatColor.RED+"✘"+ChatColor.GRAY+"]";
				pp.sendMessage(speczeichen+ChatColor.GRAY+p.getName()+ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+msg);
				}
		}
		
	}
	}
	
}
