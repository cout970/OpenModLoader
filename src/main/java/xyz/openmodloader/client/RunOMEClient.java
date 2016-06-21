package xyz.openmodloader.client;

import com.google.common.base.Strings;
import net.minecraft.client.main.Main;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Based of GradleStart
 */
public class RunOMEClient {

    public static void main(String[] args) {
        Main.main(getArgs());
    }

    private static String[] getArgs() {
        hackNatives();
        Map<String, String> argMap = new HashMap<>();
        argMap.put("version", "1.10");
        argMap.put("accessToken", "GRASS");

        ArrayList list = new ArrayList(22);
        Iterator out = argMap.entrySet().iterator();
        while (out.hasNext()) {
            Map.Entry b = (Map.Entry) out.next();
            String x = (String) b.getValue();
            if (!Strings.isNullOrEmpty(x)) {
                list.add("--" + (String) b.getKey());
                list.add(x);
            }
        }
        String[] var5 = (String[]) list.toArray(new String[list.size()]);
        return var5;
    }

    private static void hackNatives() {
        String paths = System.getProperty("java.library.path");
        String nativesDir = ".gradle/minecraft/natives/";
        if(Strings.isNullOrEmpty(paths)) {
            paths = nativesDir;
        } else {
            paths = paths + File.pathSeparator + nativesDir;
        }

        System.setProperty("java.library.path", paths);

        try {
            Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set((Object)null, (Object)null);
        } catch (Throwable var3) {
            ;
        }

    }
}
