package JP_AIO_Miner;


import org.powerbot.script.rt4.ClientContext;

public class Constants {

    public static final int[] TIN_ROCKS = {};
    public static final int[] COPPER_ROCKS = {};
    public static final int[] SILVER_ROCKS = {};
    public static final int[] IRON_ROCKS = {7488, 7455};
    public static final int[] COAL_ROCKS = {7489, 7456};
    public static final int[] GOLD_ROCKS = {};
    public static final int[] MITHRIL_ROCKS = {7492, 7459};
    public static final int[] ADAMANT_ROCKS = {7493, 7460};
    public static final int[] RUNE_ROCKS = {7494, 7461};
    public static final int TIN_ORE = 438;
    public static final int COPPER_ORE = 436;
    public static final int IRON_ORE = 440;
    public static final int SILVER_ORE = 442;
    public static final int COAL_ORE = 453;
    public static final int GOLD_ORE = 444;
    public static final int MITHRIL_ORE = 447;
    public static final int ADAMANT_ORE = 449;
    public static final int RUNE_ORE = 451;

    public static int[] getIdOfRock(ClientContext ctx) {
        if (JP_AIO_Miner.ore == null) {
            switch (JP_AIO_Miner.bar) {
                case BRONZE: {
                    if (ctx.inventory.select().id(Constants.TIN_ORE).count() == 14) {
                        return Constants.COPPER_ROCKS;
                    } else {
                        return Constants.TIN_ROCKS;
                    }
                }
                case IRON: {
                    return Constants.IRON_ROCKS;
                }
                case SILVER: {
                    return Constants.SILVER_ROCKS;
                }
                case STEEL: {
                    if (ctx.inventory.select().id(Constants.IRON_ORE).count() == 9) {
                        return Constants.COAL_ROCKS;
                    } else {
                        return Constants.IRON_ROCKS;
                    }
                }
                case GOLD: {
                    return Constants.GOLD_ROCKS;
                }
                case MITHRIL: {
                    if (ctx.inventory.select().id(Constants.MITHRIL_ORE).count() == 5) {
                        return Constants.COAL_ROCKS;
                    } else {
                        return MITHRIL_ROCKS;
                    }
                }
                case ADAMANT: {
                    if (ctx.inventory.select().id(Constants.ADAMANT_ORE).count() == 4) {
                        return Constants.COAL_ROCKS;
                    } else {
                        return Constants.ADAMANT_ROCKS;
                    }
                }
                case RUNE: {
                    if (ctx.inventory.select().id(Constants.RUNE_ORE).count() == 2) {
                        return Constants.COAL_ROCKS;
                    } else {
                        return Constants.RUNE_ROCKS;
                    }
                }
            }
        } else {
            switch (JP_AIO_Miner.ore) {
                case TIN : {
                    return Constants.TIN_ROCKS;
                }
                case COPPER : {
                    return Constants.COPPER_ROCKS;
                }
                case IRON : {
                    return Constants.IRON_ROCKS;
                }
                case SILVER: {
                    return Constants.SILVER_ROCKS;
                }
                case COAL: {
                    return Constants.COAL_ROCKS;
                }
                case GOLD: {
                    return Constants.GOLD_ROCKS;
                }
                case MITHRIL: {
                    return Constants.MITHRIL_ROCKS;
                }
                case ADAMANT: {
                    return Constants.ADAMANT_ROCKS;
                }
                case RUNE: {
                    return Constants.RUNE_ROCKS;
                }
            }
        }
        return null;
    }


}
