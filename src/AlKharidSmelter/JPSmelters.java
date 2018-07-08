package AlKharidSmelter;

import AlKharidSmelter.Tasks.*;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Script.Manifest(name="JPs Smelter", description = "Smelts All Bars in Al Khararid", properties = "author=John; topic=999; client=4")
public class JPSmelters extends PollingScript<ClientContext> implements PaintListener{

    private List<Task> taskList = new ArrayList<Task>();
    private String state = "None";
    public static Bar BAR_TO_SMELT;
    SettingsGui gui = new SettingsGui();
    public static int numberSmelted = 0;

    @Override
    public void start() {
        gui.setVisible(true);
        taskList.addAll(Arrays.asList(new BankInventory(ctx), new WalkToBank(ctx), new WalkToSmelter(ctx), new Smelt(ctx)));
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(255, 51, 51);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 17);

    public String longToTimeStamp(long millis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    public double estimatePerHour() {
        return numberSmelted * (3600000 / getRuntime());
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
        g.drawString("Smelting: " + BAR_TO_SMELT, 9, 65);
        g.drawString("Amount Smelted: " + numberSmelted, 9, 95);
        g.drawString("Amount Per Hour: " + estimatePerHour(), 9, 125);
        g.drawString("Current State: " + state, 9, 155);
    }
    //END: Code generated using Enfilade's Easel

    @Override
    public void poll() {
        while (BAR_TO_SMELT != null) {
            for (Task task: taskList) {
                if (task.activate()) {
                    state = task.getName();
                    task.execute();
                }
            }
        }
    }
}
