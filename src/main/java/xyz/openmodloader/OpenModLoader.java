package xyz.openmodloader;

import xyz.openmodloader.event.EventBus;
import xyz.openmodloader.modloader.ModLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class OpenModLoader {

    public static final OpenModLoader INSTANCE = new OpenModLoader();

    public final EventBus EVENT_BUS = new EventBus();

    private final Logger LOGGER = LogManager.getLogger();

    private ModLoader loader;

    public OpenModLoader() {
        EVENT_BUS.register(this);
    }

    public void minecraftConstruction() {
        LOGGER.info("Loading Grass mod loader");
        try {
            loader = new ModLoader();
            loader.loadMods();
            loader.registerMods();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
