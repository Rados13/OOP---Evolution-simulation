package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MapObserver {

    private Jungle map;
    private long sumOfAnimalsInAges;
    private long sumOfGrassInAges;
    private Map<Genotype, Integer> genotypeToTimesOfDominance;
    private double sumOfAverageEnergy;
    private double sumOfAverageLengthOfLife;
    private double sumOfAverageNumberOfChildren;
    private int whenFirstDead;

    MapObserver(Jungle map) {
        this.map = map;
        sumOfAnimalsInAges = map.getNumberOfAnimals();
        sumOfGrassInAges = map.getNumberOfGrass();
        genotypeToTimesOfDominance = new HashMap<Genotype, Integer>();
        genotypeToTimesOfDominance.put(map.getDominantGenotype(), 1);
        sumOfAverageEnergy = map.getAverageEnergy();
        sumOfAverageLengthOfLife = map.getAverageLengthOfLife();
        sumOfAverageNumberOfChildren = map.getAverageNumberOfChildren();
        whenFirstDead = -1;
    }

    void update() {
        sumOfAnimalsInAges += map.getNumberOfAnimals();
        sumOfGrassInAges += map.getNumberOfGrass();
        Genotype dominant = map.getDominantGenotype();
        if (genotypeToTimesOfDominance.containsKey(dominant)) {
            genotypeToTimesOfDominance.replace(dominant, genotypeToTimesOfDominance.get(dominant) + 1);
        } else {
            genotypeToTimesOfDominance.put(dominant, 1);
        }
        sumOfAverageEnergy += map.getAverageEnergy();
        if (map.getAverageLengthOfLife() != 0 && whenFirstDead == -1) {
            whenFirstDead = map.getAge();
        }
        sumOfAverageLengthOfLife += map.getAverageLengthOfLife();
        sumOfAverageNumberOfChildren += map.getAverageNumberOfChildren();
    }

    double getAveragAnimalsInAges() {
        return sumOfAnimalsInAges * 1.0 / map.getAge();
    }

    double getAverageGrassInAges() {
        return sumOfGrassInAges * 1.0 / map.getAge();
    }

    Genotype getMostDominantGenotypeInAges() {
        Genotype dominant = null;
        int max = -1;
        for (Genotype elem : genotypeToTimesOfDominance.keySet()) {
            if (max < genotypeToTimesOfDominance.get(elem)) {
                dominant = elem;
                max = genotypeToTimesOfDominance.get(elem);
            }
        }
        return dominant;
    }

    double getAverageEnergyInAges() {
        return sumOfAverageEnergy * 1.0 / map.getAge();
    }

    double getAverageLengthOfLifeInAges() {
        if (whenFirstDead == -1) return 0;
        return sumOfAverageLengthOfLife * 1.0 / (map.getAge() - whenFirstDead);
    }

    double getAverageNumberOfChildrenInAges() {
        return sumOfAverageNumberOfChildren / map.getAge();
    }
}
