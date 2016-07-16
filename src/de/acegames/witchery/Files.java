package de.acegames.witchery;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	public static Main plugin;
	
	private static File file;
	private static FileConfiguration filecfg;

	
	public static void createFile() throws IOException {
		file = new File(plugin.getDataFolder(), "Permissions_unused-file.yml");
		
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			plugin.saveResource("Commands_unused-file.yml", false);
		}

		filecfg = new YamlConfiguration();
		
		try {
			filecfg.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
