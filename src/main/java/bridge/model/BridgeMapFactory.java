package bridge.model;

import java.util.stream.IntStream;

public class BridgeMapFactory {

    private static final String[][][] upMatchTable = {
            {{"O", " "}, {"X", " "}},
            {{" ", "O"}, {" ", "X"}}
    };

    private final String moveUpKey;

    public BridgeMapFactory(String moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    public BridgeMap getObject(Bridge bridge) {
        String[][] zipMap = IntStream.range(0, bridge.movings.size())
                .mapToObj(idx -> zip(bridge, idx))
                .toArray(String[][]::new);
        String[][] unzipMap = unzip(zipMap);
        return new BridgeMap(unzipMap, bridge.gameOver, bridge.gameClear);
    }

    private String[] zip(Bridge bridge, int idx) {
        String v = bridge.value.get(idx);
        String m = bridge.movings.get(idx).label();
        int up = Boolean.TRUE.compareTo(this.moveUpKey.equals(m));
        int match = Boolean.TRUE.compareTo(m.equals(v));
        return upMatchTable[up][match];
    }

    private String[][] unzip(String[][] map) {
        var newMap = new String[2][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                newMap[j][i] = map[i][j];
            }
        }
        return newMap;
    }
}
