package xyz.openmodloader.test;

import xyz.openmodloader.event.RegisterEvent;
import xyz.openmodloader.modloader.events.MinecraftLoadEvent;

public class OMLTestMod {
    @RegisterEvent
    public void load(MinecraftLoadEvent.Pre pre) {
        System.out.println("Loading test mod");
    }
}
