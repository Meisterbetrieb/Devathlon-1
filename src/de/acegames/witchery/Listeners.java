package de.acegames.witchery;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent.ChangeReason;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener{
	public static Main plugin;
	public static Functions functions;
	public static int count;

	@EventHandler
	public void onWandselect(PlayerSwapHandItemsEvent event) throws IOException{
		Player player = event.getPlayer();
		ItemStack offhand = event.getOffHandItem();
		ItemStack mainhand = event.getMainHandItem();
		
		functions.useWizardWand(player, offhand, mainhand);
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		count = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				try {
					functions.starteffect_itself(player);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}, 20, 20);
	}
	
	@EventHandler
	public void onWitcheryTableCraft(BlockPlaceEvent event) throws IOException{
		Player player = event.getPlayer();
		Block blockplaced = event.getBlock();
		Material matplaced = blockplaced.getType();
		Location placedloc = new Location(player.getWorld(), blockplaced.getX(), blockplaced.getY()-1, blockplaced.getZ());
		Location placedloc2 = new Location(player.getWorld(), blockplaced.getX(), blockplaced.getY()-2, blockplaced.getZ());
		
		if(placedloc2.getBlock().getType()==Material.SOUL_SAND){
			if(placedloc.getBlock().getType()==Material.EMERALD_BLOCK){
				if(matplaced== Material.PURPUR_SLAB){
					/**
					 * Hat funktioniert
					 * 
					 */
					ItemStack helmet_witcher = new ItemStack(Material.LEATHER_HELMET);
					ItemMeta meta_helmet_witcher = helmet_witcher.getItemMeta();
					functions.setColor(helmet_witcher, Color.PURPLE);
					meta_helmet_witcher.setDisplayName("§a§k++§5Zauberer§a§k++§r");
					ArrayList<String> lore_helmet_witcher = new ArrayList<String>();
					lore_helmet_witcher.add("Spezialisierung: wähle an den Kesseln!");
					meta_helmet_witcher.setLore(lore_helmet_witcher);
					helmet_witcher.setItemMeta(meta_helmet_witcher);
					
					
					
					player.sendMessage(Main.prefix+"§a!");
					if(!Main.witcherscfg.contains(player.getName())){
						Main.witcherscfg.set(player.getName(), Main.profcfg.getString(player.getName()));
					}
					player.getInventory().setHelmet(helmet_witcher);
					functions.loadProfessions();
					if(Main.profcfg.getString(player.getName())!=null){
						Main.profcfg.set(player.getName(), null);
					}
					
					
					
					
				}
			}
		}
			
		}
		
	
	@EventHandler
	public void professionWaehlen(CauldronLevelChangeEvent event) throws IOException{
		Entity entity = event.getEntity();
		Player player = Bukkit.getServer().getPlayer(entity.getName());
		ItemStack oldhelmet = player.getInventory().getItemInOffHand();
		ItemStack wizardhelmet = new ItemStack(Material.LEATHER_HELMET);
		
		functions.setColor(wizardhelmet, Color.PURPLE);
		ItemMeta meta_wizardhelmet = wizardhelmet.getItemMeta();
		meta_wizardhelmet.setDisplayName("§a§k++§5Zauberer§a§k++§r");
		ArrayList<String> lore_helmet_witcher = new ArrayList<String>();
		lore_helmet_witcher.add("Spezialisierung: wähle an den Kesseln!");
		meta_wizardhelmet.setLore(lore_helmet_witcher);
		wizardhelmet.setItemMeta(meta_wizardhelmet);
		
		if(event.getReason()==ChangeReason.ARMOR_WASH){
			if(oldhelmet.isSimilar(wizardhelmet)){
				player.getInventory().remove(oldhelmet);
				Location loc = event.getBlock().getLocation();
				Location prof_block = new Location(loc.getWorld(), loc.getBlock().getX(), loc.getBlock().getY()-1, loc.getBlock().getZ());
				if(!Main.professions.exists()){ functions.loadProfessions(); }
				
				if(Main.profcfg.getString(player.getName())==null){
					Main.profcfg.set(player.getName(), null);
				}
				ItemStack prof_helmet = new ItemStack(Material.LEATHER_HELMET);
				ItemMeta prof_meta = prof_helmet.getItemMeta();
				prof_meta.setDisplayName("§a§k++§5Zauberer§a§k++§r");
				ArrayList<String> lore = new ArrayList<String>();
					if(prof_block.getBlock().getType()==Material.REDSTONE_BLOCK){
						Main.profcfg.set(player.getName(), "prof_strength");
						player.sendMessage(Main.prefix+"Du hast Die Fähigeit der Stärke erlernt!");
						
						lore.clear();
						lore.add("Spezialisierung: Stärke");
						prof_meta.setLore(lore);
						functions.setColor(prof_helmet, Color.RED);
						prof_helmet.setItemMeta(prof_meta);
						player.getInventory().setHelmet(prof_helmet);
					}
					if(prof_block.getBlock().getType()==Material.EMERALD_BLOCK){
						Main.profcfg.set(player.getName(), "prof_poison");
						player.sendMessage(Main.prefix+"Du hast Die Fähigeit der Vergiftung erlernt!");
						lore.clear();
						lore.add("Spezialisierung: Vergiftung");
						prof_meta.setLore(lore);
						functions.setColor(prof_helmet, Color.LIME);
						prof_helmet.setItemMeta(prof_meta);
						player.getInventory().setHelmet(prof_helmet);
					}
					if(prof_block.getBlock().getType()==Material.DIAMOND_BLOCK){
						Main.profcfg.set(player.getName(), "prof_regeneration");
						player.sendMessage(Main.prefix+"Du hast Die Fähigeit der Regeneration erlernt!");
						lore.clear();
						lore.add("Spezialisierung: Regeneration");
						prof_meta.setLore(lore);
						functions.setColor(prof_helmet, Color.AQUA);
						prof_helmet.setItemMeta(prof_meta);
						player.getInventory().setHelmet(prof_helmet);
					}
					if(prof_block.getBlock().getType()==Material.LAPIS_BLOCK){
						Main.profcfg.set(player.getName(), "prof_water");
						player.sendMessage(Main.prefix+"Du hast Die Fähigeit der Wasseraffinitaet erlernt!");
						lore.clear();
						lore.add("Spezialisierung: Wasseraffinität");
						prof_meta.setLore(lore);
						functions.setColor(prof_helmet, Color.BLUE);
						prof_helmet.setItemMeta(prof_meta);
						player.getInventory().setHelmet(prof_helmet);
					}
					if(prof_block.getBlock().getType()==Material.IRON_BLOCK){
						Main.profcfg.set(player.getName(), "prof_speed");
						player.sendMessage(Main.prefix+"Du hast Die Fähigeit der Schnelligkeit erlernt!");
						lore.clear();
						lore.add("Spezialisierung: Schnelligkeit");
						prof_meta.setLore(lore);
						functions.setColor(prof_helmet, Color.WHITE);
						prof_helmet.setItemMeta(prof_meta);
						player.getInventory().setHelmet(prof_helmet);
					}
				}
				
		}
	}
	}

