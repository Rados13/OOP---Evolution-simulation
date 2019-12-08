package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class ButtonPanel extends JPanel implements ActionListener {

    public static int HEIGHT = 100;
    public static int WIDTH = 300;
    private JButton startButton;
    private JButton changeParametersButton;


    ButtonPanel(){
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);

        changeParametersButton = new JButton("Change parameters");
        changeParametersButton.addActionListener(this);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(startButton);
        add(changeParametersButton);
    }


    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if(source==startButton){

        }


        if(source==changeParametersButton){

        }
    }
}
