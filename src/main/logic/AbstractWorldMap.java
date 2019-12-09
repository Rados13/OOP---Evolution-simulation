package logic;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap {
    LinkedList<Animal> animals = new LinkedList<Animal>();
    AnimalsStatus status = new AnimalsStatus();
    int numberOfAnimals=0;
    int moveEnergy = 50;


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
        status.addElement(anim);
        return true;
    }

    public void run() {
        for (Animal anim : animals ) {
            anim.move();
        }
    }


    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return status.objectAt(position);
    }

    abstract void positionChanged(Animal anim,Vector2d newPosition,MapDirection newOrientation);
    abstract Vector2d getLowerLeft();
    abstract Vector2d getUpperRight();

    public int getNextId(){
        return numberOfAnimals++;
    }
}
