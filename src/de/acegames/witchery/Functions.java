package de.acegames.witchery;

import java.io.IOException;

import org.bukkit.entity.Player;

public class Functions {
	Main plugin;

	public void newWitcher(Player player, String profession){
		if(!Main.profcfg.contains(profession)){
			player.sendMessage(Main.prefix+"§cUnbekannte Spezialisierung");
			player.sendMessage(Main.prefix+"§8Mache §7/§bwitcher professions §8um alle aufzulisten");
		}else{
			
		}
	}
	public void loadProfessions() throws IOException{
		if(!Main.professions.exists()){
			Files.createFile();
		}
		if(!Main.profcfg.contains("prof_speed")){ //Eisen
			Main.profcfg.set("prof_speed", true);
		}
		if(!Main.profcfg.contains("prof_poison")){ //Emerald
			Main.profcfg.set("prof_poison", true);
		}
		if(!Main.profcfg.contains("prof_strength")){ //redstone
			Main.profcfg.set("prof_strenth", true);
		}
		if(!Main.profcfg.contains("prof_regeneration")){ //Diamant
			Main.profcfg.set("prof_regeneration", true);
		}
		if(!Main.profcfg.contains("prof_water")){  //Lapis Lazuli
			Main.profcfg.set("prof_water", true);
		}
		Main.profcfg.save(Main.professions);
	}
}
