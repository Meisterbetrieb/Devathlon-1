package de.acegames.witchery;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Functions {
	Main plugin;

	/**
	 * "Platzspar-Klasse" (Aussiedlung von code in diese klasse um Riesenklassen zu vermeiden)
	 * 
	 * @author Meisterbetrieb
	 */
	public void newWitcher(Player player, String profession) throws IOException{
		if(Main.profcfg.get("prof_" + profession)==null){
			player.sendMessage(Main.prefix+"§cUnbekannte Spezialisierung");
			player.sendMessage(Main.prefix+"§8Mache §7/§bwitcher professions §8um alle aufzulisten");
		}else{
			Main.profcfg.set(player.getName(), "prof_"+profession);
			Main.activecfg.set(player.getName(), false);
			Main.witcherscfg.set(player.getName(), true);
			
			Main.profcfg.save(Main.professions);
			Main.activecfg.save(Main.active);
			Main.witcherscfg.save(Main.witchers);
			ItemStack magicwand = new ItemStack(Material.BLAZE_ROD);
			ArrayList<String> lore = new ArrayList<String>();
			ItemMeta magicmeta = magicwand.getItemMeta();
			magicmeta.setDisplayName("§a§k++§7Zauberstab§a§k++");
			lore.clear();
			lore.add("1. Lege Zauberstab in Offhand");
			lore.add("2. Nach 5 Minuten, zurück in Mainhand");
			lore.add("3. wieder in Offhand");
			lore.add("4. ab schritt 2 wieder");
			magicmeta.setLore(lore);
			magicwand.setItemMeta(magicmeta);
			player.getInventory().addItem(magicwand);
		}
	}
	public void loadProfessions() throws IOException{
		if(!Main.professions.exists()){
			Files.createFile();
		}
		if(Main.profcfg.get("prof_speed")==null){ //Eisen
			Main.profcfg.set("prof_speed", true);
		}
		if(Main.profcfg.get("prof_poison")==null){ //Emerald
			Main.profcfg.set("prof_poison", true);
		}
		if(Main.profcfg.get("prof_strength")==null){ //redstone
			Main.profcfg.set("prof_strenth", true);
		}
		if(Main.profcfg.get("prof_regeneration")==null){ //Diamant
			Main.profcfg.set("prof_regeneration", true);
		}
		if(!Main.profcfg.contains("prof_water")){  //Lapis Lazuli
			Main.profcfg.set("prof_water", true);
		}
		Main.profcfg.save(Main.professions);
	}
	
	public ItemStack setColor(ItemStack itemStack, Color color){
        LeatherArmorMeta itemMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        itemMeta.setColor(color);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
	
	public void useWizardWand(Player player, ItemStack offhand, ItemStack mainhand) throws IOException{
		ItemStack magicwand = new ItemStack(Material.BLAZE_ROD);
		ArrayList<String> lore = new ArrayList<String>();
		ItemMeta magicmeta = magicwand.getItemMeta();
		magicmeta.setDisplayName("§a§k++§7Zauberstab§a§k++");
		lore.clear();
		lore.add("1. Lege Zauberstab in Offhand");
		lore.add("2. Nach 5 Minuten, zurück in Mainhand");
		lore.add("3. wieder in Offhand");
		lore.add("4. ab schritt 2 wieder");
		magicmeta.setLore(lore);
		magicwand.setItemMeta(magicmeta);
		if(offhand.isSimilar(magicwand)){
			Main.activecfg.set(player.getName(), true);
			Main.activecfg.save(Main.active);
		}else{
			Main.activecfg.set(player.getName(), false);
			Main.activecfg.save(Main.active);
		}
	}
	
	public void starteffect_itself(Player player) throws IOException{
		if(Main.activecfg.get(player.getName())==null){
			Main.activecfg.set(player.getName(), false);
			Main.activecfg.save(Main.active);
		}
		if(Main.activecfg.getBoolean(player.getName())==true){
		
		if(Main.profcfg.getString(player.getName())==null){
			player.sendMessage("§cDu hast keine ZauberFähigkeiten");
		}else if(Main.profcfg.getString(player.getName()).equalsIgnoreCase("prof_speed")){
			player.sendMessage("§aSpeed Zauber auf dich angewendet!");
			for(int i=0; i>100; i++){
				PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 5, 3, false);
				player.addPotionEffect(effect);
			}
		}else if(Main.profcfg.getString(player.getName()).equalsIgnoreCase("prof_regeneration")){
			player.sendMessage("§aRegenerations Zauber auf dich angewendet!");
			for(int i=0; i>100; i++){
				PotionEffect effect = new PotionEffect(PotionEffectType.REGENERATION, 5, 3, false);
				player.addPotionEffect(effect);
			}
		}else if(Main.profcfg.getString(player.getName()).equalsIgnoreCase("prof_water")){
			player.sendMessage("§aWasseraffinitäts Zauber auf dich angewendet!");
			for(int i=0; i>100; i++){
				PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 5, 3, false);
				player.addPotionEffect(effect);
			}
		}else if(Main.profcfg.getString(player.getName()).equalsIgnoreCase("prof_strength")){
			player.sendMessage("§aStaerke Zauber auf dich angewendet!");
			for(int i=0; i>100; i++){
				PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 3, false);
				player.addPotionEffect(effect);
			}
		}
			
		}
	
	}
}


