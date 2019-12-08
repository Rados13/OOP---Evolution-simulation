package GUI;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Locale;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Simulation");
        JPanel panel = new ButtonPanel();
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 100);
        setLocation(50, 50);


        pack();
        setVisible(true);

    }

}
