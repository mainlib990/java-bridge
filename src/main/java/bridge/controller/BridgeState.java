package bridge.controller;

import bridge.context.BridgeContext;
import bridge.context.ResultContext;
import bridge.model.Bridge;
import bridge.model.BridgeFactory;
import bridge.model.BridgeGame;
import bridge.model.BridgeMap;
import bridge.model.command.GameCommand;
import bridge.model.command.GameCommandFactory;
import bridge.model.move.Moving;
import bridge.model.move.MovingFactory;
import bridge.view.InputView;
import bridge.view.OutputView;

public enum BridgeState {

    START {
        @Override
        protected BridgeState next(BridgeContext context) {
            context.getBean(OutputView.class).printStart();
            return READ_BRIDGE_SIZE;
        }
    }, READ_BRIDGE_SIZE {
        @Override
        protected BridgeState next(BridgeContext context) {
            String bridgeSizeString = context.getBean(InputView.class).readBridgeSize();
            Bridge bridge = context.getBean(BridgeFactory.class).getObject(bridgeSizeString);
            context.getBean(BridgeGame.class).setBridge(bridge);
            return READ_MOVING;
        }
    }, READ_MOVING {
        @Override
        protected BridgeState next(BridgeContext context) {
            String movingString = context.getBean(InputView.class).readMoving();
            Moving moving = context.getBean(MovingFactory.class).getObject(movingString);
            BridgeMap bridgeMap = context.getBean(BridgeGame.class).move(moving);
            context.getBean(OutputView.class).printMap(bridgeMap.getValues());
            return getBridgeState(bridgeMap, context);
        }

        private BridgeState getBridgeState(BridgeMap bridgeMap, BridgeContext context) {
            if (bridgeMap.isGameOver()) {
                saveResultContext(bridgeMap, context);
                return READ_GAME_COMMAND;
            }
            if (bridgeMap.isGameClear()) {
                saveResultContext(bridgeMap, context);
                return PRINT_RESULT;
            }
            return this;
        }

        private void saveResultContext(BridgeMap bridgeMap, BridgeContext context) {
            ResultContext resultContext = context.getResultContext();
            resultContext.setBridgeMap(bridgeMap.getValues())
                    .setGameClear(bridgeMap.isGameClear())
                    .setTryNumber(context.getBean(BridgeGame.class).getTryNumber());
        }
    }, READ_GAME_COMMAND {
        @Override
        protected BridgeState next(BridgeContext context) {
            String gameCommandString = context.getBean(InputView.class).readGameCommand();
            GameCommand gameCommand = context.getBean(GameCommandFactory.class).getObject(gameCommandString);
            return getBridgeState(gameCommand, context);
        }

        private BridgeState getBridgeState(GameCommand gameCommand, BridgeContext context) {
            if (gameCommand.isRetry()) {
                context.getBean(BridgeGame.class).retry();
                context.getResultContext().reset();
                return READ_MOVING;
            }
            return PRINT_RESULT;
        }
    }, PRINT_RESULT {
        @Override
        protected BridgeState next(BridgeContext context) {
            ResultContext resultContext = context.getResultContext();
            String[][] bridgeMapStrings = resultContext.getBridgeMap();
            boolean gameClear = resultContext.getGameClear();
            int tryNumber = resultContext.getTryNumber();
            context.getBean(OutputView.class).printResult(bridgeMapStrings, gameClear, tryNumber);
            return END;
        }
    }, END {
        @Override
        protected BridgeState next(BridgeContext context) {
            return null;
        }
    };

    public BridgeState proceed(BridgeContext context) {
        while (true) {
            try {
                return next(context);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    protected abstract BridgeState next(BridgeContext context);
}
