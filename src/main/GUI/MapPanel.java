package GUI;

import logic.StatsWriter;
import logic.Jungle;
import logic.ReadJson;
import logic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel implements ActionListener {

    DrawMapPanel mapDrawing;
    private Jungle map;
    private StatisticPanel statistics;
    private MarkedAnimalStats markedPanel;
    private JButton animalsButton;
    private JButton saveButton;
    private ListFrame listFrame = null;


    MapPanel() {
        map = World.getJungle();

        setLayout(new BorderLayout());


        mapDrawing = new DrawMapPanel(map, ReadJson.getScale(), this);

        add(new JScrollPane(mapDrawing), BorderLayout.CENTER);

        GridBagLayout layout = new GridBagLayout();
        JPanel panelEast = new JPanel(layout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 4;
        gc.gridwidth = 1;
        gc.ipady = 1;

        statistics = new StatisticPanel(map);
        panelEast.add(statistics, gc);

        gc.gridy += 4;
        markedPanel = new MarkedAnimalStats(map);
        panelEast.add(markedPanel, gc);

        gc.gridy += 4;
        gc.gridheight = 1;
        animalsButton = new JButton("<html>List of animals<html>");
        animalsButton.addActionListener(this);
        panelEast.add(animalsButton, gc);

        gc.gridy++;
        saveButton = new JButton("<html> Save statistics<html>");
        saveButton.addActionListener(this);
        panelEast.add(saveButton,gc);
        add(panelEast,BorderLayout.LINE_END);
    }

    Jungle getMap() {
        return map;
    }

    void refresh() {
        mapDrawing.repaint();
        if (listFrame != null && listFrame.isVisible()) listFrame.refresh(map);
        statistics.refresh(map);
        markedPanel.refresh();
    }

    void refreshMarkedOne() {
        map.setMarkedOne(mapDrawing.getMarkedOne());
        mapDrawing.repaint();
        markedPanel.changeMarkedAnimal(mapDrawing.getMarkedOne());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == animalsButton) {
            viewAnimalsList();
        }
        if(source == saveButton){
            saveData();
        }
    }

    private void viewAnimalsList() {
        if (listFrame == null || !listFrame.isVisible()) {
            listFrame = new ListFrame(map);
        }
    }
    private void saveData(){
        StatsWriter.saveStats(map);
    }
}

