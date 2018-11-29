package de.gamelos.bedwars;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;


public class Motd implements Listener {

	@EventHandler
	public void onping(ServerListPingEvent e){
		e.setMotd(Main.mapname);
	}
	
}
