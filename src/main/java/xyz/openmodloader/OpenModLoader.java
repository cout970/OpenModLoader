package xyz.openmodloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.openmodloader.modloader.ModLoader;

import java.io.IOException;

public enum OpenModLoader {
    INSTANCE;

    public final Logger LOGGER = LogManager.getLogger();

    private ModLoader loader;

    public void minecraftConstruction() {
        LOGGER.info("Loading Open Mod Loader");
        try {
            loader = new ModLoader();
            loader.loadMods();
            loader.registerMods();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
