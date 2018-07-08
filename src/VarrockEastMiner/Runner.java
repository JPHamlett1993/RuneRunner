package VarrockEastMiner;

import VarrockEastMiner.Tasks.*;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="Miner", description = "Hello", properties = "author=John; topic=999; client=4")
public class Runner extends PollingScript<ClientContext> implements PaintListener{

    private List<Task> taskList = new ArrayList<Task>();
    private String state = "None";
    SettingsGui gui = new SettingsGui();
    public static Bar BARS_TO_MINE;

    @Override
    public void start() {
        gui.setVisible(true);
        taskList.addAll(Arrays.asList(new WalkToBank(ctx), new WalkToMine(ctx), new BankInventory(ctx), new Mine(ctx)));
    }

    @Override
    public void stop() {
        gui.mainFrame.dispose();
    }

    @Override
    public void repaint(Graphics g1) {
        g1.drawString(state, 100, 100);
    }

    @Override
    public void poll() {
        while (BARS_TO_MINE == null);
        for (Task task: taskList) {
            if (task.activate()) {
                state = task.getName();
                task.execute();
            }
        }
    }
}
