package GUI;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Locale;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Simulation");
        JPanel panel = new MyPanel();

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setSize(300, 100);
        setLocation(50, 50);

        add(new JButton("Przycisk 1"));
        add(new JButton("Przycisk 2"));
        add(new JButton("Przycisk 3"));
        pack();
        setVisible(true);

    }

}
