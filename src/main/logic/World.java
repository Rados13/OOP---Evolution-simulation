package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {

    public static void main(String[] args) {

        try {
            ArrayList<Double> parameters = ReadJson.readFile();
            System.out.println(parameters);
            int startEnergy = parameters.get(2).intValue();
            Jungle map = new Jungle(parameters);
            new Animal(map,startEnergy);
            new Animal(map,startEnergy);
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
