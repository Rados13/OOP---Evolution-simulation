package GUI;

import logic.Animal;
import logic.Jungle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MarkedAnimalStats extends JPanel {

    private Animal markedOne;
    private Jungle map;
    private JLabel genoTypeLabel;
    private JLabel numberOfChildrenLabel;
    private JLabel numberOfHeirsLabel;
    private JLabel ageOfDeadthLabel;
    private int numberOfChildrenSinceObserv;

    MarkedAnimalStats(Jungle map) {
        this.map=map;
        Border innerBorder = BorderFactory.createTitledBorder("Marked statistics");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setPreferredSize(new Dimension(200, 200));
        setLayout(new GridLayout(4,1,0,5));


        genoTypeLabel = new JLabel(" Genotype : ");
        add(genoTypeLabel);
        numberOfChildrenLabel = new JLabel(" Number of children: 0");
        add(numberOfChildrenLabel);
        numberOfHeirsLabel = new JLabel("Number of heirs: 0");
        add(numberOfHeirsLabel);
        ageOfDeadthLabel = new JLabel("Age of deadth: unknown");
        add(ageOfDeadthLabel);
    }

    void refresh() {
        if (markedOne != null) {
            genoTypeLabel.setText("<html> Genotyp: "+markedOne.getGen().genoType+"<html>");
            numberOfChildrenLabel.setText("<html> Number of children: "+
                    Integer.toString(markedOne.getNumberOfChildren()-numberOfChildrenSinceObserv)+"<html>");
            numberOfHeirsLabel.setText("<html> Number of heirs: "+map.getNumberOfHeirsOfMarkedOne()+"<html>");
            if(map.getDeadAgeOfMarkedOne()>0)ageOfDeadthLabel.setText("<html>Age of deadth: "+map.getDeadAgeOfMarkedOne()+"<html>");
            else ageOfDeadthLabel.setText("<html>Age of deadth: unknown<html>");
        }
        else{
            genoTypeLabel.setText("<html> Dominant genotyp: <html>");
            numberOfChildrenLabel.setText("<html> Number of children: <html>");
            numberOfHeirsLabel.setText("<html> Number of heirs: unknown<html>");
        }

    }

    void changeMarkedAnimal (Animal markedOne){
        this.markedOne = markedOne;
        if(markedOne!=null)this.numberOfChildrenSinceObserv = markedOne.getNumberOfChildren();
        refresh();
    }

}
