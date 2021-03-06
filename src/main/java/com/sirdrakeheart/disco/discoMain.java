package com.sirdrakeheart.disco;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.DyeColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//import java.util.HashMap;
//import java.util.Map;

public class discoMain extends JavaPlugin {
	private discoBlockListener bListener = new discoBlockListener(this);
	Logger Log = Logger.getLogger("Minecraft");
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Player> using = new ArrayList<Player>();
	// public static Map<String, Block> blocks = new HashMap<String, Block>();
	public static ArrayList<Block> blocks = new ArrayList<Block>();

	// @Override
	public void onDisable() {
		Log.info("Disco plugin disabled!");
	}

	// @Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(bListener, this);
		getCommand("disco").setExecutor(new discoCmdExecutor(this));
		discoPreferences discopreferences = new discoPreferences();
		discopreferences.createDir();
		coordinateReader reader = new coordinateReader(this);
		reader.readAll();
		Log.info("Disco plugin enabled!");
	}

	@SuppressWarnings("deprecation")
	public static void blockChanger() {
		// Random random2 = new Random();
		// Block block = blocks.get(random2.nextInt(blocks.size()));
		// Random random = new Random();
		// byte randomColor = (byte) random.nextInt(10000);
		// if (randomColor == ){
		// / block.setData(randomColor);
		// }

		Random random2 = new Random();
		Block block = blocks.get(random2.nextInt(blocks.size()));
		Random random = new Random();
		byte randomColor = (byte) random.nextInt(16);
		if (randomColor == 0 || randomColor == 7 || randomColor == 8
				|| randomColor == 12 || randomColor == 15 || randomColor == 13) {
			blockChanger();
			return;
		} else {
			BlockState state = block.getState();
			MaterialData data = state.getData();
			if (data instanceof Wool) {
				((Wool) data).setColor(DyeColor.getByDyeData(randomColor));
				state.setData(data);
				state.update(true);
			}
		}
	}
}