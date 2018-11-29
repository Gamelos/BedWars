package de.gamelos.bedwars;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import net.md_5.bungee.api.ChatColor;

public class Deaktivieren implements Listener {

	public static ArrayList<Material> allow = new ArrayList<>();
	
	public static void setitems(){
		allow.add(Material.SANDSTONE);
		allow.add(Material.GLASS);
		allow.add(Material.ENDER_STONE);
		allow.add(Material.PACKED_ICE);
		allow.add(Material.IRON_BLOCK);
		allow.add(Material.LADDER);
		allow.add(Material.CHEST);
		allow.add(Material.ENDER_CHEST);
		allow.add(Material.BED_BLOCK);
		allow.add(Material.WEB);
		allow.add(Material.AIR);
		allow.add(Material.DOUBLE_PLANT);
	}
	
	@EventHandler
	public void onbrake(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(!allow.contains(e.getBlock().getType())){
		p.sendMessage(Main.Prefix+ChatColor.RED+"Du darfst den Block "+ChatColor.YELLOW+e.getBlock().getType().toString()+ChatColor.RED+" nicht abbauen!");	
		e.setCancelled(true);
		}
		if(e.getBlock().getType() == Material.DOUBLE_PLANT){
			e.getBlock().setType(Material.AIR);
		}
	}
	
	@EventHandler
	public void onb(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(e.getBlock().getType() == Material.IRON_BLOCK){
			if(p.getItemInHand()!=null){
			if(p.getItemInHand().getType() ==Material.WOOD_PICKAXE){
				e.setCancelled(true);
			}
			}else{
				e.setCancelled(true);
			}
		}
		
		if(e.getBlock().getType() == Material.WEB){
			if(p.getItemInHand()!=null){
			if(p.getItemInHand().getType()!=Material.WOOD_SWORD&&p.getItemInHand().getType()!=Material.STONE_SWORD&&p.getItemInHand().getType()!=Material.IRON_SWORD){
				e.setCancelled(true);
			}
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onbbr(BlockPlaceEvent e){
		if(e.getBlock().getType() == Material.TNT){
			 e.getBlock().setType(Material.AIR);
			    TNTPrimed tnt = (TNTPrimed) e.getBlock().getWorld().spawnEntity (e.getBlock().getLocation (), EntityType.PRIMED_TNT);
			  tnt.setFuseTicks(80);
		}
	}
	
}
