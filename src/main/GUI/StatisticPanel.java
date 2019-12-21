package GUI;

import logic.Jungle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class StatisticPanel extends JPanel {
    private JLabel numberOfAge;
    private JLabel numberOfLivingAnimals;
    private JLabel numberOfGrass;
    private JLabel dominantGenotype;
    private JLabel averageEnergy;
    private JLabel averageLengthOfLife;
    private JLabel averageNumberOfChildren;

    StatisticPanel(Jungle map) {
        Border innerBorder = BorderFactory.createTitledBorder("Actual statistics");
        Border outerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setPreferredSize(new Dimension(200, 400));

        setLayout(new GridLayout(7, 1, 0, 0));
        numberOfAge = new JLabel("Number of age: " + Integer.toString(map.getAge()));
        add(numberOfAge);
        numberOfLivingAnimals = new JLabel("Number of animals: " + Integer.toString(map.getNumberOfAnimals()));
        add(numberOfLivingAnimals);
        numberOfGrass = new JLabel("Number of grass: " + Integer.toString(map.getNumberOfGrass()));
        add(numberOfGrass);
        if (map.getDominantGenotype() == null) {
            dominantGenotype = new JLabel("<html>Dominant genotyp: unknown<html>");
        } else {
            dominantGenotype = new JLabel("<html>Dominant genotyp: " + map.getDominantGenotype().genoType + "<html>");
        }
        add(dominantGenotype);
        averageEnergy = new JLabel("<html> Average energy: " + map.getAverageEnergy() + "<html>");
        add(averageEnergy);
        averageLengthOfLife = new JLabel("<html> Average length of life: unknown <html>");
        add(averageLengthOfLife);
        averageNumberOfChildren = new JLabel("<html>Average number of children: " + map.getAverageNumberOfChildren() + "<html>");
        add(averageNumberOfChildren);
    }

    void refresh(Jungle map) {
        numberOfAge.setText("<html>Number of age: " + Integer.toString(map.getAge()) + "<html>");
        numberOfLivingAnimals.setText("<html>Number of animals: " + Integer.toString(map.getNumberOfAnimals()) + "<html>");
        numberOfGrass.setText("<html>Number of grass: " + Integer.toString(map.getNumberOfGrass()) + "<html>");
        if (map.getDominantGenotype() == null) {
            dominantGenotype.setText("<html> Dominant genotyp: unknown<html>");
        } else {
            dominantGenotype.setText("<html> Dominant genotyp: " + map.getDominantGenotype().genoType + "<html>");
        }
        averageEnergy.setText("<html> Average energy: " +  (Math.round(map.getAverageEnergy() * 100d) / 100d) + "<html>");
        if (map.getAverageLengthOfLife() != 0) {
            averageLengthOfLife.setText("<html>Average length of life: " +
                    Math.round(map.getAverageLengthOfLife()*100)/100d + "<html>");
        }
        averageNumberOfChildren.setText("<html>Average number of children: " +
                Math.round(map.getAverageNumberOfChildren()*100)/100d + "<html>");

    }

}
