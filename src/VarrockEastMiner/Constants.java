package VarrockEastMiner;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

public class Constants {

    static final int[][] PATH = {
            {3284, 3363},
            {3289, 3372},
            {3292, 3378},
            {3291, 3386},
            {3291, 3394},
            {3291, 3404},
            {3285, 3411},
            {3280, 3418},
            {3271, 3425},
            {3265, 3429},
            {3258, 3429},
            {3253, 3426},
            {3264, 3425},
            {3254, 3420}
    };

    public static final Tile[] TILE_PATH = Tile.fromArray(PATH);

    public static Tile BANK_TILE = new Tile(3254, 3421, 0);

    public static Tile MINE_TILE = new Tile(3284, 3363, 0);

    public static int[] COPPER_IDS = {7484, 7453};
    public static int[] TIN_IDS = {7486, 7485};
    public static int[] IRON_IDS = {7488, 7455};
    public static int COPPER_INV_IDS = 436;

    public static boolean inMine(ClientContext ctx) {
        int distanceToMine = ctx.movement.distance(MINE_TILE);
        return distanceToMine != -1 && distanceToMine < 10;
    }

    public static boolean inBank(ClientContext ctx) {
        int distanceToBank = ctx.movement.distance(BANK_TILE);
        return distanceToBank != -1 && distanceToBank < 5;
    }
}
