package bridge.model.command;

public final class GameCommand {

    private final String label;
    private final boolean retry;

    public GameCommand(String label, boolean retry) {
        this.label = label;
        this.retry = retry;
    }

    public String label() {
        return this.label;
    }

    public boolean isRetry() {
        return this.retry;
    }
}
