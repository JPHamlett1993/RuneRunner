package VarrockWestSmither;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

public class Constants {

    public static boolean isSmithing(ClientContext ctx) {
        int amount = ctx.inventory.select().id(VarrockWestSmither.Constants.getBronzeBarId()).count();
        Condition.sleep(2000);
        return ctx.inventory.select().id(VarrockWestSmither.Constants.getBronzeBarId()).count() != amount;
    }


    static int[][] path = {
            {3187, 3426},
            {3185, 3429},
            {3184, 3436}
    };

    static Tile[] tilePath = Tile.fromArray(path);

    static Tile smitherTile = new Tile(3187, 3426,0);
    static Tile bankerTile = new Tile(3184, 3436,0);

    static int anvilId = 2097;
    static int daggerMenuWidget = 312;
    static int componentMenuId = 28;
    static int bronzeBarId = 2359;
    static int bronzeDaggerId = 9379;
    static int hammerId = 2347;

    public static Tile getSmitherTile() {
        return smitherTile;
    }

    public static int getAnvilId() {
        return anvilId;
    }

    public static int getDaggerMenuWidget() {
        return daggerMenuWidget;
    }

    public static int getComponentMenuId() {
        return componentMenuId;
    }

    public static int getBronzeBarId() {
        return bronzeBarId;
    }

    public static boolean isMenuOpen(ClientContext ctx) {
        return ctx.widgets.widget(getDaggerMenuWidget()).component(getComponentMenuId()).valid();
    }

    public static Tile[] getTilePath() {
        return tilePath;
    }

    public static Tile getBankerTile() {
        return bankerTile;
    }

    public static int getBronzeDaggerId() {
        return bronzeDaggerId;
    }

    public static int getHammerId() {
        return hammerId;
    }
}

