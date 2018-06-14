package VarrockEastMiner;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

public class Constants {

    static final int[][] path = {
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

    static final Tile[] tilePath = Tile.fromArray(path);

    static Tile bankTile = new Tile(3254, 3421, 0);

    static Tile mineTile = new Tile(3284, 3363, 0);

    static int[] copperIds = {7484, 7453};
    static int[] tinIds = {7486, 7485};
    static int copperInvId = 436;
    static int tinInvId  = 438;

    public static boolean inMine(ClientContext ctx) {
        int distanceToMine = ctx.movement.distance(mineTile);
        return distanceToMine != -1 && distanceToMine < 15;
    }

    public static boolean inBank(ClientContext ctx) {
        int distanceToBank = ctx.movement.distance(bankTile);
        return distanceToBank != -1 && distanceToBank < 5;
    }

    public static  Tile[] getPath() {
        return tilePath;
    }

    public static int[] getCopperIds() {
        return copperIds;
    }

    public static int[] getTinIds() {
        return tinIds;
    }

    public static int getCopperInvId() {
        return copperInvId;
    }

    public static int getTinInvId() {
        return tinInvId;
    }
}
