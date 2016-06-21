package xyz.openmodloader.client;

import xyz.openmodloader.modloader.ModLoader;

import java.util.ArrayList;
import java.util.List;

public class OpenModLoaderClient {

    public static List<String> getMainMenuStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add(ModLoader.mods.size() + " loaded mod" + (ModLoader.mods.size() > 1 ? "s" : ""));
        list.add("Version, 0.0.0");
        list.add("Open Mod Loader");
        return list;
    }


}
