package de.neuwirthinformatik.Alexander.bukkit.AUP;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AUP extends JavaPlugin
{
	private Logger log = Logger.getLogger("Minecraft");
	public void onEnable() {
		this.logMessage("Enabled AUP.");
	}
	
	public void onDisable() {
		this.logMessage("Disabled AUP.");
		
	}
	
	public void logMessage(String str) {
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[" + pdf.getName()+ "] " + str);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("helloworld"))
		{
			for(Player p : Bukkit.getOnlinePlayers())p.sendMessage(ChatColor.RED + "Hello World");
			return true;
		}
		return false;
	}
}
