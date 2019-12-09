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
            btnPanel.setVisible(false);
            frame.remove(btnPanel);

        }

        @Override
        public void goBackToMenu() {
            frame.btnPanel = new MenuPanel(this);
            frame.add(btnPanel);
            if (parametersPanel != null) {
                parametersPanel.setVisible(false);
                frame.remove(parametersPanel);
            }
            if (simulationPanel != null) {
                simulationPanel.setVisible(false);
                frame.remove(simulationPanel);
            }
        }

        @Override
        public void setParameters() {
            frame.parametersPanel = new ParametersPanel(this);
            frame.add(parametersPanel);
            btnPanel.setVisible(false);
            frame.remove(btnPanel);
        }

    }


    ParametersPanel parametersPanel;
    SimulationPanel simulationPanel;
    MenuPanel btnPanel;
    mainListener listener;


    MainFrame() {
        super("Simulation");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocation(50, 50);
        listener = new mainListener(this);
        btnPanel = new MenuPanel(listener);

        add(btnPanel, BorderLayout.CENTER);
        setVisible(true);
//        add(simulationPanel,BorderLayout.CENTER);
//        simulationPanel.setVisible(false);
//        add(parametersPanel,BorderLayout.CENTER);
//        parametersPanel.setVisible(false);

//        pack();

    }
}
