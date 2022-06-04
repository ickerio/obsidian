package io.icker.obsidian;

import net.fabricmc.api.ModInitializer;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Obsidian implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Obsidian");
	public static final HashMap<String, Float> CONFIG = Config.load();

	@Override
	public void onInitialize() {
		LOGGER.info("Obsidian mod initialized");
	}
}
