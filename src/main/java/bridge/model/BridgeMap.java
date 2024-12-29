package bridge.model;

public final class BridgeMap {

    private final String[][] values;
    private final boolean gameOver;
    private final boolean gameClear;

    public BridgeMap(String[][] values, boolean gameOver, boolean gameClear) {
        this.values = values;
        this.gameOver = gameOver;
        this.gameClear = gameClear;
    }

    public String[][] getValues() {
        return values;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameClear() {
        return gameClear;
    }
}
