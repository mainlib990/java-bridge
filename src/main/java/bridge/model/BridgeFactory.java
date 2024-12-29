package bridge.model;

import bridge.BridgeMaker;
import bridge.context.BridgeContext;

public class BridgeFactory {

    private final BridgeMapFactory bridgeMapFactory;
    private final BridgeMaker bridgeMaker;
    private final int startInclusive;
    private final int endInclusive;
    private final String errorMessage;

    public BridgeFactory(BridgeContext context,
                         BridgeMaker bridgeMaker,
                         int startInclusive,
                         int endInclusive) {
        this.bridgeMapFactory = context.getBean(BridgeMapFactory.class);
        this.bridgeMaker = bridgeMaker;
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.errorMessage = String.format("다리 길이는 %d부터 %d 사이의 숫자여야 합니다.", startInclusive, endInclusive);
    }

    public Bridge getObject(String bridgeSizeString) {
        int bridgeSize;
        try {
            bridgeSize = Integer.parseInt(bridgeSizeString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(this.errorMessage, e);
        }
        if (bridgeSize < this.startInclusive || this.endInclusive < bridgeSize) {
            throw new IllegalArgumentException(this.errorMessage);
        }
        return new Bridge(this.bridgeMapFactory, this.bridgeMaker.makeBridge(bridgeSize));
    }
}
