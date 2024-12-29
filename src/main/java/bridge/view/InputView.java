package bridge.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private final String upKey;
    private final String downKey;
    private final String retryKey;
    private final String quitKey;

    public InputView(String upKey,
                     String downKey,
                     String retryKey,
                     String quitKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.retryKey = retryKey;
        this.quitKey = quitKey;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public String readBridgeSize() {
        System.out.println();
        System.out.println("다리의 길이를 입력해주세요.");
        return Console.readLine();
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println();
        System.out.printf("이동할 칸을 선택해주세요. (위: %s, 아래: %s)%n", this.upKey, this.downKey);
        return Console.readLine();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println();
        System.out.printf("게임을 다시 시도할지 여부를 입력해주세요. (재시도: %s, 종료: %s)%n", this.retryKey, this.quitKey);
        return Console.readLine();
    }
}
