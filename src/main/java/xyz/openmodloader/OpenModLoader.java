package xyz.openmodloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import xyz.openmodloader.event.EventBus;
import xyz.openmodloader.event.strippable.Side;
import xyz.openmodloader.modloader.ModLoader;

public enum OpenModLoader {
    INSTANCE;

    public static Side SIDE;
    public final Logger LOGGER = LogManager.getLogger();
    public final EventBus EVENT_BUS = new EventBus();

    public void minecraftConstruction() {
        LOGGER.info("Loading Open Mod Loader");
        ModLoader.loadMods();
        ModLoader.registerMods();
    }
}
