package de.acegames.witchery;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	public static Main plugin;
	

	
	public static void createFile() throws IOException {
		Main.messages = new File(plugin.getDataFolder(), "messages.yml");
		Main.witchers = new File(plugin.getDataFolder(), "witchers.yml");
		Main.professions = new File(plugin.getDataFolder(), "professions.yml");
		
		
		if (!Main.messages.exists()) {
			Main.messages.getParentFile().mkdirs();
			plugin.saveResource("messages.yml", false);
			plugin.mclogger.info("Neues Nachrichten File erstellt!");
		}
		if (!Main.witchers.exists()) {
			Main.witchers.getParentFile().mkdirs();
			plugin.saveResource("witchers.yml", false);
			plugin.mclogger.info("Neues Zauberer Haupt-File erstellt!");
		}
		if (!Main.professions.exists()) {
			Main.professions.getParentFile().mkdirs();
			plugin.saveResource("professions.yml", false);
			plugin.mclogger.info("Neues ZauberSpezialisierungs File erstellt!");
		}

		Main.msgcfg = new YamlConfiguration();
		Main.witcherscfg = new YamlConfiguration();
		Main.profcfg = new YamlConfiguration();
		
		try {
			Main.msgcfg.load(Main.messages);
			Main.witcherscfg.load(Main.witchers);
			Main.profcfg.load(Main.professions);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
