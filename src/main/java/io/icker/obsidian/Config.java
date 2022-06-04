package io.icker.obsidian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.fabricmc.loader.api.FabricLoader;

public class Config {
    private static final File file = FabricLoader.getInstance().getGameDir().resolve("config").resolve("obsidian.json").toFile();

    public static HashMap<String, Float> load() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        if (!file.exists()) {
            Obsidian.LOGGER.warn("No Obisidian config file exists. All blocks will have their default blast resistance");
            return new HashMap<>();
        }

        try {
            Type type = new TypeToken<HashMap<String, Float>>(){}.getType();
            return gson.fromJson(new FileReader(file), type);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            Obsidian.LOGGER.warn("Could not read Obsidian config file. All blocks will have their default blast resistance");
        }

        return new HashMap<>();
    }
}
