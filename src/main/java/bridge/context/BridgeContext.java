package bridge.context;

import bridge.config.BridgeConfig;
import bridge.controller.BridgeController;
import bridge.model.BridgeFactory;
import bridge.model.BridgeGame;
import bridge.model.BridgeMapFactory;
import bridge.model.command.GameCommandFactory;
import bridge.model.move.MovingFactory;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class BridgeContext {

    private final Map<Class<?>, Object> classBeanMap = new HashMap<>();
    private final ResultContext resultContext;

    public BridgeContext(BridgeConfig config) {
        config.setContext(this);
        registerBeans(config);
        this.resultContext = new ResultContext();
    }

    private void registerBeans(BridgeConfig config) {
        this.classBeanMap.put(BridgeController.class, config.bridgeController());
        this.classBeanMap.put(BridgeGame.class, config.bridgeGame());
        this.classBeanMap.put(BridgeMapFactory.class, config.bridgeMapFactory());
        this.classBeanMap.put(BridgeFactory.class, config.bridgeFactory());
        this.classBeanMap.put(MovingFactory.class, config.movingFactory());
        this.classBeanMap.put(GameCommandFactory.class, config.gameCommandFactory());
        this.classBeanMap.put(InputView.class, config.inputView());
        this.classBeanMap.put(OutputView.class, config.outputView());
    }

    public <T> T getBean(Class<T> clazz) {
        Object o = this.classBeanMap.get(clazz);
        if (clazz.equals(o.getClass())) {
            return clazz.cast(o);
        }
        throw new IllegalArgumentException(clazz.getName());
    }

    public ResultContext getResultContext() {
        return this.resultContext;
    }
}
