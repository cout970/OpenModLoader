package xyz.openmodloader.event;

import java.util.ArrayList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class EventBus {

    private final List<SavedEvent> events = new ArrayList<>();


    /**
     * This is used to trigger an event
     *
     * @param event the event that should be called
     * @return if the event was canceled
     */
    public void trigger(Event event) {
        for (SavedEvent savedEvent : events) {
            //This is the method that will be called.
            Method method = savedEvent.method;
            //This used reflection to make sure the method isn't private or protected.
            method.setAccessible(true);
            //This gets the parameters of the method
            Class<?>[] parameterTypes = method.getParameterTypes();
            //We only need the first one
            Class<?> eventType = parameterTypes[0];
            //This checks to see if the parameter is the same as the one that was passed above
            if (eventType.getName().equals(event.getClass().getName())) {
                //This checks to see if the first parameter is an instance of Event
                if (!Event.class.isAssignableFrom(eventType)) {
                    throw new IllegalArgumentException("Method " + method + " has @RegisterEvent annotation, but takes a argument that is not an Event " + eventType);
                }
                try {
                    //Use java reflection to call it
                    method.invoke(savedEvent.clazz, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //This registers a object to be scanned for event handlers indicated with RegisterEvent
    public void register(Object target) {
        //For loop over all of the method in the target
        for (Method method : target.getClass().getMethods()) {
            //Checks to see if it has been annotated with RegisterEvent
            if (method.isAnnotationPresent(RegisterEvent.class)) {
                Class<?>[] arguments = method.getParameterTypes();
                //Checks to see if it is a valid method
                if (arguments.length != 1) {
                    //Crashes if not a valid method
                    throw new IllegalArgumentException(
                            "Method " + method + " has @RegisterEvent annotation, but requires " + arguments.length +
                                    " arguments.  Event handler methods must require a single argument."
                    );
                }
                Class<?> eventType = arguments[0];
                //Some more checks to see if it is an event
                if (!Event.class.isAssignableFrom(eventType)) {
                    throw new IllegalArgumentException("Method " + method + " has @RegisterEvent annotation, but takes a argument that is not an Event " + eventType);
                }
                //Saves this in the array
                events.add(new SavedEvent(method, target));
            }
        }
    }

    //This is a small internal class used to store some data about the event
    public
    static class SavedEvent {
        private final Method method;

        private final Object clazz;

        public SavedEvent(Method method, Object clazz) {
            this.method = method;
            this.clazz = clazz;
        }

        public Method getMethod() {
            return method;
        }

        public Object getClazz() {
            return clazz;
        }
    }
}
