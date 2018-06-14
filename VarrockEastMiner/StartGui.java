package VarrockEastMiner;

import VarrockEastMiner.Tasks.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGui extends JFrame {

    JFrame mainFrame;

    public StartGui() {
        final JRadioButton bronzeButton = new JRadioButton("Bronze");
        JRadioButton ironButton = new JRadioButton("Iron");
        JButton startButton = new JButton("Start");

        JLabel label = new JLabel("What bars would you like to mine for?");

        mainFrame = new JFrame();
        mainFrame.setLayout(new GridLayout(3, 1));

        JPanel buttonFrame = new JPanel();
        buttonFrame.setLayout(new GridLayout(2, 2));
        buttonFrame.add(bronzeButton);
        buttonFrame.add(ironButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bronzeButton.isSelected()) {
                    Runner.BARS_TO_MINE = Bar.BRONZE;
                } else {
                    Runner.BARS_TO_MINE = Bar.IRON;
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
