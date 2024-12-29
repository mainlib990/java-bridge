package bridge.model.command;

public class GameCommandFactory {

    private final GameCommand commandRetry;
    private final GameCommand commandQuit;
    private final String errorMessage;

    public GameCommandFactory(String commandRetryKey, String commandQuitKey) {
        this.commandRetry = new GameCommand(commandRetryKey, true);
        this.commandQuit = new GameCommand(commandQuitKey, false);
        this.errorMessage = String.format("게임 커맨드는 %s (재시도) 또는 %s (종료) 여야 합니다.", commandRetryKey, commandQuitKey);
    }

    public GameCommand getObject(String gameCommandString) {
        if (this.commandRetry.label().equals(gameCommandString)) {
            return this.commandRetry;
        }
        if (this.commandQuit.label().equals(gameCommandString)) {
            return this.commandQuit;
        }
        throw new IllegalArgumentException(this.errorMessage);
    }
}
