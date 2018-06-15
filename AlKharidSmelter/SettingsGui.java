package AlKharidSmelter;

import AlKharidSmelter.Tasks.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGui extends JFrame {

    JFrame mainFrame;

    public SettingsGui() {
        final JRadioButton bronzeButton = new JRadioButton("Bronze");
        final JRadioButton ironButton = new JRadioButton("Iron");
        final JRadioButton silverButton = new JRadioButton("Silver");
        final JRadioButton steelButton = new JRadioButton("Steel");
        final JRadioButton goldButton = new JRadioButton("Gold");
        final JRadioButton mithrilButton = new JRadioButton("Mithril");
        final JRadioButton adamantButton = new JRadioButton("Adamant");
        final JRadioButton runeButton = new JRadioButton("Rune");
        JButton startButton = new JButton("Start");

        JLabel label = new JLabel("What bars would you like to mine for?");

        mainFrame = new JFrame();
        mainFrame.setLayout(new GridLayout(3, 1));

        JPanel buttonFrame = new JPanel();
        buttonFrame.setLayout(new GridLayout(8, 1));
        buttonFrame.add(bronzeButton);
        buttonFrame.add(ironButton);
        buttonFrame.add(silverButton);
        buttonFrame.add(steelButton);
        buttonFrame.add(goldButton);
        buttonFrame.add(mithrilButton);
        buttonFrame.add(adamantButton);
        buttonFrame.add(runeButton);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bronzeButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.BRONZE;
                }
                if (ironButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.IRON;
                }
                if (silverButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.SILVER;
                }
                if (steelButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.STEEL;
                }
                if (goldButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.GOLD;
                }
                if (mithrilButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.MITHRIL;
                }
                if (adamantButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.ADAMANT;
                }
                if (runeButton.isSelected()) {
                    Runner.BAR_TO_SMELT = Bar.RUNE;
                }
            }
        });
        mainFrame.setSize(250, 250);
        mainFrame.add(label);
        mainFrame.add(buttonFrame);
        mainFrame.add(startButton);
    }

    public void setVisible(boolean show) {
        mainFrame.setVisible(show);
    }


}
