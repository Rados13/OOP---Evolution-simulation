package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsSimulationPanel extends JPanel implements ActionListener {

    private JButton backButton;
    private JButton turnButton;
    private JButton eatButton;
    private JButton moveButton;
    private JButton deadthButton;
    private JButton animalsButton;
    private JButton reproductionButton;
    private JButton generateGrassButton;
    private ISimulationChangeListener listener;
    private JTextField textField;

    ButtonsSimulationPanel(ISimulationChangeListener listener) {

        this.listener = listener;

        setLayout(new GridLayout(9, 1, 0, 10));


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        turnButton = new JButton("N turn");
        turnButton.addActionListener(this);
        panel.add(turnButton);
        textField = new JTextField();
        panel.add(textField);
        add(panel);

        moveButton = new JButton("Moving");
        moveButton.addActionListener(this);
        add(moveButton);

        eatButton = new JButton("Eating");
        eatButton.addActionListener(this);
        add(eatButton);

        deadthButton = new JButton("Clear from dead animals");
        deadthButton.addActionListener(this);
        add(deadthButton);

        reproductionButton = new JButton("Reproduction");
        reproductionButton.addActionListener(this);
        add(reproductionButton);


        generateGrassButton = new JButton("Generate Grass");
        generateGrassButton.addActionListener(this);
        add(generateGrassButton);


        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);


        animalsButton = new JButton("List of animals");
        animalsButton.addActionListener(this);
        add(animalsButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == backButton) {
            listener.goBackToMenu();
        }

        if (source == moveButton) {
            listener.makeMove();
        }

        if (source == eatButton) {
            listener.eat();
        }

        if (source == turnButton) {
            if (textField.getText().equals(""))
                throw new IllegalArgumentException(textField.getText() + " is not number");
            listener.makeNMoves(Integer.parseInt(textField.getText()));
        }

        if (source == deadthButton) {
            listener.clearDead();
        }

        if (source == reproductionButton) {
            listener.reproduction();
        }


        if (source == generateGrassButton) {
            listener.generateGrass();
        }

        if (source == animalsButton) {
            listener.viewAnimalsList();
        }
    }

}
