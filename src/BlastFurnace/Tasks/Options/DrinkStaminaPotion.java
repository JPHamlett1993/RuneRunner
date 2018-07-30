package BlastFurnace.Tasks.Options;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class DrinkStaminaPotion extends Task<ClientContext> {

    public DrinkStaminaPotion(ClientContext ctx) {
        super(ctx);
    }

    public boolean isStaminaPotionActive() {
        return ctx.varpbits.varpbit(638) == 0x100000;
    }

    @Override
    public boolean activate() {
        return !ctx.bank.opened() && Furnace.useStaminaPotions && !isStaminaPotionActive() && Constants.hasStaminaPotionInventory(ctx);
    }

    @Override
    public void execute() {
        Furnace.printMessage("Drinking Stamina Potion");
        while (!isStaminaPotionActive()) {
            for (int staminaId: Constants.STAMINA_POTION_IDS) {
                ctx.inventory.select().id(staminaId).poll().interact("Drink");
            }
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return isStaminaPotionActive();
                }
            }, 10, 75);
        }
        Furnace.printMessage("Drank Stamina Potion");
    }

    @Override
    public String getName() {
        return "Drinking Stamina Potion";
    }
}
