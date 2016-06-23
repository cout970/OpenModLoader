package xyz.openmodloader.event;

import xyz.openmodloader.event.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Events<T extends Event> {
    
    public static final Events<BlockEvent.Place> BLOCK_PLACE = new Events<>();
    public static final Events<BlockEvent.Destroy> BLOCK_DESTROY = new Events<>();
    public static final Events<BlockEvent.DigSpeed> DIG_SPEED = new Events<>();
    public static final Events<GuiEvent.Open> OPEN_GUI = new Events<>();
    public static final Events<GuiEvent.Init> INIT_GUI = new Events<>();
    public static final Events<GuiEvent.ButtonClick> BUTTON_CLICK = new Events<>();
    public static final Events<UpdateEvent.World> WORLD_UPDATE = new Events<>();
    public static final Events<UpdateEvent.Entity> ENTITY_UPDATE = new Events<>();
    public static final Events<UpdateEvent.Render> RENDER_UPDATE = new Events<>();
    public static final Events<ChatReceivedEvent> CHAT_RECEIVED = new Events<>();
    public static final Events<ExplosionEvent> EXPLOSION = new Events<>();
    public static final Events<ItemEnchantedEvent> ITEM_ENCHANTED = new Events<>();
    public static final Events<SplashLoadEvent> SPLASH_LOAD = new Events<>();
    public static final Events<ScreenshotEvent> SCREENSHOT = new Events<>();

    private List<IEventExecutor<T>> executorList = new ArrayList<>();

    /**
     * Register an event executor.
     *
     * @param executor the executor instance
     */
    public void register(IEventExecutor<T> executor) {
        this.executorList.add(executor);
    }

    /**
     * Post an event to all its executors.
     *
     * @param event the event instance
     * @return true if the event <strong>isn't</strong> canceled
     */
    public boolean post(T event) {
        for (IEventExecutor<T> executor : this.executorList) {
            executor.execute(event);
            if (event.isCanceled()) {
                return false;
            }
        }
        return true;
    }
}
