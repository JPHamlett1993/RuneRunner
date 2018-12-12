package RunecraftingRunner.Task;


import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class Constants {

    public static final Tile[] path = {new Tile(2987, 3290, 0), new Tile(2989, 3293, 0), new Tile(2991, 3296, 0), new Tile(2991, 3299, 0), new Tile(2991, 3302, 0), new Tile(2994, 3305, 0), new Tile(2997, 3308, 0), new Tile(2999, 3311, 0), new Tile(2999, 3314, 0), new Tile(3000, 3317, 0), new Tile(3003, 3319, 0), new Tile(3006, 3322, 0), new Tile(3006, 3325, 0), new Tile(3006, 3328, 0), new Tile(3006, 3331, 0), new Tile(3006, 3334, 0), new Tile(3007, 3337, 0), new Tile(3007, 3340, 0), new Tile(3007, 3343, 0), new Tile(3008, 3346, 0), new Tile(3009, 3349, 0), new Tile(3008, 3352, 0), new Tile(3008, 3355, 0), new Tile(3008, 3358, 0), new Tile(3011, 3359, 0), new Tile(3012, 3356, 0)};

    public static boolean inBank(ClientContext ctx) {
        return new Area(new Tile(3010, 3358), new Tile(3016, 3355)).contains(ctx.players.local().tile());
    }

    public static boolean inAlter(ClientContext ctx) {
        return ctx.players.local().tile().y() > 4000;
    }

    public static int notedPureEssId = 7937;
    public static int pureEssId = 7936;

    public static int tradeWidget = 335;
    public static int secondTradeWidget = 334;
    public static int tradeAcceptChild = 12;
    public static int otherPlayerHasAcceptedChild = 30;
    public static int secondScreenAcceptChild = 25;
    public static int secondScreenOtherPlayerChild = 4;
}
