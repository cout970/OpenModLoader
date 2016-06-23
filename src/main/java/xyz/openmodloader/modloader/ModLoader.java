package xyz.openmodloader.modloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import xyz.openmodloader.OpenModLoader;

public class ModLoader {
    
    public static final Map<String, ModContainer> MODS = new HashMap<String, ModContainer>();

    private File runDir = new File(".");
    private File modsDir = new File(runDir, "mods");
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();

    public void loadMods() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (this.modsDir.exists()) {
            File[] files = this.modsDir.listFiles();
            if (files != null) {
                for (File mod : files) {
                    if (mod.getName().endsWith(".jar")) {
                        JarFile jar = new JarFile(mod);
                        for (JarEntry entry : Collections.list(jar.entries())) {
                            String fileName = entry.getName();
                            if (fileName.equals("mod.json")) {
                                this.loadMod(jar.getInputStream(entry));
                            }
                        }
                        jar.close();
                    }
                }
            }
        }

        URL roots = this.getClass().getClassLoader().getResource("");
        if (roots != null) {
            File root = new File(roots.getPath());
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equals("mod.json")) {
                        this.loadMod(new FileInputStream(file));
                    }
                }
            }
        }
    }

    public void loadMod(InputStream stream) {
        List<ModContainer> containerList = new ArrayList<>();
        JsonElement element = this.parser.parse(new InputStreamReader(stream));
        if (element.isJsonArray()) {
            for (JsonElement e : element.getAsJsonArray()) {
                containerList.add(this.gson.fromJson(e, ModContainer.class));
            }
        } else {
            containerList.add(this.gson.fromJson(new InputStreamReader(stream), ModContainer.class));
        }
        for (ModContainer container : containerList) {
            if(container != null){
                OpenModLoader.INSTANCE.LOGGER.info("Found mod " + container.getName() + " (with id " + container.getModID() + ")");
                MODS.put(container.getModID(), container);
            }
        }
    }

    public void registerMods() throws IllegalAccessException, InstantiationException {
        for (ModContainer mod : MODS.values()) {
            try {
                mod.getInstance().onEnable();
            } catch (RuntimeException e) {
                System.err.println("An error occurred while enabling mod " + mod.getModID());
                e.printStackTrace();
                MODS.remove(mod.getModID());
            }
        }
    }
}
