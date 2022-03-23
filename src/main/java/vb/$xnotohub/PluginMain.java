package vb.$xnotohub;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(GUIManager.getInstance(), this);
		GUIManager.getInstance().register("", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(new GUIIdentifier(""),
						((int) (Object) null), ((java.lang.String) null));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}, true);
		GUIManager.getInstance().register("1", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(new GUIIdentifier("1"),
						((int) (Object) null), ((java.lang.String) null));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}, true);
		try {
			PluginMain.getInstance().getLogger().info("xNotoPlugin Has been Enabled!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		GUIManager.getInstance().register("serverselector", guiPlayer -> {
			try {
				org.bukkit.inventory.Inventory guiInventory = Bukkit.createInventory(
						new GUIIdentifier("serverselector"), ((int) (27d)),
						ChatColor.translateAlternateColorCodes('&', "&e&lServer Selector"));
				guiInventory.setItem(((int) (11d)), PluginMain.getNamedItemWithLore(
						((org.bukkit.Material) org.bukkit.Material.GRASS_BLOCK),
						ChatColor.translateAlternateColorCodes('&', "&a&lSKYBLOCK"), new ArrayList(Arrays
								.asList(ChatColor.translateAlternateColorCodes('&', "&7Click to join Skyblock")))));
				guiInventory.setItem(((int) (15d)), PluginMain.getNamedItemWithLore(
						((org.bukkit.Material) org.bukkit.Material.TNT),
						ChatColor.translateAlternateColorCodes('&', "&c&lFACTIONS"), new ArrayList(Arrays
								.asList(ChatColor.translateAlternateColorCodes('&', "&7Click to join Factions")))));
				return guiInventory;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}, true);
	}

	@Override
	public void onDisable() {
		try {
			PluginMain.getInstance().getLogger().info("xNotoPlugin Has Been Disabled");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler
	public void onGUIClick(GUIClickEvent event) throws Exception {
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event1(org.bukkit.event.entity.EntityDamageEvent event) throws Exception {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event2(org.bukkit.event.block.BlockPlaceEvent event) throws Exception {
		if (PluginMain.checkEquals(
				((org.bukkit.GameMode) ((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event
						.getPlayer())).getGameMode()),
				((org.bukkit.GameMode) org.bukkit.GameMode.SURVIVAL))) {
			event.setCancelled(true);
		}
		((org.bukkit.entity.Player) event.getPlayer()).spigot().sendMessage(
				net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
						ChatColor.translateAlternateColorCodes('&', "&4&l>> &cYou cannot place block! &4&l<<")));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event3(org.bukkit.event.block.BlockBreakEvent event) throws Exception {
		if (PluginMain.checkEquals(
				((org.bukkit.GameMode) ((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event
						.getPlayer())).getGameMode()),
				((org.bukkit.GameMode) org.bukkit.GameMode.SURVIVAL))) {
			event.setCancelled(true);
		}
		((org.bukkit.entity.Player) event.getPlayer()).spigot().sendMessage(
				net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
						ChatColor.translateAlternateColorCodes('&', "&4&l>> &cYou cannot break block! &4&l<<")));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event4(org.bukkit.event.player.PlayerJoinEvent event) throws Exception {
		event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a+&7] "));
		((org.bukkit.inventory.Inventory) ((org.bukkit.inventory.InventoryHolder) (Object) ((org.bukkit.entity.Player) event
				.getPlayer())).getInventory()).setItem(
						((int) (0d)),
						PluginMain.getNamedItemWithLore(((org.bukkit.Material) org.bukkit.Material.COMPASS),
								ChatColor.translateAlternateColorCodes('&', "&e&lServer Selector"),
								new ArrayList(Arrays.asList(ChatColor.translateAlternateColorCodes('&',
										"&7Right-click to open the server selector")))));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event5(org.bukkit.event.player.PlayerInteractEvent event) throws Exception {
		if (PluginMain.checkEquals(((org.bukkit.inventory.ItemStack) event.getItem()), PluginMain.getNamedItemWithLore(
				((org.bukkit.Material) org.bukkit.Material.COMPASS),
				ChatColor.translateAlternateColorCodes('&', "&e&lServer Selector"), new ArrayList(Arrays.asList(
						ChatColor.translateAlternateColorCodes('&', "&7Right-click to open the server selector")))))) {
			GUIManager.getInstance().open("serverselector", ((org.bukkit.entity.Player) event.getPlayer()));
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event6(org.bukkit.event.inventory.InventoryMoveItemEvent event) throws Exception {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event7(org.bukkit.event.player.PlayerDropItemEvent event) throws Exception {
		if (PluginMain.checkEquals(
				((org.bukkit.GameMode) ((org.bukkit.entity.HumanEntity) (Object) ((org.bukkit.entity.Player) event
						.getPlayer())).getGameMode()),
				((org.bukkit.GameMode) org.bukkit.GameMode.SURVIVAL))) {
			event.setCancelled(true);
		}
		((org.bukkit.entity.Player) event.getPlayer()).spigot().sendMessage(
				net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
						ChatColor.translateAlternateColorCodes('&', "&4&l>> &cYou cannot drop items/blocks! &4&l<<")));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event8(org.bukkit.event.player.PlayerKickEvent event) throws Exception {
		event.setLeaveMessage(ChatColor.translateAlternateColorCodes('&', "%player_name% &7[&c+&7]"));
	}

	public static org.bukkit.inventory.ItemStack getNamedItemWithLore(Material material, String name,
			List<String> lore) {
		org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material);
		org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
		if (itemMeta != null) {
			itemMeta.setDisplayName(name);
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
		}
		return item;
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
