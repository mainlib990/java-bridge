package bridge.config;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.context.BridgeContext;
import bridge.controller.BridgeController;
import bridge.model.BridgeFactory;
import bridge.model.BridgeGame;
import bridge.model.BridgeMapFactory;
import bridge.model.command.GameCommandFactory;
import bridge.model.move.MovingFactory;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeConfig {

    private final int lowerBridgeSize;
    private final int upperBridgeSize;
    private final String moveUpKey;
    private final String moveDownKey;
    private final String mapDelimiter;
    private final String mapPrefix;
    private final String mapSuffix;
    private final String commandRetryKey;
    private final String commandQuitKey;

    private BridgeContext context;

    public BridgeConfig(BridgeProperties properties) {
        this.lowerBridgeSize = properties.getInt("bridge.size.lower");
        this.upperBridgeSize = properties.getInt("bridge.size.upper");
        this.moveUpKey = properties.getString("bridge.move.up_key");
        this.moveDownKey = properties.getString("bridge.move.down_key");
        this.mapDelimiter = properties.getString("bridge.map.delimiter");
        this.mapPrefix = properties.getString("bridge.map.prefix");
        this.mapSuffix = properties.getString("bridge.map.suffix");
        this.commandRetryKey = properties.getString("bridge.command.retry_key");
        this.commandQuitKey = properties.getString("bridge.command.quit_key");
    }

    public void setContext(BridgeContext context) {
        this.context = context;
    }

    public BridgeGame bridgeGame() {
        return new BridgeGame(this.context);
    }

    public BridgeFactory bridgeFactory() {
        return new BridgeFactory(
                this.context,
                new BridgeMaker(new BridgeRandomNumberGenerator()),
                this.lowerBridgeSize,
                this.upperBridgeSize);
    }

    public BridgeMapFactory bridgeMapFactory() {
        return new BridgeMapFactory(this.moveUpKey);
    }

    public MovingFactory movingFactory() {
        return new MovingFactory(this.moveUpKey, this.moveDownKey);
    }

    public GameCommandFactory gameCommandFactory() {
        return new GameCommandFactory(this.commandRetryKey, this.commandQuitKey);
    }

    public InputView inputView() {
        return new InputView(
                this.moveUpKey,
                this.moveDownKey,
                this.commandRetryKey,
                this.commandQuitKey);
    }

    public OutputView outputView() {
        return new OutputView(
                this.mapDelimiter,
                this.mapPrefix,
                this.mapSuffix);
    }

    public BridgeController bridgeController() {
        return new BridgeController(this.context);
    }
}
