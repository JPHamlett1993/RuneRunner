package BlastFurnace.Tasks.Bank;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class WithdrawOres extends Task<ClientContext> {

    public WithdrawOres(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && !Constants.hasBarsInInventory(ctx) && !Constants.hasOresInInventory(ctx);
    }

    @Override
    public void execute() {
        if (Furnace.useStaminaPotions && !Constants.hasStaminaPotionInventory(ctx) && Constants.hasStaminaPotionBank(ctx)) {
            ctx.bank.withdraw(Constants.STAMINA_POTION_IDS[0], 1);
        }
        int ore_id = 0;
        switch (Furnace.ORES_TO_SMELT[Constants.oreIndex]) {
            case COAL: {
                ore_id = Constants.COAL_ID;
                break;
            }
            case MITHRIL: {
                ore_id = Constants.MITHRIL_ID;
                break;
            }
            case ADAMANT: {
                ore_id = Constants.ADAMANT_ID;
                break;
            }
            case RUNITE: {
                ore_id = Constants.RUNE_ID;
                break;
            }
            case GOLD: {
                ore_id = Constants.GOLD_ID;
                break;
            }
            case IRON: {
                ore_id = Constants.IRON_ID;
                break;
            }

        }
        Furnace.printMessage("Withdrawing all ores: " + ore_id);
        ctx.bank.withdraw(ore_id, Bank.Amount.ALL);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().count() > 0;
            }
        }, 100, 10);
        Furnace.printMessage("Withdrew all ores: " + ore_id);
    }

    @Override
    public String getName() {
        return "Withdrawing Ores";
    }
}
