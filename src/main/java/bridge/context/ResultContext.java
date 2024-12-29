package bridge.context;

import java.util.Objects;

public class ResultContext {

    private String[][] bridgeMap;
    private Boolean gameClear;
    private Integer tryNumber;

    public ResultContext setBridgeMap(String[][] bridgeMap) {
        this.bridgeMap = bridgeMap;
        return this;
    }

    public ResultContext setGameClear(boolean gameClear) {
        this.gameClear = gameClear;
        return this;
    }

    public ResultContext setTryNumber(int tryNumber) {
        this.tryNumber = tryNumber;
        return this;
    }

    public String[][] getBridgeMap() {
        return this.bridgeMap;
    }

    public boolean getGameClear() {
        return Objects.requireNonNull(this.gameClear, "gameClear");
    }

    public int getTryNumber() {
        return Objects.requireNonNull(this.tryNumber, "tryNumber");
    }

    public void reset() {
        this.bridgeMap = null;
        this.gameClear = null;
        this.tryNumber = null;
    }
}
