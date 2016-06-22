package xyz.openmodloader.client;

import com.google.common.base.Strings;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import xyz.openmodloader.OpenModLoader;
import xyz.openmodloader.event.strippable.Side;

public class RunOMLClient {

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("../.gradle/minecraft/natives/").getAbsolutePath());

        OpenModLoader.SIDE = Side.CLIENT;
        Launch.main(getArgs());
    }

    private static String[] getArgs() {
        Map<String, String> argMap = new HashMap<>();
        argMap.put("version", "1.10");
        argMap.put("accessToken", "OpenModLoader");
        argMap.put("tweakClass", "xyz.openmodloader.launcher.OMLTweaker");

        ArrayList<String> list = new ArrayList<>();
        Iterator out = argMap.entrySet().iterator();
        while (out.hasNext()) {
            Map.Entry b = (Map.Entry) out.next();
            String x = (String) b.getValue();
            if (!Strings.isNullOrEmpty(x)) {
                list.add("--" + b.getKey());
                list.add(x);
            }
        }
        String[] var5 = list.toArray(new String[list.size()]);
        return var5;
    }
}
