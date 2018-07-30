package Superheat.Task;

import com.sun.tools.internal.jxc.ap.Const;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import Superheat.*;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Magic;

public class Bank extends Task<ClientContext> {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(Constants.IRON_ORE).count() == 0 ||
                ctx.inventory.select().id(Constants.COAL_ORE).count() < ctx.inventory.select().id(Constants.IRON_ORE).count() * 2;

    }

    @Override
    public void execute() {
        if (ctx.magic.casting(Magic.Spell.SUPERHEAT_ITEM)) {
            ctx.input.click(true);
        }
        Main.amountMined += ctx.inventory.select().id(2353).count();
        while (!ctx.bank.opened()) {
            int tries = 0;
            ctx.bank.open();
            do {
                Condition.sleep(10);
                tries++;
            } while (tries < 15 && !ctx.bank.opened());
        }
        for (Item item : ctx.inventory.select()) {
            int tries = 0;
            if (!item.name().contains("ature")) {
                ctx.bank.deposit(item.id(), org.powerbot.script.rt4.Bank.Amount.ALL);
            }
            do {
                Condition.sleep(10);
                tries++;
            } while (tries < 15 && ctx.inventory.select().id(item.id()).count() > 0);
        }
        while (ctx.inventory.select().id(Constants.IRON_ORE).count() == 0) {
            ctx.bank.withdraw(Constants.IRON_ORE, 9);
            int tries = 0;
            while (tries < 15 && ctx.inventory.select().id(Constants.IRON_ORE).count() == 0) {
                Condition.sleep(10);
                tries++;
            }
        }
        while (ctx.inventory.select().id(Constants.COAL_ORE).count() == 0) {
            ctx.bank.withdraw(Constants.COAL_ORE, org.powerbot.script.rt4.Bank.Amount.ALL);
            int tries = 0;
            while (tries < 15 && ctx.inventory.select().id(Constants.COAL_ORE).count() == 0) {
                Condition.sleep(10);
                tries++;
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
