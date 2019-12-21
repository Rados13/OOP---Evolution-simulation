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

    void refresh(Jungle map) {
        scroll.setVisible(false);
        remove(scroll);
        animalList = new AnimalListPanel(map);
        scroll = new JScrollPane(animalList);
        add(scroll);
        setVisible(true);
    }
}
