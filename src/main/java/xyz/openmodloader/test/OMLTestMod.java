package xyz.openmodloader.test;

import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import xyz.openmodloader.event.Events;
import xyz.openmodloader.modloader.IMod;

import java.awt.image.BufferedImage;
import java.io.File;

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

        Events.SPLASH_LOAD.register(event -> {
            event.getSplashTexts().clear();
            event.getSplashTexts().add("OpenModLoader Test!");
        });

        Events.SCREENSHOT.register(event -> {
            event.setScreenshotFile(new File("screenshotevent/", event.getScreenshotFile().getName()));
            event.setResultMessage(new TextComponentString("Screenshot saved to " + event.getScreenshotFile().getPath()));
            BufferedImage image = event.getImage();
            for (int x = 0; x < 20; x++) {
                for (int y = 0; y < 20; y++) {
                    image.setRGB(x, y, 0xFF0000);
                }
            }
        });
    }
}
