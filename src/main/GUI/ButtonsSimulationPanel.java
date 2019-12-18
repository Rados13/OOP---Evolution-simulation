package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsSimulationPanel extends JPanel implements ActionListener {

    private JButton turnButton;
    private JButton stopButton;
    private JButton startButton;
    private JButton animalsButton;
    private JButton changeParametersButton;
    private ISimulationChangeListener listener;
    private JTextField textField;

    ButtonsSimulationPanel(ISimulationChangeListener listener) {

        this.listener = listener;




//        setLayout(new GridLayout(1, 5, 10, 0));
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        add(stopButton);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        turnButton = new JButton("N turn");
        turnButton.addActionListener(this);
        turnButton.setPreferredSize(new Dimension(25,25));
        panel.add(turnButton);
        textField = new JTextField();
        panel.add(textField);
        add(panel);


        animalsButton = new JButton("List of animals");
        animalsButton.addActionListener(this);
        add(animalsButton);

        changeParametersButton = new JButton("Change parameters");
        changeParametersButton.addActionListener(this);
        add(changeParametersButton);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();


        if (source == startButton) {
            listener.start();
        }

        if (source == stopButton) {
            listener.stop();
        }


        if (source == turnButton) {
            if (textField.getText().equals(""))
                throw new IllegalArgumentException(textField.getText() + " is not number");
            listener.makeNTurn(Integer.parseInt(textField.getText()));
        }

        if (source == changeParametersButton) {
            listener.setParameters();
        }


        if (source == animalsButton) {
            listener.viewAnimalsList();
        }

    }

}
