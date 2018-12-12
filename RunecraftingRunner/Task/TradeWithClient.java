package RunecraftingRunner.Task;

import RunecraftingRunner.RunecraftingRunner;
import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Player;

public class TradeWithClient extends Task<ClientContext> implements MessageListener {

    public TradeWithClient(ClientContext ctx) {
        super(ctx);
    }
    boolean traded = false;
    @Override
    public boolean activate() {
        return Constants.inAlter(ctx) && ctx.inventory.select().id(Constants.pureEssId).size() > 0;
    }

    @Override
    public void execute() {
        Player p;
        while (!ctx.widgets.widget(Constants.tradeWidget).component(Constants.tradeAcceptChild).valid()) {
            p = ctx.players.name(RunecraftingRunner.playerName).nearest().poll();
            p.interact("Trade");
            int d = 0;
            while (d < 150 && !ctx.widgets.widget(Constants.tradeWidget).component(Constants.tradeAcceptChild).valid()) {
                d++;
                Condition.sleep(150);
            }
        }
        ctx.inventory.select().id(Constants.pureEssId).first().poll().interact("Offer-All");
        while (!ctx.widgets.widget(Constants.tradeWidget).component(Constants.otherPlayerHasAcceptedChild).text().contains("Other")) {
            Condition.sleep(100);
        }
        ctx.widgets.widget(Constants.tradeWidget).component(Constants.tradeAcceptChild).interact("Accept");
        while (!ctx.widgets.widget(Constants.secondTradeWidget).component(Constants.secondScreenOtherPlayerChild).text().contains("Other")) {
            System.out.println(ctx.widgets.widget(Constants.secondTradeWidget).component(Constants.secondScreenOtherPlayerChild).text());
            Condition.sleep(100);
        }
        ctx.widgets.widget(Constants.secondTradeWidget).component(Constants.secondScreenAcceptChild).interact("Accept");
    }

    @Override
    public String getName() {
        return "Trade with client";
    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        System.out.println("Got message");
        System.out.println(messageEvent.text().toLowerCase());
        if (messageEvent.text().toLowerCase().contains("offer")) {
            traded = true;
        }
    }
}
