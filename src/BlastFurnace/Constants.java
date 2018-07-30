package BlastFurnace;

import BlastFurnace.Tasks.Ore;
import org.powerbot.script.rt4.ClientContext;

public class Constants {

    public final static int IRON_BAR_ID = 2351;
    public final static int STEEL_BAR_ID = 2353;
    public final static int GOLD_BAR_ID = 2357;
    public final static int MITHRIL_BAR_ID = 2359;
    public final static int ADAMANT_BAR_ID = 2361;
    public final static int RUNE_BAR_ID = 2363;

    public final static int[] BAR_IDS = {
            IRON_BAR_ID,
            STEEL_BAR_ID,
            GOLD_BAR_ID,
            MITHRIL_BAR_ID,
            ADAMANT_BAR_ID,
            RUNE_BAR_ID
    };

    public final static int IRON_ID = 440;
    public final static int COAL_ID = 453;
    public final static int GOLD_ID = 444;
    public final static int MITHRIL_ID = 447;
    public final static int ADAMANT_ID = 449;
    public final static int RUNE_ID = 451;

    public final static int[] ORE_IDS = {
            IRON_ID,
            COAL_ID,
            GOLD_ID,
            MITHRIL_ID,
            ADAMANT_ID,
            RUNE_ID
    };

    public final static Ore[] IRON_ORDER = {Ore.IRON, null};
    public final static Ore[] STEEL_ORDER = {Ore.COAL, Ore.IRON, null};
    public final static Ore[] GOLD_ORDER = {Ore.GOLD, null};
    public final static Ore[] MITHRIL_ORDER = {Ore.COAL, Ore.COAL, Ore.MITHRIL, null};
    public final static Ore[] ADAMANT_ORDER = {Ore.COAL, Ore.COAL, Ore.COAL, Ore.ADAMANT, null};
    public final static Ore[] RUNITE_ORDER = {Ore.COAL, Ore.COAL, Ore.COAL, Ore.COAL, Ore.RUNITE, null};

    public final static int[] STAMINA_POTION_IDS = {12625, 12627, 12629, 12631};

    public final static int BELT_ID = 9100;
    public final static int COAL_BAG_ID = 18339;
    public final static int GOLD_GLOVE_ID = 776;
    public final static int ICE_GLOVE_ID = 1580;


    public final static int[] PERM_ITEMS = {COAL_BAG_ID, 12625, 12627, 12629, 12631};

    public static boolean hasStaminaPotionInventory(ClientContext ctx) {
        Furnace.printMessage("Checking if we have a stamina potion in inventory");
        for (int staminaId : STAMINA_POTION_IDS) {
            if (ctx.inventory.select().id(staminaId).count() > 0) {
                Furnace.printMessage("We have a stamina potion in inventory");
                return true;
            }
        }
        Furnace.printMessage("We do not have a stamina potion in inventory");
        return false;
    }

    public static boolean hasStaminaPotionBank(ClientContext ctx) {
        Furnace.printMessage("Checking if we have a stamina potion in bank");
        for (int staminaId : STAMINA_POTION_IDS) {
            if (ctx.bank.select().id(staminaId).count() > 0) {
                Furnace.printMessage("We have a stamina potion in bank");
                return true;
            }
        }
        Furnace.printMessage("We do not have a stamina potion in bank");
        return false;
    }

    public static boolean hasOresInInventory(ClientContext ctx) {
        Furnace.printMessage("Checking if we have ores");
        for (int oreID : ORE_IDS) {
            if (ctx.inventory.select().id(oreID).count() > 0) {
                Furnace.printMessage("We have ores");
                return true;
            }
        }
        Furnace.printMessage("We do not have ores");
        return false;
    }

    public static boolean hasBarsInInventory(ClientContext ctx) {
        Furnace.printMessage("Checking if we have bars");
        for (int barId : BAR_IDS) {
            if (ctx.inventory.select().id(barId).count() > 0) {
                Furnace.printMessage("We have bars");
                return true;
            }
        }
        Furnace.printMessage("We do not have bars");
        return false;
    }

    public static boolean isBarInterfaceOpen(ClientContext ctx) {
        int widgetId = 270;
        int componentId = 14;
        return ctx.widgets.widget(widgetId).component(componentId).valid();
    }
    public static int oreIndex = 0;

    public final static int SMITHING_SKILL_INDEX = 13;

}
