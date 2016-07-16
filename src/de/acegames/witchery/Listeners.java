package de.acegames.witchery;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Listeners implements Listener{
	public static Main plugin;

	@EventHandler
	public void onWitcheryTableCraft(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Block blockplaced = event.getBlock();
		Material matplaced = blockplaced.getType();
		if(!Main.witcherscfg.contains(player.getName())){
			Main.witcherscfg.addDefault(player.getName(), Main.profcfg.get(player.getName()));
		}
	}
}
