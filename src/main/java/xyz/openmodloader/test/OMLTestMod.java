package xyz.openmodloader.test;

import xyz.openmodloader.event.Events;
import xyz.openmodloader.modloader.IMod;

public class OMLTestMod implements IMod {
    @Override
    public void onEnable() {
        System.out.println("Loading test mod");
        Events.BLOCK_PLACE.register(event -> {
            System.out.println("Placed block: " + event.getBlockState() + " isRemote: " + event.getWorld().isRemote);
        });
    }
}
