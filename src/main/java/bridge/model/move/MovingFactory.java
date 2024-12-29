package bridge.model.move;

public class MovingFactory {

    private final Moving moveUp;
    private final Moving moveDown;
    private final String errorMessage;

    public MovingFactory(String moveUpKey, String moveDownKey) {
        this.moveUp = new Moving(moveUpKey);
        this.moveDown = new Moving(moveDownKey);
        this.errorMessage = String.format("이동할 칸은 %s (위) 또는 %s (아래)여야 합니다.", moveUpKey, moveDownKey);
    }

    public Moving getObject(String movingString) {
        if (this.moveUp.label().equals(movingString)) {
            return this.moveUp;
        }
        if (this.moveDown.label().equals(movingString)) {
            return this.moveDown;
        }
        throw new IllegalArgumentException(this.errorMessage);
    }
}
