package xyz.openmodloader.client;

import xyz.openmodloader.event.strippable.Side;
import xyz.openmodloader.event.strippable.Strippable;
import xyz.openmodloader.modloader.ModLoader;

import java.util.ArrayList;
import java.util.List;

@Strippable(side = Side.CLIENT)
public class OpenModLoaderClient {
    public static List<String> getMainMenuStrings() {
        List<String> list = new ArrayList<>();
        list.add(ModLoader.MODS.size() + " loaded mod" + (ModLoader.MODS.size() == 1 ? "" : "s"));
        list.add("Version 0.0.0");
        list.add("Open Mod Loader");
        return list;
    }
}
