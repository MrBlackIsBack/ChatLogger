package com.mrblackisback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
				if (args.length == 0 || args.length > 2) {
					sender.sendMessage(ChatColor.RED + "/show [player] <number>");
					return true;
				}

				Player t = Bukkit.getPlayer(args[0]);
				FileHandler hand;
				SortedMap<Integer, String> page = new TreeMap<Integer, String>();

				if (t != null) {
					hand = new FileHandler(t.getName(), main.getDataFolder());

					try {
						BufferedReader read = new BufferedReader(new FileReader(hand.getFile()));

						String line = "";
						int l = 1;

						while ((line = read.readLine()) != null) {

							page.put(l, line);
							l++;
						}

						int num = 1;

						try {
							if (args.length == 2) {
								num = Integer.parseInt(args[1]);
							} else {
								num = 1;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (l > 1) {
							paginate(sender, page, num, 5);
						} else {
							paginate(sender, page, 1, 5);
						}
						// while ((line = read.readLine()) != null) {
						// page.put(l, line);
						// paginate(sender, page, 1, 5);
						// sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						// line));
						// l++;
						// }

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

	public void paginate(CommandSender sender, SortedMap<Integer, String> map, int page, int pageLength) {
		int i = 0, k = 0;
		page--;
		for (final Entry<Integer, String> e : map.entrySet()) {
			k++;
			if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
				i++;
				sender.sendMessage(e.getValue());
			}
		}
		sender.sendMessage(ChatColor.GOLD + "" + (page + 1) + " / "
				+ (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1)
				+ ChatColor.GREEN + " pages");
	}

}
