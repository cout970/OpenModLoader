package xyz.openmodloader.event;

public class Event {
    private boolean canceled;

    public boolean isCancelable() {
        return false;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
