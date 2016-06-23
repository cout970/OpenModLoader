package xyz.openmodloader.event.impl;

import java.util.List;

import xyz.openmodloader.event.Event;

public class SplashLoadEvent extends Event {
    private final List<String> splashTexts;

    public SplashLoadEvent(List<String> splashTexts) {
        this.splashTexts = splashTexts;
    }

    public List<String> getSplashTexts() {
        return splashTexts;
    }
}
