package xyz.openmodloader.test;

import net.minecraft.init.Blocks;
import xyz.openmodloader.event.Events;
import xyz.openmodloader.modloader.IMod;

public class OMLTestMod implements IMod {
    @Override
    public void onEnable() {
        System.out.println("Loading test mod");

        Events.BLOCK_PLACE.register(event -> {
            System.out.println("Placed block: " + event.getBlockState() + " isRemote: " + event.getWorld().isRemote);
            if (event.getBlockState().getBlock() == Blocks.GRASS) {
                event.setBlockState(Blocks.DIRT.getDefaultState());
            }
        });
        Events.BLOCK_DESTROY.register(event -> {
            System.out.println("Destroyed block: " + event.getBlockState() + " isRemote: " + event.getWorld().isRemote);
            if (event.getBlockState().getBlock() == Blocks.GRASS) {
                event.setCanceled(true);
            }
        });
    }
}
