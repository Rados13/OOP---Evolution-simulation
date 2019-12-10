package logic;

import java.util.*;

public class Animal {
    ArrayList<IAnimalStatusChangeObserver> observerList = new ArrayList<>();
    private MapDirection orientation = MapDirection.NORTH;
    double energy = 100;
    Gene gen;
    private Vector2d position;
    AbstractWorldMap map;
    private int id;


    Animal(AbstractWorldMap map, double startEnergy) {
        this.position = new Vector2d().generatePosition(0, 0, map);
        this.map = map;
        this.energy = startEnergy;
        this.map.place(this);
        this.orientation = OptionsParser.parse((int) Math.round(Math.random() * 7));
        this.id = map.getNextId();
        this.gen = new Gene();
    }

    Animal(AbstractWorldMap map, double startEnergy, int x, int y, Gene genoType) {
        this.position = new Vector2d(x, y);
        this.map = map;
        this.energy = startEnergy;
        this.id = map.getNextId();
        this.map.place(this);
        this.gen = genoType;
    }

    public String toString() {
        switch (this.orientation) {
            case NORTH:
                return "N";
            case NORTHEAST:
                return "NE";
            case EAST:
                return "E";
            case SOUTHEAST:
                return "SE";
            case SOUTH:
                return "S";
            case SOUTHWEST:
                return "SW";
            case WEST:
                return "W";
            case NORTHWEST:
                return "NW";
        }
        return "";
    }

    public void move() {
        int howManyRotates = gen.getMoveFromGene();
        MapDirection changingOrientation = getOrientation();
        for (int i = 0; i < howManyRotates; i++) {
            changingOrientation = changingOrientation.next();
        }
        Vector2d addedVector = this.position.add(changingOrientation.toUnitVector());
        map.positionChanged(this, futurePosition(addedVector), changingOrientation);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Animal)) return false;
        Animal that = (Animal) other;
        return this.id == that.id && this.position == that.position && this.energy == that.energy
                && this.gen.equals(that.gen);
    }

    public int getId() {
        return this.id;
    }

    public double getEnergy() {
        return this.energy;
    }

    public Gene getGen() {
        return this.gen;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    private Vector2d futurePosition(Vector2d addedVector) {
        int x = addedVector.x;
        int y = addedVector.y;
        if (x < 0) {
            x = map.getUpperRight().x;
        }
        if (x >= map.getUpperRight().x) {
            x = 0;
        }
        if (y < 0) {
            y = map.getUpperRight().y;
        }

        if (y >= map.getUpperRight().y) {
            y = 0;
        }
        return new Vector2d(x, y);
    }


}
