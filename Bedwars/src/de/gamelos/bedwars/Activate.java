package de.gamelos.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class Activate implements Listener {
	@EventHandler
	public void oninte(PlayerInteractAtEntityEvent e){
		Player p = e.getPlayer();
		Entity entity = (Entity) e.getRightClicked();
		if(entity.getType() == EntityType.VILLAGER){
			if(!Main.teamname.get(p).equals("Spectator")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Bedwars"), new Runnable(){
				@Override
				public void run() {
					Shop.open(p);
				}
			},0);
			}
		}
	}
}
