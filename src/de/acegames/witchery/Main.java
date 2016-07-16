package de.acegames.witchery;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public final Logger mclogger = Logger.getLogger("Minecraft");
	public static Main plugin;
	
	/**
	 * UnterKlassen definieren:
	 * - Functions = Alle Funktionen
	 * - Files = Alle Files au�er config.yml und plugin.yml
	 */
	private Listeners listeners;
	private Functions functions;
	public static Files files;
	static File messages, witchers, professions;
	static FileConfiguration msgcfg, witcherscfg, profcfg;
	
	/**
	 * Strings definieren
	 */
	static String prefix = "§8[§5Witchery§8]§7 ";
	
	@Override
	public void onEnable(){
		//plugin.yml
		PluginDescriptionFile pdfFile = this.getDescription();
		
		//config.yml
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		//Extra Files erstellen
		try {
			Files.createFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Intialisiere die Listener Klasse
		Bukkit.getServer().getPluginManager().registerEvents(listeners, this);
		
		//Plugin-Aktivierungs-Nachricht
		if(this.getConfig().getBoolean("console-output-on-startup-and-end")){
			this.mclogger.info(pdfFile.getName()+pdfFile.getVersion()+" ist nun aktiv!");
		}
	}
	
	@Override
	public void onDisable(){
		//plugin.yml
		PluginDescriptionFile pdfFile = this.getDescription();
		
		
		
		//Plugin-Deaktivierungs-Nachricht
		if(this.getConfig().getBoolean("console-output-on-startup-and-end")){
		this.mclogger.info(pdfFile.getName()+pdfFile.getVersion()+" ist nun deaktiviert!");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("witcher")){
			if(args.length==3)
				if(args[0].equalsIgnoreCase("add")){
				if(Bukkit.getServer().getPlayer(args[1])!=null){
					functions.newWitcher(player, args[2]);
				}
			}
		}
		
		return false;
	}
	
	
}
