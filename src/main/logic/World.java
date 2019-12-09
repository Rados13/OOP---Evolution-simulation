package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {

    public static Jungle getJungle(){
        try {

            ArrayList<Double> parameters = ReadJson.readFileWorld();
            if(parameters.size()<6){
                throw new ExceptionInInitializerError();
            }
            int startEnergy = parameters.get(2).intValue();
            Jungle map = new Jungle(parameters);
            for(int i=0;i<parameters.get(parameters.size()-1).intValue();i++) {
                System.out.println(ReadJson.getGene());
                new Animal(map, startEnergy, new Gene(ReadJson.getGene()));
            }
            return map;
        }catch (IllegalArgumentException | ExceptionInInitializerError ex) {
            System.out.println(ex);
            System.exit(1);
        }
        return null;
    }

    public static void moveAllAnimals(Jungle map){
        map.run();
    }

    public static void eat(Jungle map){
        map.eating();
    }

    public static void clearFromDeadth(Jungle map){
        map.clearMapOfDeadths();
    }

    public static void reproduction(Jungle map){
        map.reproduction();
    }

    public static void generateGrass(Jungle map){
        map.generateGrassForOneDay();
    }

    public static void makeNTurns(Jungle map,int n){
        for(int i=1;i<n;i++){
            map.clearMapOfDeadths();
            map.run();
            map.eating();
            map.reproduction();
            map.generateGrassForOneDay();
        }
    }



    public static void main(String[] args) {

        try {
            ArrayList<Double> parameters = ReadJson.readFileWorld();
            System.out.println(parameters);
            int startEnergy = parameters.get(2).intValue();
            Jungle map = new Jungle(parameters);
            new Animal(map,startEnergy,new Gene());
            new Animal(map,startEnergy,new Gene());
            System.out.println(map);
            for(int i=0; i <20;i++) {
//                System.out.println(map.status.vectorToAnimals.size());
                map.run();
                map.eating();
                map.reproduction();
            }
            System.out.println(map);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

}
