package logic;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap {
    LinkedList<Animal> animals = new LinkedList<Animal>();
    AnimalsStatus animalsStatus = new AnimalsStatus();
    GenotypeStatus genesStatus = new GenotypeStatus();
    AncestorStatus ancestorStatus = new AncestorStatus();
    int numberOfAnimals = 0;
    double moveEnergy;
    int numberOfAge = 0;

    public String toString() {
        return new MapVisualizer(this).draw(getLowerLeft(), getUpperRight());
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean place(Animal anim) {
        if (!canMoveTo(anim.getPosition())) {
            throw new IllegalArgumentException(anim.getPosition() + " is ocuppied position");
        }
        animals.add(anim);
        anim.addObserver(animalsStatus);
        anim.addObserver(genesStatus);
        return true;
    }

    public void run() {
        for (Animal anim : animals) {
            anim.move();
        }
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return animalsStatus.objectAt(position);
    }

    abstract Vector2d getLowerLeft();

    abstract Vector2d getUpperRight();

    double getMoveEnergy() {
        return moveEnergy;
    }

    int getNextId() {
        return numberOfAnimals++;
    }

    void nextAge() {
        numberOfAge++;
    }

    public int getAge() {
        return numberOfAge;
    }

    public Genotype getDominantGenotype() {
        return genesStatus.getDominantGenotype();
    }

    public ArrayList<Animal> getAnimalsWithDominantGenotype() {
        return genesStatus.getAnimalsWithDominantGenotype();
    }

    public Double getAverageEnergy() {
        return animalsStatus.getAverageEnergy();
    }

    public Double getAverageNumberOfChildren() {
        return animalsStatus.getAverageChildren();
    }

    public Double getAverageLengthOfLife() {
        return animalsStatus.getAverageLengthOfLife();
    }

    public Animal getAnimalWithHighestEnergy(Vector2d vec) {
        List<Animal> anim = animalsStatus.getAnimalWithHighestEnergy(vec);
        if (anim != null) return animalsStatus.getAnimalWithHighestEnergy(vec).get(0);
        return null;
    }

    public void setMarkedOne(Animal markedOne) {
        ancestorStatus.setMarkedOne(markedOne);
    }

    public int getNumberOfHeirsOfMarkedOne() {
        return ancestorStatus.getNumberOfHeirs();
    }

    public int getNumberOfAnimals() {
        if (animals == null) return 0;
        return animals.size();
    }

    public int getDeadAgeOfMarkedOne() {
        return ancestorStatus.getDeadAge();
    }
}
