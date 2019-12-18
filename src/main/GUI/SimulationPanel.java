package GUI;

import logic.Jungle;
import logic.ReadJson;
import logic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimulationPanel extends JPanel {

    private MapPanel mapDrawing;
    private StatisticPanel statistics;
    private ButtonsSimulationPanel buttonsPanel;
    private Jungle map;
    private IChangePanelListener mainListener;
    private ISimulationChangeListener simulationListener;

    class simulationListener implements ISimulationChangeListener {

        Timer timer;

        class ActionListenerForNTurn implements ActionListener {
            int n;
            Timer timer;

            ActionListenerForNTurn(Timer timer) {
                this.n = -1;
                this.timer = timer;
            }

            ActionListenerForNTurn(int n, Timer timer) {
                this.n = n;
                this.timer = timer;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                World.makeTurn(map);
                refreshData();
                if (n > 0) this.n--;
                if (n == 0) {
                    timer.stop();
                }

            }
        }

        ListFrame animalFrame;

        @Override
        public void setParameters() {
            if (timer != null) timer.stop();
            mainListener.setParameters();
        }

        @Override
        public void start() {
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            timer.addActionListener(new ActionListenerForNTurn(timer));
            timer.start();

        }

        @Override
        public void stop() {
            if (timer != null) timer.stop();
        }

        @Override
        public void makeNTurn(int n) {

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            timer.addActionListener(new ActionListenerForNTurn(n, timer));
            timer.start();
        }

        @Override
        public void viewAnimalsList() {
            animalFrame = new ListFrame(map);
        }


        private void refreshData() {
            mapDrawing.repaint();
            if (animalFrame != null) animalFrame.refresh(map);
            statistics.refresh(map);
        }

    }


    public SimulationPanel(IChangePanelListener listener) {

        setLayout(new BorderLayout());

        map = World.getJungle();

        mapDrawing = new MapPanel(map, ReadJson.getScale());

        add(new JScrollPane(mapDrawing), BorderLayout.CENTER);

        this.mainListener = listener;
        this.simulationListener = new simulationListener();
        buttonsPanel = new ButtonsSimulationPanel(simulationListener);
        add(buttonsPanel, BorderLayout.NORTH);
        statistics = new StatisticPanel(map);
        add(statistics, BorderLayout.EAST);

        setVisible(true);
    }
}
