package GUI;

import logic.Animal;
import logic.Jungle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AnimalListPanel extends JPanel {

    private JLabel textLabel;

    AnimalListPanel(Jungle map){

        Border innerBorder = BorderFactory.createTitledBorder("List of existing animals");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=1;
        gc.gridheight=1;
        gc.ipadx=10;
        gc.ipady=5;
        for(Animal elem: map.getAnimals()){
            textLabel = new JLabel(Integer.toString(elem.getId()));
            add(textLabel,gc);
            gc.gridx++;
            textLabel = new JLabel(elem.getPosition().toString());
            add(textLabel,gc);
            gc.gridx++;
            textLabel = new JLabel(String.valueOf(elem.getOrientation()));
            add(textLabel,gc);
            gc.gridx++;
            textLabel= new JLabel(Double.toString(elem.getEnergy()));
            add(textLabel,gc);
            gc.gridx++;
            textLabel = new JLabel("<html>"+elem.getGen().genoType.toString()+"<html>");
            add(textLabel,gc);
            gc.gridx=0;
            gc.gridy+=2;
        }
    }

}
