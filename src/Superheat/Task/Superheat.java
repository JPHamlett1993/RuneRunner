package Superheat.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import Superheat.*;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Magic;

public class Superheat extends Task<ClientContext> {

    public Superheat(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(Constants.IRON_ORE).count() > 0
        && ctx.inventory.select().id(Constants.COAL_ORE).count() >= ctx.inventory.select().id(Constants.IRON_ORE).count() * 2;
    }

    @Override
    public void execute() {
        if (ctx.game.tab() != Game.Tab.MAGIC) {
            int tries = 0;
            ctx.game.tab(Game.Tab.MAGIC);
            while (tries < 5 && ctx.game.tab() != Game.Tab.MAGIC) {
                Condition.sleep(10);
                tries++;
            }
        }
        ctx.magic.cast(Magic.Spell.SUPERHEAT_ITEM);
        int tries = 0;
        while (tries < 5 && ctx.game.tab() != Game.Tab.INVENTORY) {
            Condition.sleep(10);
            tries++;
        }
        ctx.inventory.select().id(Constants.IRON_ORE).first().poll().click();
        Condition.sleep(450);
        tries = 0;
        while (tries < 5 && ctx.players.local().animation() != -1) {
            Condition.sleep();
            tries++;
        }
    }
}
