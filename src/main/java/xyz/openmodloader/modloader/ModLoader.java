package xyz.openmodloader.modloader;

import com.google.gson.Gson;
import xyz.openmodloader.OpenModLoader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
    public static List<ModContainer> MODS = new ArrayList<>();

    private File runDir = new File(".");
    private File modsDir = new File(runDir, "mods");
    private Gson gson = new Gson();

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
        ModContainer container = this.gson.fromJson(new InputStreamReader(stream), ModContainer.class);
        OpenModLoader.INSTANCE.LOGGER.info("Found mod " + container.getName() + " (with id " + container.getModID() + ")");
        ModLoader.MODS.add(container);
    }

    public void registerMods() throws IllegalAccessException, InstantiationException {
        for (ModContainer mod : new ArrayList<>(MODS)) {
            try {
                OpenModLoader.INSTANCE.EVENT_BUS.register(mod.getInstance());
            } catch (RuntimeException e) {
                System.err.println("An error occurred while loading mod " + mod.getModID() + ". Unloading mod.");
                e.printStackTrace();
                ModLoader.MODS.remove(mod);
            }
        }
    }
}
