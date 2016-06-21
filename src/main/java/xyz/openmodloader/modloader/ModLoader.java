package xyz.openmodloader.modloader;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import xyz.openmodloader.OpenModLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {

    static File runDir = new File(".");
    static File modsDir = new File(runDir, "mods");
    public static ArrayList<ModContainer> mods = new ArrayList<>();
    private ArrayList<File> result = new ArrayList<>();

    public void loadMods() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (modsDir.exists()) {
            for (File mod : modsDir.listFiles()) {
                if (mod.getName().endsWith(".jar")) {
                    JarFile jar = new JarFile(mod);
                    for (JarEntry entry : Collections.list(jar.entries())) {
                        String fileName = entry.getName();
                        if (!fileName.endsWith(".class"))
                            continue;
                        String classname = fileName.replace('/', '.').substring(0, fileName.length() - 6);

                        Class clazz = Class.forName(classname);

                        if (clazz.isInstance(Mod.class))
                            continue;
//                        mods.add(clazz);
                    }
                }
            }
        }

        URL roots = getClass().getClassLoader().getResource("");
        if (roots != null) {
            File root = new File(roots.getPath());
            File[] files = root.listFiles();
            if (files!=null)
                for (File file : files) {
                    search(file);
                }

            for (File temp : result) {
                String entryName = temp.getPath().replace(root.getPath() + "\\", "").replace(".class", "").replace("\\", ".");
                ModContainer clazz = isModClass(new FileInputStream(temp.getPath()), temp, entryName);
                if (clazz != null) {
                    mods.add(clazz);
                }
            }
        }
    }

    public void registerMods() throws IllegalAccessException, InstantiationException {
        for (ModContainer mod : mods) {
            Object object = mod.clazz.newInstance();
            OpenModLoader.INSTANCE.EVENT_BUS.register(object);
        }
    }

    public void search(File dir) {
        if (dir.listFiles() != null)
            for (File temp : dir.listFiles())
                if (temp.isDirectory())
                    search(temp);
                else
                    if (temp.getName().contains(".class") && !temp.getPath().contains("core\\"))
                        result.add(temp);
    }

    public ModContainer isModClass(InputStream stream, File module, String entryName) throws ClassNotFoundException {
        try {
            // creates a new classnode instance.
            ClassNode classNode = new ClassNode();
            new ClassReader(stream).accept(classNode, 0);

            // Creates list of visible annotations within the class.
            List annotations = classNode.visibleAnnotations;

            // Checks for at least one valid annotation
            if (annotations != null && !annotations.isEmpty()) {

                // loops through all annotations.
                for (int i = 0; i < annotations.size(); i++) {

                    // Checks if current annotation is the @Module annotation.
                    if (Type.getType(((AnnotationNode) annotations.get(i)).desc).equals(Type.getType(Mod.class))) {

                        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{module.toURI().toURL()}, this.getClass().getClassLoader());
                        Class<?> clazz = Class.forName(entryName.replace(".class", "").replace("/", "."), true, urlClassLoader);

                        Mod mod = clazz.getAnnotation(Mod.class);
                        return new ModContainer(mod.modid(), mod.name(), mod.version(), clazz);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
