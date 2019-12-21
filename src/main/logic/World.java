package logic;

import java.util.ArrayList;

public class World {


    public static Jungle getJungle() {
        try {
            ArrayList<Double> parameters = ReadJson.readFileWorld();
            if (parameters.size() < 7) {
                throw new ExceptionInInitializerError();
            }
            int startEnergy = parameters.get(2).intValue();
            Jungle map = new Jungle(parameters);
            return map;
        } catch (IllegalArgumentException | ExceptionInInitializerError ex) {
            System.out.println(ex);
            System.exit(1);
        }
        return null;
    }

    public static void makeTurn(Jungle map) {
        map.clearMapOfDeadths();
        map.run();
        map.eating();
        map.reproduction();
        map.generateGrassForOneDay();
        map.nextAge();
        map.updateStatistics();
    }

    static int getNumberOfAges(Jungle map){return map.getAge();}

    static double getAverageNumberOfAnimals(Jungle map){
        return map.getStatistics().getAveragAnimalsInAges();
    }

    static double getAverageNumberOfGrass(Jungle map){
        return map.getStatistics().getAverageGrassInAges();
    }

    static Genotype getMostDominantGenotype(Jungle map){
        return map.getStatistics().getMostDominantGenotypeInAges();
    }
    static double getAverageEnergy(Jungle map){
        return map.getStatistics().getAverageEnergyInAges();
    }
    static double getAverageLengthOfLife(Jungle map){
        return map.getStatistics().getAverageLengthOfLifeInAges();
    }
    static double getAverageNumberOfChildren(Jungle map){
        return map.getStatistics().getAverageNumberOfChildrenInAges();
    }



}
