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
	 * - Files = Alle Files außer config.yml und plugin.yml
	 * 
	 * @author Meisterbetrieb
	 */
	private Listeners listeners;
	private Functions functions;
	public static Files files;
	static File messages, witchers, professions, active;
	static FileConfiguration msgcfg, witcherscfg, profcfg, activecfg;
	
	/**
	 * - Strings definieren
	 * - onEnable()
	 * - onDisable()
	 * @author Meisterbetrieb
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
	/**
	 * On Command Teil
	 * @param
	 * @author TheBozZ_99
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("witchery")){
			if(args.length==3){
				if(args[0].equalsIgnoreCase("add")){
					if(Bukkit.getServer().getPlayer(args[1])!=null){
						if(witcherscfg.get(player.getName())!=null){
							try {
								functions.newWitcher(player, args[2]);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			if(args.length==1){
				if(args[0].equalsIgnoreCase("help")){
					player.sendMessage(prefix+"§8~~~ §5Alle Befehle §8~~~");
					player.sendMessage(prefix+"§7/§bwitchery how §8|§4|§8| §eAnleitung zum Plugin");
					player.sendMessage(prefix+"§7/§bwitchery help §8|§4|§8| §eHier bist du gerade");
					player.sendMessage(prefix+"§7/§bwitchery add <Username> <Spezialität> §8|§4|§8| Cheater! ;D");
				}
				if(args[0].equalsIgnoreCase("how")){
					player.sendMessage(prefix+"§8~~~ §5Wie man ein guter Zauberer ist §8~~~");
					player.sendMessage(prefix+"§8[§51§7.§8]§b Im Tempel die Purpur Slab auf EmeraldBlock §7(§bThron§7)");
					player.sendMessage(prefix+"§8[§52§7.§8]§b Wähle deine Spezialität an den Cauldrons (Deinen Hut waschen)");
					player.sendMessage(prefix+"§8[§53§7.§8]§b Du bekommst einen Zauberstab §7O.o");
					player.sendMessage(prefix+"§8[§5END§8]§b Weiter gehts wenn du dir die lore des Zauberstabs ansiehst");
				}
				
			}
		}
		
		return false;
	}
	
	
}
