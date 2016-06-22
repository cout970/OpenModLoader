package xyz.openmodloader.test;

import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
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

        Events.OPEN_GUI.register(event -> {
            System.out.println("Opening gui: " + event.getGui());
            if (event.getGui() instanceof GuiLanguage) {
                event.setCanceled(true);
            }
        });

        Events.DIG_SPEED.register(event -> {
            if (event.getBlockState().getBlock() == Blocks.DIRT) {
                event.setDigSpeed(0.05F);
            }
        });
        
        Events.ITEM_ENCHANTED.register(event -> {
        	System.out.println(event.getItemStack().getDisplayName() + " " + event.getEnchantments().toString());
        });

        Events.EXPLOSION.register(event -> {
            event.setCanceled(true);
        });
    }
}
