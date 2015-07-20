package com.mrblackisback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.mrblackisback.handler.FileHandler;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		FileHandler hand = new FileHandler(p.getName(), ChatLogger.instance.getDataFolder());

		String temp = "";

		for (String msg : e.getMessage().split(" "))
			temp += (msg + " ");

		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		Date date = new Date();
		String time = dateFormat.format(date);

		hand.write(time + " | " + p.getName() + " > " + temp);

	}

}
