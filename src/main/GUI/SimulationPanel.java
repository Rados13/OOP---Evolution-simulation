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
    private ButtonsSimulationPanel buttonsPanel;
    private Jungle map;
    private IChangePanelListener mainListener;
    private ISimulationChangeListener simulationListener;

    class simulationListener implements ISimulationChangeListener {

        ListFrame animalFrame;

        public void goBackToMenu() {
            mainListener.goBackToMenu();
            refreshData();
        }

        @Override
        public void makeMove() {
            World.moveAllAnimals(map);
            refreshData();
        }

        @Override
        public void makeNTurn(int n) {
            ActionListener listener = new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    World.makeTurn(map);
                    refreshData();
                }
            };
            Timer timer = new Timer(1000,listener);
            timer.setRepeats(true);

            ActionListener listenerEnd = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timer.stop();
                }
            };
            Timer timerEnd = new Timer(1000*(n+1),listenerEnd);
            timer.start();
            timerEnd.setRepeats(false);
            timerEnd.start();
        }

        @Override
        public void eat() {
            World.eat(map);
            refreshData();
        }

        @Override
        public void clearDead() {
            World.clearFromDeadth(map);
            refreshData();
        }

        @Override
        public void reproduction() {
            World.reproduction(map);
            refreshData();
        }

        @Override
        public void generateGrass() {
            World.generateGrass(map);
            refreshData();
        }

        @Override
        public void viewAnimalsList() {
            animalFrame = new ListFrame(map);
        }


        private void refreshData() {
            mapDrawing.repaint();
            if (animalFrame != null) animalFrame.refresh(map);
        }

    }


    public SimulationPanel(IChangePanelListener listener) {

        setLayout(new GridBagLayout());

        map = World.getJungle();

        mapDrawing = new MapPanel(map, ReadJson.getScale());

        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 100;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        JScrollPane scroll = new JScrollPane(mapDrawing);

        add(scroll, gc);

        this.mainListener = listener;
        this.simulationListener = new simulationListener();
        buttonsPanel = new ButtonsSimulationPanel(simulationListener);
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        add(buttonsPanel, gc);


//        setPreferredSize(new Dimension(600, 600));
        setVisible(true);
    }
}
