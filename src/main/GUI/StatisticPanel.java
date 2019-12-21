package GUI;

import logic.Jungle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class StatisticPanel extends JPanel {
    private JLabel numberOfAge;
    private JLabel numberOfLivingAnimals;
    private JLabel numberOfGrass;
    private JLabel dominantGenotype;
    private JLabel averageEnergy;
    private JLabel averageLengthOfLife;
    private JLabel averageNumberOfChildren;

    public StatisticPanel(Jungle map){
        Border innerBorder = BorderFactory.createTitledBorder("Actual statistics");
        Border outerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setPreferredSize(new Dimension(200,400));

        setLayout(new GridLayout(7,1,0,0));
        numberOfAge = new JLabel("Number of age: "+Integer.toString(map.getAge()));
        add(numberOfAge);
        numberOfLivingAnimals = new JLabel("Number of animals: "+Integer.toString(map.getNumberOfAnimals()));
        add(numberOfLivingAnimals);
        numberOfGrass = new JLabel("Number of grass: "+Integer.toString(map.getNumberOfGrass()));
        add(numberOfGrass);
        if(map.getDominantGenotype()==null){
            dominantGenotype = new JLabel("<html>Dominant genotyp: unknown<html>");
        }
        else {
            dominantGenotype = new JLabel("<html>Dominant genotyp: " + map.getDominantGenotype().genoType + "<html>");
        }
        add(dominantGenotype);
        averageEnergy = new JLabel(" Average energy: "+map.getAverageEnergy());
        add(averageEnergy);
        averageLengthOfLife = new JLabel("Average length of life: "+map.getAverageLengthOfLife());
        add(averageLengthOfLife);
        averageNumberOfChildren = new JLabel("Average number of children: "+map.getAverageNumberOfChildren());
        add(averageNumberOfChildren);
    }

    void refresh(Jungle map){
        numberOfAge.setText("<html>Number of age: "+Integer.toString(map.getAge())+"<html>");
        numberOfLivingAnimals.setText("<html>Number of animals: "+Integer.toString(map.getNumberOfAnimals())+"<html>");
        numberOfGrass.setText("<html>Number of grass: "+Integer.toString(map.getNumberOfGrass())+"<html>");
        if(map.getDominantGenotype()==null){
            dominantGenotype.setText("<html> Dominant genotyp: unknown<html>");
        }
        else{
            dominantGenotype.setText("<html> Dominant genotyp: "+map.getDominantGenotype().genoType+"<html>");
        }
        averageEnergy.setText("<html> Average energy: "+map.getAverageEnergy()+"<html>");
        averageLengthOfLife.setText("<html>Average length of life: "+map.getAverageLengthOfLife()+"<html>");
        averageNumberOfChildren.setText("<html>Average number of children: "+map.getAverageNumberOfChildren()+"<html>");

    }

}
