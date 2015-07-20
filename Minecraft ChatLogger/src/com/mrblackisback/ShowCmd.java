package com.mrblackisback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mrblackisback.handler.FileHandler;

public class ShowCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

		ChatLogger main = ChatLogger.instance;

		if (cmd.getName().equalsIgnoreCase("show")) {
			if (sender.hasPermission("chatlogger.show")) {
				if (args.length == 0 || args.length > 1) {
					sender.sendMessage(ChatColor.RED + "/show [player]");
					return true;
				}

				Player t = Bukkit.getPlayer(args[0]);
				FileHandler hand;

				if (t != null) {
					hand = new FileHandler(t.getName(), main.getDataFolder());

					try {
						BufferedReader read = new BufferedReader(new FileReader(hand.getFile()));

						String line = "";

						while ((line = read.readLine()) != null) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
						}

						read.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					sender.sendMessage(ChatColor.RED + "Could not find player!");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid permissions.");
			}
			return true;
		}
		return false;
	}

}
