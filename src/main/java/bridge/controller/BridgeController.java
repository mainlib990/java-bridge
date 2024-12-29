package bridge.controller;

import bridge.context.BridgeContext;

import java.util.Objects;

import static bridge.controller.BridgeState.END;
import static bridge.controller.BridgeState.START;

public class BridgeController {

    private final BridgeContext context;

    private BridgeState state;

    public BridgeController(BridgeContext context) {
        this(context, START);
    }

    public BridgeController(BridgeContext context, BridgeState state) {
        this.context = context;
        this.state = state;
    }

    public boolean hasNext() {
        return state != END;
    }

    public void next() {
        this.state = Objects.requireNonNull(this.state, "state").proceed(context);
    }
}
