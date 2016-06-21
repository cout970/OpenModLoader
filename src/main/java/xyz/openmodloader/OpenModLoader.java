package xyz.openmodloader;

import xyz.openmodloader.event.EventBus;
import xyz.openmodloader.modloader.ModLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Mark on 18/06/2016.
 */
public class OpenModLoader {

    public static final OpenModLoader instance = new OpenModLoader();

    public final EventBus eventBus = new EventBus();

    private final Logger logger = LogManager.getLogger();

    private ModLoader loader;

    public OpenModLoader() {
        eventBus.register(this);
    }

    public void minecraftConstruction() {
        logger.info("Loading Grass mod loader");
        try {
            loader = new ModLoader();
            loader.loadMods();
            loader.registerMods();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
