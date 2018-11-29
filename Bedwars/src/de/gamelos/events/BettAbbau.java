package de.gamelos.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;

public class BettAbbau implements Listener{
	//=======================================BETTABBAU==================================
	@EventHandler
	public void onbreake(BlockBreakEvent e){
		if(e.getBlock().getLocation().getWorld().getName().equals("gameworld")){
			if(e.getBlock().getType() == Material.BED_BLOCK){
				
				String teambed = "";
				for(String ss:Events.getteams()){
					int x = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".x1")));	
					int y = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".y1")));	
					int z = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".z1")));	
					int x2 = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".x2")));	
					int y2 = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".y2")));	
					int z2 = Integer.parseInt((Main.loc.getString(Main.mapname+"."+ss+".z2")));
					if(e.getBlock().getX()==x&&e.getBlock().getY()==y&&e.getBlock().getZ()==z){
						teambed = ss;
					}else if(e.getBlock().getX()==x2&&e.getBlock().getY()==y2&&e.getBlock().getZ()==z2){
						teambed = ss;
					}
				}
				
				if(Main.teamname.get(e.getPlayer()).equals(Events.umlautübersetzer(teambed))){
					e.setCancelled(true);
					e.getPlayer().sendMessage(Main.Prefix+ChatColor.RED+"Du kannst dein eigenes Bett nicht abbauen!");
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1F, 1F);
				}else{
				e.setExpToDrop(0);
				e.getBlock().setType(Material.AIR);
				for(org.bukkit.entity.Entity ent : Bukkit.getWorld("gameworld").getEntities()){
					if(ent.getType() == EntityType.DROPPED_ITEM){
						if(ent.getName().equals("item.item.bed")){
						ent.remove();
						}
					}
				}
				Player p = e.getPlayer();
				for(Player pp : Bukkit.getOnlinePlayers()){
					pp.sendMessage(Main.Prefix+"Das Bett von Team "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(teambed)+".fabe"))+Events.umlautübersetzer(teambed)+ChatColor.GRAY+" wurde von "+ChatColor.translateAlternateColorCodes('&', Main.loc.getString(Main.mapname+"."+Events.umlautentferner(Main.teamname.get(p)+".fabe"))+Main.getrealname(p, pp)+ChatColor.GRAY+" abgebaut"));
				}
				de.gamelos.bedwars.BedwarsStats.addBetten(p.getUniqueId().toString(), 1, p.getName());
				for(Player current : Bukkit.getOnlinePlayers()){
					current.playSound(current.getLocation(), Sound.WITHER_DEATH, 1F, 1F);	
				}
				Events.teamswithbed.remove(Events.umlautübersetzer(teambed));
				de.gamelos.scoreboard.IngameBoard.setscoreboard();
				}
			}
		}
	}
}
