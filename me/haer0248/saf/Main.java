package me.haer0248.saf;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getLogger().info("§9插件成功啟用。");
		getServer().getPluginManager().registerEvents(this, this);

		File config = new File("plugins/ShiftAndF/config.yml");

		if (config.exists()) {
			getLogger().info("§a設定檔案已自動寫入。");
			prefix = this.getConfig().getString("prefix");
			ShiftHand = this.getConfig().getStringList("S-F_command");
			ShiftQHand = this.getConfig().getStringList("S-Q_command");
		} else {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			getLogger().info("§6設定檔案已自動建立在 plugins/ShiftAndF 。");
		}
	}

	public void onDisable() {
		getLogger().info("§9插件成功關閉。");
		saveDefaultConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		String help = "\n----- §e§lShiftAndF§r -----\n"
				+ "§6Author: §fNekomataOnigiri\n"
				+ "§6Website: §fhttps://haer0248.me/\n"
				+ "§6Version §f1.2.0\n"
				+ "§6Open Source: §fhttps://github.com/haer0248/ShiftAndF"
				+ "§f\n";
		if (cmd.getName().equalsIgnoreCase("saf")) {
			if (args.length < 1) {
				sender.sendMessage(help);
			} else if (args[0].equals("reload")) {
				if (sender.isOp() || sender.hasPermission("saf.reload")) {
					reloadConfig();
					prefix = this.getConfig().getString("prefix");
					ShiftHand = this.getConfig().getStringList("S-F_command");
					ShiftQHand = this.getConfig().getStringList("S-Q_command");
					sender.sendMessage(prefix + "§a插件已成功重新讀取。");
				} else {
					sender.sendMessage(prefix + "§c沒有執行此指令的權限。");
				}
			} else {
				sender.sendMessage(help);
			}
		}
		return true;
	}

	@EventHandler
	public void onF(PlayerSwapHandItemsEvent event){
		Player player;
		player = event.getPlayer();
		if (getConfig().getBoolean("enable_F")) {
			if (event.getPlayer().isSneaking()) {
				event.setCancelled(true);

				String command_f;
				for(Iterator<String> iterator = ShiftHand.iterator(); iterator.hasNext(); Bukkit.dispatchCommand(player, command_f)) {
					command_f = (String)iterator.next();
				}
			}
		}
	}

	@EventHandler
	public void onQ(PlayerDropItemEvent event){
		Player player;
		player = event.getPlayer();
		if (getConfig().getBoolean("enable_Q")) {
			if (event.getPlayer().isSneaking()) {
				event.setCancelled(true);

				String command_q;
				for(Iterator<String> iterator = ShiftQHand.iterator(); iterator.hasNext(); Bukkit.dispatchCommand(player, command_q)) {
					command_q = (String)iterator.next();
				}
			}
		}
	}

	public static Plugin plugin;
	List<String> ShiftHand;
	List<String> ShiftQHand;
	String prefix;
}
