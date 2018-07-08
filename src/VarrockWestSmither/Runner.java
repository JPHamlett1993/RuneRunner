package VarrockWestSmither;

import VarrockWestSmither.Tasks.*;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="Smither", description = "Hello", properties = "author=John; topic=999; client=4")
public class Runner extends PollingScript<ClientContext> implements PaintListener{

    private List<Task> taskList = new ArrayList<Task>();
    private String state = "None";

    @Override
    public void start() {
        taskList.addAll(Arrays.asList(new OpenSmithingMenu(ctx), new SmithDaggers(ctx), new CloseBank(ctx), new DepositDaggers(ctx), new OpenBank(ctx), new WalkToAnvil(ctx), new WalkToBank(ctx), new WithdrawBars(ctx)));
    }

    @Override
    public void repaint(Graphics g1) {
        g1.drawString(state, 100, 100);
    }

    @Override
    public void poll() {
        for (Task task: taskList) {
            if (task.activate()) {
                state = task.getName();
                task.execute();
            }
        }
    }
}
