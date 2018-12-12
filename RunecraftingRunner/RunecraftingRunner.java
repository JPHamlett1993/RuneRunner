package RunecraftingRunner;

import RunecraftingRunner.Task.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PaintListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="JP Rune Runner", description="JP AIO Miner")
public class RunecraftingRunner  extends PollingScript<ClientContext>  implements PaintListener {

    private List<Task> taskList = new ArrayList();
    public static String playerName = null;
    public static Integer amountOfRuneEss = null;
    String state = "";

    @Override
    public void start() {
        new GUI().init();
        taskList.addAll(Arrays.asList(new LeaveAlter(ctx), new WalkToBank(ctx), new ExchangeNotes(ctx), new WalkToAlter(ctx),
                new EnterAlter(ctx), new TradeWithClient(ctx)));
//        taskList.addAll(Arrays.asList(new Mine(ctx), new WalkToRocks(ctx), new WalkToBank(ctx), new Bank(ctx)));

    }

    @Override
    public void poll() {
        if (playerName != null && amountOfRuneEss != null) {
            for (Task task : taskList) {
                if (task.activate()) {
                    state = task.getName();
                    task.execute();
                }
            }
        }
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(255, 51, 51);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 17);

    public String longToTimeStamp(long millis) {
        long seconds, minutes, hours;
        seconds = millis / 1000;
        minutes = seconds / 60;
        seconds = seconds % 60;
        hours = minutes / 60;
        minutes = minutes % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void repaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(3, 4, 250, 160, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(3, 4, 250, 160, 16, 16);
        g.setFont(font1);
        g.drawString("Time Running: " + longToTimeStamp(getRuntime()), 9, 35);
        g.drawString("Parent Account: " + playerName, 9, 125);
        g.drawString("Current State: " + state, 9, 155);
    }
    //END: Code generated using Enfilade's Easel
}
