package de.neuwirthinformatik.Alexander.bukkit.AUP;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;



public class AUP extends JavaPlugin
{
	private Logger log = Logger.getLogger("Minecraft");
	
	public void update()
	{
		File d = new File(getDataFolder().getParentFile().getAbsolutePath() +FileSystems.getDefault().getSeparator());
		File[] pl = d.listFiles();
		for(Plugin p : Bukkit.getServer().getPluginManager().getPlugins())
		{
			if(p.getDescription().getName().equals(getDescription().getName()) && !p.getDescription().getVersion().equals(getDescription().getVersion()))
			{
				Bukkit.getServer().getPluginManager().disablePlugin(p);
			}
		}
		for (File  f : pl)
		{
			if(f.getName().startsWith(this.getDescription().getName()) && !f.getName().endsWith(getDescription().getVersion() + ".jar"))
			{
				this.logMessage(f.getName() + " del:" + f.delete());
			}
		}
		logMessage(""+d.delete());
		
		String json = Wget.wGet("https://" + "api." + this.getDescription().getWebsite().replaceAll("github.com/", "github.com/repos/") + "/releases/latest").replaceAll("\n", "");
		JSONObject jso = new JSONObject(json);
		String tag_name = jso.getString("tag_name");
		if(!tag_name.equals(this.getDescription().getVersion()))
		{
			//update
			String name = this.getDescription().getName() + "-" + tag_name + ".jar";
			String nw = getDataFolder().getParentFile().getAbsolutePath() +FileSystems.getDefault().getSeparator()+ name;
			String old = getDataFolder().getParentFile().getAbsolutePath() +FileSystems.getDefault().getSeparator()+ this.getDescription().getName() + "-" + getDescription().getVersion() + ".jar";
			Wget.wGet(nw ,"https://" + this.getDescription().getWebsite() + "/releases/download/"+tag_name+"/" + name);
			this.logMessage("Downloading AUP " + name);
			this.logMessage(nw);
			
			//Bukkit.getServer().getPluginManager().loadPlugin(new File(nw));
			
			//Bukkit.getServer().getPluginManager().un
			Bukkit.getServer().reload();
			
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
