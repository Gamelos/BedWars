package de.gamelos.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.gamelos.bedwars.Events;
import de.gamelos.bedwars.Main;

public class TeamSelector implements Listener {
	@EventHandler
	public void onklick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.RED+"Teamauswahl")){
			if(e.getCurrentItem() != null){
				if(e.getCurrentItem().getType() ==Material.WOOL){
					String teamname = e.getCurrentItem().getItemMeta().getDisplayName();
					if(Events.playerinteam(ChatColor.stripColor(teamname))< Integer.parseInt(Main.loc.getString(Main.mapname + ".playerinteam"))){
					if(Main.teamname.containsKey(p)){
					if(ChatColor.stripColor(teamname) != Main.teamname.get(p)){
						Main.teamname.remove(p);
						Main.teamname.put(p, ChatColor.stripColor(teamname));
						p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du bist erfolgreich Team "+teamname+ChatColor.GREEN+" beigetreten!");
							de.gamelos.scoreboard.Lobby.createScoreboard();
						p.closeInventory();
					}else{
						p.sendMessage(Main.Prefix+ChatColor.RED+"Du bist bereits in diesem Team!");
						p.closeInventory();
					}
					}else{
						Main.teamname.put(p, ChatColor.stripColor(teamname));
						p.sendMessage(Main.Prefix+ChatColor.GREEN+"Du bist erfolgreich Team "+teamname+ChatColor.GREEN+" beigetreten!");
							de.gamelos.scoreboard.Lobby.createScoreboard();
						p.closeInventory();
					}
				}else{
					p.sendMessage(Main.Prefix+ChatColor.RED+"Dises Team ist voll!");
					p.closeInventory();
				}
				}
			}
		}
	}
}
