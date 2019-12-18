package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    class mainListener implements IChangePanelListener {
        private MainFrame frame;

        mainListener(MainFrame frame) {
            this.frame = frame;
        }

        @Override
        public void startSimulation() {
            frame.simulationPanel = new SimulationPanel(this);
            frame.add(simulationPanel);
            parametersPanel.setVisible(false);
            frame.remove(parametersPanel);

        }

        @Override
        public void setParameters() {
            frame.parametersPanel = new ParametersPanel(this);
            frame.add(parametersPanel);
            simulationPanel.setVisible(false);
            frame.remove(simulationPanel);
        }

    }


    private ParametersPanel parametersPanel;
    private SimulationPanel simulationPanel;
    private mainListener listener;


    MainFrame() {
        super("Simulation");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(50, 50);
        listener = new mainListener(this);

        parametersPanel = new ParametersPanel(listener);
        add(parametersPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
