package io.icker.obsidian;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Obsidian implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Obsidian");
    public static final HashMap<String, HashMap<String, Float>> CONFIG = Config.load();
    private static NbtCompound database = new NbtCompound();
    private static File file;

    @Override
    public void onInitialize() {
        LOGGER.info("Obsidian mod initialized");

        file = FabricLoader.getInstance().getGameDir().resolve("config").resolve("obsidian.dat").toFile();
        if (!file.exists()) {
            try {
                NbtIo.writeCompressed(new NbtCompound(), file);
            } catch (IOException e) {
                LOGGER.error("Failed to create NBT file", e);
            }
        }

        try {
            database = NbtIo.readCompressed(file);
        } catch (IOException e) {
            LOGGER.error("Failed to load NBT file", e);
        }
    }

    private static String getKey(World world, BlockPos pos, String key) {
        return String.format("%s-%d-%d-%d-%s", world.getRegistryKey().getValue().toString(), pos.getX(), pos.getY(), pos.getZ(), key);
    }

    public static void setBlastCount(World world, BlockPos pos, String key, int count) {
        database.putInt(getKey(world, pos, key), count);
    }

    public static int getBlastCount(World world, BlockPos pos, String key) {
        return database.getInt(getKey(world, pos, key));
    }
    
    public static void removeBlastCount(World world, BlockPos pos, String key) {
        database.remove(getKey(world, pos, key));
    }

    public static void save() {
        try {
            NbtIo.writeCompressed(database, file);
        } catch (IOException e) {
            LOGGER.error("Failed to save NBT file", e);
        }
    }
}
