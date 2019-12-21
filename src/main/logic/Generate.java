package logic;

import java.util.ArrayList;

public class Generate {

    public static Vector2d generatePosition(AbstractWorldMap map) {
        int n = map.getUpperRight().x * map.getUpperRight().y / 2;
        while (n > 0) {
            int x, y;
            Vector2d vector;
            x = (int) (Math.random() * (map.getUpperRight().x));
            y = (int) (Math.random() * (map.getUpperRight().y));
            vector = new Vector2d(x, y);
            if (!map.isOccupied(vector)) {
                return vector;
            }
            n--;
        }
        return null;
    }

    public static Vector2d generateFreeSpace(Vector2d pos, AbstractWorldMap map) {
        ArrayList<Vector2d> positions = new ArrayList<Vector2d>();
        OptionsParser.getAllDirectionsVectors().forEach(direction -> positions.add(direction.toUnitVector().add(pos)));
        ArrayList<Vector2d> result = new ArrayList<>(positions);
        for (Vector2d elem : positions) {
            if (!map.canMoveTo(elem)) {
                result.remove(elem);
            }
        }
        if (result.size() == 0) {
            return generatePosition(map);
        } else {
            return result.get((int) (Math.random() * (result.size() - 1)));
        }
    }


    public static void generateGrassInsideJungle(Jungle map) {
        Vector2d vector;
        int x, y;

        int numberOfTry = (int) (map.getUpperRight().x * map.getUpperRight().y * Math.pow(map.getJungleRatio(), 2));

        while (numberOfTry > 0) {
            x = (int) (Math.random() * map.getJungleWidth());
            x += map.getJungleBeginX();
            y = (int) (Math.random() * map.getJungleHeight());
            y += map.getJungleBeginY();
            vector = new Vector2d(x, y);
            if (!map.isOccupied(vector)) {
                map.fields.put(vector, new Grass(vector));
                return;
            }
            numberOfTry--;
        }
    }

    public static void generateGrassSavanna(Jungle map) {
        Vector2d vector;
        int x, y, rand;
        int numberOfTry = (int) (map.getUpperRight().x * map.getUpperRight().y * Math.pow(1 - map.getJungleRatio(), 2));
        while (numberOfTry > 0) {
            rand = (int) Math.round(Math.random());
            if (rand == 1) {
                x = (int)Math.round(Math.random() * (map.getSavannaWidth()));
                if (x > map.getSavannaWidth() / 2) x = x - map.getSavannaWidth() / 2 - 1 + map.getSavannaSecondBeginX();
                y = (int) (Math.random() * map.getUpperRight().y);
            } else {
                y = (int) Math.round(Math.random() * (map.getSavannaHeight()));
                if (y > map.getSavannaHeight() / 2) y = y - map.getSavannaHeight() / 2 - 1 + map.getSavannaSecondBeginY();
                x = (int) (Math.random() * map.getUpperRight().x);
            }
            vector = new Vector2d(x, y);
            if (!map.isOccupied(vector)) {
                map.fields.put(vector, new Grass(vector));
                return;
            }
            numberOfTry--;
        }
    }
}
