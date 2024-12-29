package bridge.model;

import bridge.model.move.Moving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Bridge {

    private final BridgeMapFactory bridgeMapFactory;

    final List<String> value;
    final List<Moving> movings;

    boolean gameOver;
    boolean gameClear;

    public Bridge(BridgeMapFactory bridgeMapFactory, List<String> value) {
        this.bridgeMapFactory = bridgeMapFactory;
        this.value = Collections.unmodifiableList(value);
        this.movings = new ArrayList<>(value.size());
        this.gameOver = false;
        this.gameClear = false;
    }

    public BridgeMap cross(Moving moving) {
        this.gameOver = !this.value.get(this.movings.size()).equals(moving.label());
        this.movings.add(moving);
        this.gameClear = !this.gameOver && value.size() == movings.size();
        return bridgeMapFactory.getObject(this);
    }

    public void reset() {
        this.movings.clear();
        this.gameOver = false;
    }
}
