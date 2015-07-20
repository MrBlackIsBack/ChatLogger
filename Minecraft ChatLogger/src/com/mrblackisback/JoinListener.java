package com.mrblackisback;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mrblackisback.handler.FileHandler;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ChatLogger main = ChatLogger.instance;
		
		if (!(main.getDataFolder().exists()))
			main.getDataFolder().mkdir();

		new FileHandler(p.getName(), main.getDataFolder());
	}

}
