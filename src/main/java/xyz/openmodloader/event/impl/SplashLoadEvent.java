package xyz.openmodloader.event.impl;

import xyz.openmodloader.event.Event;
import java.util.List;

public class SplashLoadEvent extends Event {
    private final List<String> splashTexts;

    public SplashLoadEvent(List<String> splashTexts) {
        this.splashTexts = splashTexts;
    }

    public List<String> getSplashTexts() {
        return splashTexts;
    }
}
