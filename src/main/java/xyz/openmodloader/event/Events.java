package xyz.openmodloader.event;

import xyz.openmodloader.event.impl.BlockEvent;

import java.util.ArrayList;
import java.util.List;

public class Events<T extends Event> {
    public static final Events<BlockEvent.PlaceEvent> BLOCK_PLACE = new Events<>();

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
     * @return true is the event <strong>isn't</strong> canceled
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
