package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class SmithDaggers extends Task<ClientContext> {

    public SmithDaggers(ClientContext ctx) {
        super(ctx);
    }



    @Override
    public boolean activate() {
        return Constants.isMenuOpen(ctx);
    }

    @Override
    public void execute() {
        ctx.widgets.widget(Constants.getDaggerMenuWidget()).component(Constants.getComponentMenuId()).interact("All", "Mithril bolts");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.widgets.widget(233).component(1).valid() || Constants.isSmithing(ctx);
            }
        }, 100, 1000);
    }

    @Override
    public String getName() {
        return "Smithing";
    }
}
