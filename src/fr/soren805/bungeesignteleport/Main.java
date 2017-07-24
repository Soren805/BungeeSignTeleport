package fr.soren805.bungeesignteleport;

import org.bukkit.plugin.java.JavaPlugin;

import fr.soren805.bungeesignteleport.commands.ListenersExec;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("BungeeSignTeleport > Enabled!");
		getServer().getPluginManager().registerEvents(new ListenersExec(this), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void onDisable() {
		System.out.println("BungeeSignTeleport > Disabled!");
	}

}
