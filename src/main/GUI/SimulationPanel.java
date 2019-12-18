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
    private MarkedAnimalStats markedPanel;

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
            if (timer == null) {
                timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });

                timer.addActionListener(new ActionListenerForNTurn(timer));
                timer.start();
            }else{
                timer.start();
            }
        }


        @Override
        public void stop() {
            if (timer != null) timer.stop();
            refreshData();
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
            markedPanel.refresh();
        }

        public void refreshMarkedOne() {
            map.setMarkedOne(mapDrawing.getMarkedOne());
            mapDrawing.repaint();
            markedPanel.changeMarkedAnimal(mapDrawing.getMarkedOne());
        }

        @Override
        public void highlight() {
            mapDrawing.highlightAnimalsDominantGenotype();
            refreshData();
        }

        @Override
        public void addNewMap() {
            
        }

    }


    public SimulationPanel(IChangePanelListener listener) {

        this.mainListener = listener;
        this.simulationListener = new simulationListener();

        setLayout(new BorderLayout());

        map = World.getJungle();

        mapDrawing = new MapPanel(map, ReadJson.getScale(), simulationListener);

        add(new JScrollPane(mapDrawing), BorderLayout.CENTER);

        buttonsPanel = new ButtonsSimulationPanel(simulationListener);
        add(buttonsPanel, BorderLayout.NORTH);

        JPanel panelEast = new JPanel();
        panelEast.setLayout(new BorderLayout());
        statistics = new StatisticPanel(map);
        panelEast.add(statistics, BorderLayout.CENTER);
        markedPanel = new MarkedAnimalStats(map);
        panelEast.add(markedPanel, BorderLayout.SOUTH);
        add(panelEast, BorderLayout.EAST);

        setVisible(true);
    }
}
