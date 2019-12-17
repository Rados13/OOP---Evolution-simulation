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

    public static void makeTurn(Jungle map){
            map.clearMapOfDeadths();
            map.run();
            map.eating();
            map.reproduction();
            map.generateGrassForOneDay();
    }

}
