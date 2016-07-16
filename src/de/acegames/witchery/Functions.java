package de.acegames.witchery;

import org.bukkit.entity.Player;

public class Functions {
	Main plugin;

	public void newWitcher(Player player, String profession){
		if(!Main.profcfg.contains(profession)){
			player.sendMessage(Main.prefix+"§cUnbekannte Spezialisierung");
			player.sendMessage(Main.prefix+"§8Mache §7/§bwitcher professions §8um alle aufzuliste");
		}else{
			
		}
	}
}
