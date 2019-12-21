package GUI;

import logic.Jungle;
import logic.ReadJson;
import logic.World;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    DrawMapPanel mapDrawing;
    private Jungle map;
    StatisticPanel statistics;
    MarkedAnimalStats markedPanel;
    ListFrame listFrame ;


    MapPanel() {
        map = World.getJungle();

        setLayout(new FlowLayout());
        mapDrawing = new DrawMapPanel(map, ReadJson.getScale(), this);

        add(new JScrollPane(mapDrawing));

        JPanel panelEast = new JPanel();
        panelEast.setLayout(new BorderLayout());
        statistics = new StatisticPanel(map);
        panelEast.add(statistics, BorderLayout.NORTH);
        markedPanel = new MarkedAnimalStats(map);
        panelEast.add(markedPanel, BorderLayout.SOUTH);
        add(panelEast);

    }

    Jungle getMap() {
        return map;
    }

    void refresh() {
        mapDrawing.repaint();
        if (listFrame != null) listFrame.refresh(map);
        statistics.refresh(map);
        markedPanel.refresh();
    }

    public void refreshMarkedOne() {
            map.setMarkedOne(mapDrawing.getMarkedOne());
            mapDrawing.repaint();
            markedPanel.changeMarkedAnimal(mapDrawing.getMarkedOne());
    }
}

