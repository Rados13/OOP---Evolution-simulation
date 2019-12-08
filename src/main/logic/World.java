package logic;

import java.util.ArrayList;
import java.util.Arrays;

public class World {

    public static void main(String[] args) {

        try {
            Jungle map = new Jungle(5,5,5);
            new Animal(map,100);
            new Animal(map, 100);
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
