package de.neuwirthinformatik.Alexander.bukkit.AUP;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;



public class AUP extends JavaPlugin
{
	private Logger log = Logger.getLogger("Minecraft");
	
	public void update()
	{
		String json = Wget.wGet(this.getDescription().getWebsite() + "/releases/latest").replaceAll("\n", "");
		JSONObject jso = new JSONObject(json);
		String tag_name = jso.getString("tag_name");
		if(!tag_name.equals(this.getDescription().getVersion()))
		{
			this.logMessage("Downloading AUP " + tag_name);
			//update
			Wget.wGet(getDataFolder().getAbsolutePath() + "/../" + this.getDescription().getName(), this.getDescription().getWebsite() + "/releases/download/"+tag_name+"/" + this.getDescription().getName() + "-" + this.getDescription().getVersion());
		}
		else
		{
			this.logMessage("Not Downloading AUP " + tag_name);
			
		}
	}
	
	
	public void onEnable() {
		this.logMessage("Enabled AUP " + this.getDescription().getVersion());
		update();
		
	}
	
	public void onDisable() {
		this.logMessage("Disabled AUP " + this.getDescription().getVersion());
		
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
