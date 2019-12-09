package GUI;

import logic.Jungle;

import javax.swing.*;

public class ListFrame extends JFrame {

    AnimalListPanel animalList;

    ListFrame(Jungle map){
        super("Animal list");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setLocation(50, 50);


        animalList = new AnimalListPanel(map);
        add(animalList);
        pack();
        setVisible(true);
    }

    public void refresh(Jungle map){
        animalList.setVisible(false);
        remove(animalList);
        animalList = new AnimalListPanel(map);
        add(animalList);
    }
}
