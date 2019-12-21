package GUI;

import logic.Jungle;
import logic.ReadJson;
import logic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SimulationPanel extends JPanel {

    ArrayList<MapPanel> mapList = new ArrayList<MapPanel>();
    JPanel mapsPanel;
    private ButtonsSimulationPanel buttonsPanel;
    private IChangePanelListener mainListener;
    private ISimulationChangeListener simulationListener;

    class simulationListener implements ISimulationChangeListener {

        Timer timer;
        ArrayList<Thread> threads = new ArrayList<Thread>();
        int delay;

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
                for (Thread elem : threads) {
                    elem.run();
                }
                if (n > 0) this.n--;
                if (n == 0) {
                    timer.stop();
                }
            }
        }


        simulationListener(int delay) {
            threads.add(new Thread(() -> {
                World.makeTurn(mapList.get(0).getMap());
                mapList.get(0).refresh();
            }));
            this.delay = delay;
        }


        @Override
        public void setParameters() {
            if (timer != null) timer.stop();
            mainListener.setParameters();
        }

        @Override
        public void start() {
            if (timer == null) {
                timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                timer.addActionListener(new ActionListenerForNTurn(timer));
                timer.start();
            } else {
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


        private void refreshData() {
            for (MapPanel elem : mapList) {
                elem.refresh();
            }
        }


        @Override
        public void highlight() {
            for (MapPanel elem : mapList) {
                elem.mapDrawing.highlightAnimalsDominantGenotype();
                elem.refresh();
            }
        }

        @Override
        public void addNewMap() {
            addMapPanel();
            int n = threads.size();
            threads.add(new Thread(() -> {
                World.makeTurn(mapList.get(n).getMap());
                mapList.get(n).refresh();
            }));
        }
    }


    public SimulationPanel(IChangePanelListener listener) {

        this.mainListener = listener;
        this.simulationListener = new simulationListener(ReadJson.getDelay());

        setLayout(new BorderLayout());

        mapList.add(new MapPanel());

        mapsPanel = new JPanel();
        mapsPanel.setLayout(new GridLayout(1, mapList.size()));
        for (MapPanel elem : mapList) {
            mapsPanel.add(elem);
        }

        add(new JScrollPane(mapsPanel), BorderLayout.CENTER);

        buttonsPanel = new ButtonsSimulationPanel(simulationListener);
        add(buttonsPanel, BorderLayout.NORTH);


        setVisible(true);
    }

    void addMapPanel() {
        mapList.add(new MapPanel());
        mapsPanel.add(mapList.get(mapList.size() - 1));
    }
}
