package bridge.model;

import bridge.context.BridgeContext;
import bridge.controller.BridgeController;
import bridge.model.move.Moving;

import java.util.Objects;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final BridgeController controller;

    private Bridge bridge;
    private int tryNumber;

    public BridgeGame(BridgeContext context) {
        this.controller = context.getBean(BridgeController.class);
        this.tryNumber = 1;
    }

    public void run() {
        while (this.controller.hasNext()) {
            this.controller.next();
        }
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public BridgeMap move(Moving moving) {
        return Objects.requireNonNull(this.bridge, "bridge").cross(moving);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        Objects.requireNonNull(this.bridge, "bridge").reset();
        this.tryNumber++;
    }

    public int getTryNumber() {
        return this.tryNumber;
    }
}
