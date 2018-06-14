package AlKharidSmelter;

import org.powerbot.script.Tile;

public class Constants {

    private static int[][] path = {
            {3269, 3168},
            {3275, 3169},
            {3276, 3174},
            {3280, 3181},
            {3275, 3185}
    };

    static Tile[] tilePath = Tile.fromArray(path);

    static Tile bankTile = new Tile(3269, 3168, 0);
    static Tile furnaceTile = new Tile(3275, 3185,0);

    static int tinId = 438;
    static int copperId = 436;
    static int furnaceId = 24009;

    public static Tile getBankTile() {
        return bankTile;
    }

    public static Tile getFurnaceTile() {
        return furnaceTile;
    }

    public static int getTinId() {
        return tinId;
    }

    public static int getCopperId() {
        return copperId;
    }

    public static Tile[] getTilePath() {
        return tilePath;
    }

    public static int getFurnaceId() {
        return furnaceId;
    }
}
