package xyz.openmodloader.test;

import java.awt.image.BufferedImage;
import java.io.File;

import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import xyz.openmodloader.OpenModLoader;
import xyz.openmodloader.event.Events;
import xyz.openmodloader.modloader.IMod;

public class OMLTestMod implements IMod {
    @Override
    public void onEnable() {
        OpenModLoader.INSTANCE.LOGGER.info("Loading test mod");

        Events.BLOCK_PLACE.register(event -> {
            OpenModLoader.INSTANCE.LOGGER.info("Placed block: " + event.getBlockState() + " isRemote: " + event.getWorld().isRemote);
            if (event.getBlockState().getBlock() == Blocks.GRASS) {
                event.setBlockState(Blocks.DIRT.getDefaultState());
            }
        });

        Events.BLOCK_DESTROY.register(event -> {
            OpenModLoader.INSTANCE.LOGGER.info("Destroyed block: " + event.getBlockState() + " isRemote: " + event.getWorld().isRemote);
            if (event.getBlockState().getBlock() == Blocks.GRASS) {
                event.setCanceled(true);
            }
        });

        Events.OPEN_GUI.register(event -> {
            OpenModLoader.INSTANCE.LOGGER.info("Opening gui: " + event.getGui());
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
        	OpenModLoader.INSTANCE.LOGGER.info(event.getItemStack().getDisplayName() + " " + event.getEnchantments().toString());
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
