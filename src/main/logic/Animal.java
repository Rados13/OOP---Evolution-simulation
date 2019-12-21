package logic;

import java.util.*;

public class Animal {
    private ArrayList<IAnimalStatusChangeObserver> observerList = new ArrayList<>();
    private MapDirection orientation = MapDirection.NORTH;
    double energy = 100;
    Genotype gen;
    private Vector2d position;
    AbstractWorldMap map;
    private int id;
    private int numberOfChildren=0;
    private int birthAge;


    Animal(AbstractWorldMap map, double startEnergy) {
        this.position = Generate.generatePosition(map);
        this.map = map;
        this.energy = startEnergy;
        this.orientation = OptionsParser.parse((int) Math.round(Math.random() * 7));
        this.id = map.getNextId();
        this.gen = new Genotype();
        this.birthAge = map.getAge();

        this.map.place(this);

    }

    Animal(AbstractWorldMap map, double startEnergy, int x, int y, Genotype genoType) {
        this.position = new Vector2d(x, y);
        this.map = map;
        this.energy = startEnergy;
        this.id = map.getNextId();
        this.gen = genoType;
        this.birthAge = map.getAge();
        this.map.place(this);
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

    void move() {
        int howManyRotates = gen.getMoveFromGene();
        MapDirection changingOrientation = getOrientation();
        for (int i = 0; i < howManyRotates; i++) {
            changingOrientation = changingOrientation.next();
        }
        Vector2d addedVector = this.position.add(changingOrientation.toUnitVector());
        double futureEnergy = energy - map.getMoveEnergy();
        for (IAnimalStatusChangeObserver elem : observerList) {
            elem.positionChange(this, map.futurePosition(addedVector),
                    changingOrientation, futureEnergy);
        }
    }

    void energyChange(double newEnergy) {
        for(IAnimalStatusChangeObserver elem : observerList){
            elem.energyChange(this,newEnergy);
        }
    }

    boolean isDead(){
        if(this.energy<=0){
            for(IAnimalStatusChangeObserver elem : observerList){
                elem.deadth(this);
            }
            return true;
        }
        return false;
    }

    void addObserver(IAnimalStatusChangeObserver observer) {
        observerList.add(observer);
        observer.addElement(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Animal)) return false;
        Animal that = (Animal) other;
        return this.id == that.id ;
    }

    public int getId() {
        return this.id;
    }

    public double getEnergy() {
        return this.energy;
    }

    public void setEnergy(double energy){this.energy = energy;}

    public Genotype getGen() {
        return this.gen;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public int getNumberOfChildren (){ return this.numberOfChildren; }

    void newChildren (){this.numberOfChildren++;}

    int getBirthAge (){return this.birthAge;}




}
