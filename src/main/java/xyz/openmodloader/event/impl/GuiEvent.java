package xyz.openmodloader.event.impl;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import xyz.openmodloader.event.Event;

import java.util.List;

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

    public static class Init extends GuiEvent {
        private List<GuiButton> buttonList;

        public Init(GuiScreen gui, List<GuiButton> buttonList) {
            super(gui);
            this.buttonList = buttonList;
        }

        public List<GuiButton> getButtonList() {
            return buttonList;
        }
    }

    public static class ButtonClick extends GuiEvent {
        private GuiButton button;

        public ButtonClick(GuiScreen gui, GuiButton button) {
            super(gui);
        }

        public GuiButton getButton() {
            return button;
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
