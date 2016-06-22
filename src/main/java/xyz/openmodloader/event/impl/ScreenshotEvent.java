package xyz.openmodloader.event.impl;

import net.minecraft.util.text.ITextComponent;
import xyz.openmodloader.event.Event;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This event is fired whenever a screenshot is taken, after the image has been created from the screen but before it has been saved to a file.
 * This event is cancelable.
 *
 * @see xyz.openmodloader.event.Events#SCREENSHOT
 */
public class ScreenshotEvent extends Event {
    /**
     * The image that will be saved to the file.
     * This can be modified but not replaced.
     *
     * @see #getScreenshotFile()
     */
    private BufferedImage image;
    /**
     * The file the screenshot will be saved to.
     * This can be changed.
     * @see #getScreenshotFile()
     * @see #setScreenshotFile(File)
     */
    private File screenshotFile;

    /**
     * The result message to be returned.
     *
     * @see #setResultMessage(ITextComponent)
     * @see #getResultMessage()
     */
    private ITextComponent resultMessage;

    public ScreenshotEvent(BufferedImage image, File screenshotFile) {
        this.image = image;
        this.screenshotFile = screenshotFile;
    }

    public BufferedImage getImage() {
        return image;
    }

    public File getScreenshotFile() {
        return screenshotFile;
    }

    public void setScreenshotFile(File screenshotFile) {
        this.screenshotFile = screenshotFile;
    }

    public ITextComponent getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(ITextComponent resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
