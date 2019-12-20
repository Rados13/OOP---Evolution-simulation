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
    private JButton highlightDominantGeneAnimalsButton;
    private JButton addNewMapButton;
    private ISimulationChangeListener listener;
    private JTextField textField;

    ButtonsSimulationPanel(ISimulationChangeListener listener) {

        this.listener = listener;

//        setLayout(new GridLayout(1, 5, 10, 0));
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        startButton = new JButton("<html>Start<html>");
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("<html>Stop<html>");
        stopButton.addActionListener(this);
        add(stopButton);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        turnButton = new JButton("<html>N turn<html>");
        turnButton.addActionListener(this);
        panel.add(turnButton);
        textField = new JTextField();
        panel.add(textField);
        add(panel);


        highlightDominantGeneAnimalsButton = new JButton("<html>Highlight dominant genotype animals<html>");
        highlightDominantGeneAnimalsButton.addActionListener(this);
        add(highlightDominantGeneAnimalsButton);

        animalsButton = new JButton("<html>List of animals<html>");
        animalsButton.addActionListener(this);
        add(animalsButton);


        addNewMapButton = new JButton("<html>Add second map<html>");
        addNewMapButton.addActionListener(this);
        add(addNewMapButton);

        changeParametersButton = new JButton("<html>Change parameters<html>");
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

        if (source == addNewMapButton) {
            listener.addNewMap();
        }


        if( source == highlightDominantGeneAnimalsButton){
            listener.highlight();
        }

    }

}
