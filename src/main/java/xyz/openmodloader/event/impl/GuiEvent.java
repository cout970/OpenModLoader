package xyz.openmodloader.event.impl;

import net.minecraft.client.gui.GuiScreen;
import xyz.openmodloader.event.Event;

public class GuiEvent extends Event {
    protected GuiScreen gui;

    public GuiEvent(GuiScreen gui) {
        this.gui = gui;
    }

    public static class Open extends GuiEvent {
        public Open(GuiScreen gui) {
            super(gui);
        }

        public void setGui(GuiScreen gui) {
            this.gui = gui;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public GuiScreen getGui() {
        return gui;
    }
}
