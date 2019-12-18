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
            double diffrence = animalFirst.energy - animalSecond.energy;
            if (diffrence != 0) {
                return (int) diffrence;
            }
            return animalFirst.getId() - animalSecond.getId();
        }
    }

    Map<Vector2d, ArrayList<Animal>> vectorToAnimals = new HashMap<>();

    private int totalLengthOfLifeOfDeadAnimals = 0;
    private int numberOfDeadAnimals = 0;

    public void positionChange(Animal prevState, Vector2d newPosition, MapDirection newOrientation, double newEnergy) {
        removeElement(prevState);
        prevState.setPosition(newPosition);
        prevState.setOrientation(newOrientation);
        prevState.setEnergy(newEnergy);
        addElement(prevState);
    }

    @Override
    public void deadth(Animal prevState) {
        removeElement(prevState);
        totalLengthOfLifeOfDeadAnimals += prevState.map.getAge() - prevState.getBirthAge();
        numberOfDeadAnimals ++;
    }


    public void energyChange(Animal prevState, double newEnergy) {
        ArrayList<Animal> array = vectorToAnimals.get(prevState.getPosition());
        int idx = array.indexOf(prevState);
        prevState.setEnergy(newEnergy);
        array.set(idx,prevState);
    }

    public void addElement(Animal elem) {
        ArrayList<Animal> array = vectorToAnimals.get(elem.getPosition());
        if (array == null) {
            array = new ArrayList<Animal>();
            array.add(elem);
            vectorToAnimals.put(elem.getPosition(), array);
        } else {
            array.add(elem);
        }
    }

    private void removeElement(Animal elem) {
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
        if(array == null) return null;
        array.sort(new CompareByEnergy());
        List<Animal> highestEnergyAnimals = new ArrayList<Animal>();
        for (int i = array.size() - 1; i >= 0 && array.get(i).energy == array.get(array.size() - 1).energy; i--) {
            highestEnergyAnimals.add(array.get(i));
        }
        return highestEnergyAnimals;
    }

    List<Animal> getParents(Vector2d vector, double reproductionEnergy) {
        ArrayList<Animal> array = vectorToAnimals.get(vector);
        if (array.size() <= 0) throw new IllegalArgumentException(vector + " on this position none animal exist");
        if (array.size() < 2) {
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

    double getAverageEnergy(){
        double sum = 0;
        int number = 0;
        for( ArrayList<Animal> array : vectorToAnimals.values()){
            for(Animal anim : array){
                sum+= anim.getEnergy();
                number++;
            }
        }
        return sum/number;
    }

    double getAverageChildren(){
        int sum = 0;
        int number =0;
        for( ArrayList<Animal> array : vectorToAnimals.values()){
            for(Animal anim : array){
                sum+= anim.getNumberOfChildren();
                number++;
            }
        }
        return (double) sum/number;
    }

    double getAverageLengthOfLife(){
        if(numberOfDeadAnimals==0)return 0;
        return (double)totalLengthOfLifeOfDeadAnimals/numberOfDeadAnimals;
    }
}