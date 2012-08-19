package me.JayzaSapphire;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NfwExecutor implements CommandExecutor {

	private NoFallWorld nfw;
	public NfwExecutor(NoFallWorld nfw) {
		this.nfw = nfw;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		if (sender instanceof Player) {
			if (a.length == 0) {
				if (sender.hasPermission("nfw.register")) {
					Player player = (Player)sender;
					if (!nfw.worlds.contains(player.getWorld().getName().toLowerCase())) {
						sender.sendMessage(ChatColor.DARK_AQUA + (player.getWorld().getName() + " registered."));
						
						nfw.worlds.add(player.getWorld().getName().toLowerCase());
						nfw.getConfig().set("worlds", nfw.worlds);
						nfw.saveConfig();
					} else {
						sender.sendMessage(ChatColor.RED + "This world is already registered.");
					}
				}
			}
			
			if (a.length > 0) {
				if (sender.hasPermission("nfw.register")) {
					sender.sendMessage(ChatColor.RED + "Too many arguements.");
				}
			}
		} else {
			if (a.length == 0) {
				sender.sendMessage(ChatColor.RED + "Console cannot use this command.");
			}
			
			if (a.length > 0) {
				sender.sendMessage(ChatColor.RED + "Too many arguements.");
			}
		}
		return false;
	}
}
