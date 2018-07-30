package JP_AIO_Miner.Task;

import JP_AIO_Miner.*;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Item;


import java.util.concurrent.Callable;

public class Bank extends Task<ClientContext> {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
                ctx.objects.select().name(Constants.BANK_CHESTS).poll().inViewport()
                && !ctx.players.local().inMotion();
    }



    @Override
    public void execute() {
        JP_AIO_Miner.state = "Bank";
        JP_AIO_Miner.amountMined += JP_AIO_Miner.getAmountMined(ctx);
        while (!ctx.bank.opened()) {
            int tries = 0;
            ctx.bank.open();
            do {
                Condition.sleep(10);
                tries++;
            } while (tries < 15 && !ctx.bank.opened());
        }
        while (ctx.inventory.select().count() == 28) {
            for (Item item : ctx.inventory.select()) {
                int tries = 0;
                if (!item.name().contains("ickaxe")) {
                    ctx.bank.deposit(item.id(), org.powerbot.script.rt4.Bank.Amount.ALL);
                }
                do {
                    Condition.sleep(10);
                    tries++;
                } while (tries < 15 && ctx.inventory.select().id(item.id()).count() > 0);
            }
        }
        while (ctx.bank.opened()) {
            int tries = 0;
            ctx.bank.close();
            do {
                Condition.sleep(10);
                tries++;
            } while (tries < 15 && ctx.bank.opened());
        }

    }
}
