package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class MenuPanel extends JPanel implements ActionListener {

    private static int HEIGHT = 100;
    private static int WIDTH = 300;
    private JButton startButton;
    private JButton changeParametersButton;
    private IChangePanelListener listener;

    MenuPanel(IChangePanelListener listener) {
        this.listener=listener;
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);

        changeParametersButton = new JButton("Change parameters");
        changeParametersButton.addActionListener(this);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(startButton,BorderLayout.WEST);
        add(changeParametersButton,BorderLayout.EAST);
    }



    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == startButton) {
            listener.startSimulation();
        }


        if (source == changeParametersButton) {
            listener.setParameters();
        }
    }
}
