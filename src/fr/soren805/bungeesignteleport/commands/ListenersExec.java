package fr.soren805.bungeesignteleport.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.soren805.bungeesignteleport.Main;

public class ListenersExec implements Listener {
	
	private Main main;
	private FileConfiguration config;
	
	public ListenersExec(Main main) {
		this.main = main;
	}
	
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		
		if(event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK) {
			
			BlockState bs = event.getClickedBlock().getState();
			if(bs instanceof Sign) {
				
				Sign sign = (Sign) bs;
				
				if(sign.getLine(0).equalsIgnoreCase("[BungeeCord]") && sign.getLine(1).equalsIgnoreCase("Teleport")) {
					
					if(sign.getLine(2) != null) {
						String serverName = sign.getLine(2);
						ByteArrayOutputStream b = new ByteArrayOutputStream();
						DataOutputStream out = new DataOutputStream(b);
						
						try {
							out.writeUTF("Connect");
							out.writeUTF(serverName);
						} catch (IOException e) {
							e.printStackTrace();
							player.sendMessage(config.getString("bungeesignteleport.messages.server-not-existing").replace("&", "§"));
						}
						
						player.sendPluginMessage(main, "BungeeCord", b.toByteArray());
						player.sendMessage(config.getString("bungeesignteleport.messages.teleported-to-server").replace("&", "§"));
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
