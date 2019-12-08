package logic;

import javafx.collections.transformation.SortedList;
import jdk.nashorn.api.tree.Tree;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


public class AnimalsStatus implements IAnimalStatusChangeObserver {

    class CompareByEnergy implements Comparator {

        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Animal) || !(o2 instanceof Animal)) {
                throw new IllegalArgumentException("Trying compare animal not belong to map ");
            }
            Animal animalFirst = (Animal) o1;
            Animal animalSecond = (Animal) o2;
            int diffrence = animalFirst.energy - animalSecond.energy;
            if (diffrence != 0) {
                return diffrence;
            }
            return animalFirst.id - animalSecond.id;
        }
    }

    Map<Vector2d, ArrayList<Animal>> vectorToAnimals = new LinkedHashMap<>();

    public void positionChanged(Animal prevState, Vector2d newPosition, MapDirection newOrientation,int newEnergy) {
        removeElement(prevState);
        prevState.setPosition(newPosition);
        prevState.setOrientation(newOrientation);
        prevState.energy=newEnergy;
        addElement(prevState);
    }

    public void energyChanged(Animal prevState, int newEnergy){
        removeElement(prevState);
        prevState.energy=newEnergy;
        addElement(prevState);
    }

    void addElement(Animal elem) {
        ArrayList<Animal> array = vectorToAnimals.get(elem.getPosition());
        if (array == null) {
            array = new ArrayList<Animal>();
            array.add(elem);
            vectorToAnimals.put(elem.getPosition(), array);
        } else {
            array.add(elem);
        }
    }

    void removeElement(Animal elem) {
        ArrayList<Animal> array = vectorToAnimals.get(elem.getPosition());

        if (!array.removeAll(Collections.singleton(elem))) {
            throw new IllegalArgumentException("Brak synchronizacji elementow");
        }
        if (array.size() == 0) vectorToAnimals.remove(elem.getPosition());
    }

    Object objectAt(Vector2d vector) {
        return vectorToAnimals.get(vector);
    }

    List<Animal> getAnimalWithHighestEnergy(Vector2d vector) {
        ArrayList<Animal> array = vectorToAnimals.get(vector);
        array.sort(new CompareByEnergy());
        List<Animal> highestEnergyAnimals = new ArrayList<Animal>();
        for (int i = array.size() - 1; i >= 0 && array.get(i).energy == array.get(array.size() - 1).energy; i--) {
            highestEnergyAnimals.add(array.get(i));
            array.remove(i);
        }
        return highestEnergyAnimals;
    }

    List<Animal> getParents(Vector2d vector, int reproductionEnergy) {
        ArrayList<Animal> array = vectorToAnimals.get(vector);
        if (array.size() <= 0) throw new IllegalArgumentException(vector + " on this position none animal exist");
        if(array.size()<2){
            return null;
        }
        array.sort(new CompareByEnergy());
        List<Animal> list = array.subList(array.size() - 2, array.size())
                .stream().filter(elem -> elem.energy >= reproductionEnergy)
                .collect(Collectors.toList());
        if (list.size() >= 2) {
            return list;
        }
        return null;
    }
}