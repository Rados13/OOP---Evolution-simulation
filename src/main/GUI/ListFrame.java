package GUI;

import logic.Jungle;

import javax.swing.*;

public class ListFrame extends JFrame {

    AnimalListPanel animalList;
    JScrollPane scroll;

    ListFrame(Jungle map) {
        super("Animal list");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setLocation(50, 50);
        animalList = new AnimalListPanel(map);
        scroll = new JScrollPane(animalList);
        add(scroll);
        setVisible(true);

    }

    public void refresh(Jungle map) {
        animalList = new AnimalListPanel(map);
        scroll.add(animalList);
        animalList.repaint();
        setVisible(true);
    }
}
