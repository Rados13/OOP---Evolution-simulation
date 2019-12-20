package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametersPanel extends JPanel implements ActionListener {


    private IChangePanelListener listener;
    private JButton simulationButton;
    private FormPanel form;


    public ParametersPanel(IChangePanelListener listener) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        this.listener = listener;

        simulationButton = new JButton("Go to simulation");
        simulationButton.addActionListener(this);

        form = new FormPanel(simulationButton);

        add(form);

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == simulationButton) {
            listener.startSimulation();
        }
    }
}
