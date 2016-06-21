package xyz.openmodloader.event;

@FunctionalInterface
public interface IEventExecutor<T extends Event> {
    void execute(T event);
}
