package bridge;

import bridge.config.BridgeConfig;
import bridge.config.BridgeProperties;
import bridge.context.BridgeContext;
import bridge.model.BridgeGame;

public class Application {

    public static void main(String[] args) {
        var bridgeProperties = new BridgeProperties("application.properties");
        var context = new BridgeContext(new BridgeConfig(bridgeProperties));
        BridgeGame game = context.getBean(BridgeGame.class);
        game.run();
    }
}
