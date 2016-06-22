package xyz.openmodloader.event.impl;

import net.minecraft.util.text.ITextComponent;
import xyz.openmodloader.event.Event;
import xyz.openmodloader.event.strippable.Side;

public class ChatReceivedEvent extends Event {
    private ITextComponent message;
    private Side side;

    public ChatReceivedEvent(ITextComponent message, Side side) {
        this.message = message;
        this.side = side;
    }

    public ITextComponent getMessage() {
        return message;
    }

    public void setMessage(ITextComponent message) {
        this.message = message;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
