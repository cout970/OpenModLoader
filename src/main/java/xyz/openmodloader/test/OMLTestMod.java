package xyz.openmodloader.test;

import xyz.openmodloader.event.RegisterEvent;
import xyz.openmodloader.modloader.Mod;
import xyz.openmodloader.modloader.events.MinecraftLoadEvent;

/**
 * Created by Mark on 18/06/2016.
 */
@Mod
public class OMLTestMod {

    @RegisterEvent
    public void load(MinecraftLoadEvent.Pre pre) {
        System.out.println("Loading test mod");
    }


}
