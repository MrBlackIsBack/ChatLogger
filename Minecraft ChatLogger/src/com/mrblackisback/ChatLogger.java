package com.mrblackisback;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatLogger extends JavaPlugin {

	public static ChatLogger instance = null;

	@Override
	public void onEnable() {

		instance = this;

		this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);

		getCommand("show").setExecutor(new ShowCmd());
	}

	@Override
	public void onDisable() {

		instance = null;

	}
}