package RunecraftingRunner.Task;

import RunecraftingRunner.RunecraftingRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    JFrame jFrame;
    JTextField username, numberOfEss;
    JButton startButton;

    public void init() {
        jFrame = new JFrame();
        username = new JTextField("Username");
        numberOfEss = new JTextField("Number of Ess");
        startButton = new JButton("Start");
        jFrame.setLayout(new GridLayout(4, 1));
        jFrame.add(username);
        jFrame.add(numberOfEss);
        jFrame.add(startButton);
        jFrame.setSize(250, 250);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RunecraftingRunner.playerName = username.getText();
                RunecraftingRunner.amountOfRuneEss = Integer.parseInt(numberOfEss.getText());
            }
        });
        jFrame.setVisible(true);
    }




}
