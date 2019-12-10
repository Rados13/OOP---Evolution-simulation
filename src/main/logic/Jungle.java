package logic;

import java.util.*;
import java.util.stream.Collectors;

public class Jungle extends AbstractWorldMap {
    Map<Vector2d, Grass> fields = new LinkedHashMap<Vector2d, Grass>();
    private Vector2d LowerLeft = new Vector2d(0, 0);
    private Vector2d UpperRight = new Vector2d(0, 0);
    private double startEnergy;
    private double moveEnergy;
    private double plantEnergy;
    private double jungleRatio;

    Jungle(int x, int y, double moveEnergy) {
        UpperRight = UpperRight.add(new Vector2d(x - 1, y - 1));
        this.moveEnergy = moveEnergy;
    }

    Jungle(ArrayList<Double> parameters) {
        if (Math.round(parameters.get(0)) < 0 || Math.round(parameters.get(1)) < 0) {
            throw new IllegalArgumentException(Math.round(parameters.get(0)) + "  " + Math.round(parameters.get(1)) + " are not legal map size");
        }
        UpperRight = UpperRight.add(new Vector2d((int)Math.round(parameters.get(0)) - 1, (int)Math.round(parameters.get(1)) - 1));

        this.startEnergy = parameters.get(2);
        this.moveEnergy = parameters.get(3);
        this.plantEnergy = parameters.get(4);
        this.jungleRatio = parameters.get(5);


        if (parameters.get(6).intValue() < 0 || parameters.get(6) >= parameters.get(0).intValue() * parameters.get(1).intValue()) {
            throw new IllegalArgumentException(parameters.get(6) + " is not legal number of grass");
        }
        int n = (int) parameters.get(6).longValue();
        while (fields.size() < n * 3 / 4) {
            generateGrassCenter();
        }
        while (fields.size() < n) {
            generateGrassAllMap();
        }
    }

    public String toString() {
        return super.toString();
    }

    public Object objectAt(Vector2d position) {
        Object something = super.objectAt(position);
        if (something != null) {
            return something;
        }
        for (Grass elem : fields.values()) {
            if (elem.getPosition().equals(position)) return elem;
        }
        return null;
    }

    @Override
    void positionChanged(Animal anim,Vector2d newPosition,MapDirection newOrientation) {
        status.positionChanged(anim,newPosition,newOrientation,anim.energy-moveEnergy);
    }

    void clearMapOfDeadths() {
        for (Animal anim : animals) {
            if (anim.energy <= 0) {
                status.removeElement(anim);
            }
        }
        animals=animals.stream().filter(anim -> status.exist(anim)).collect(Collectors.toCollection(LinkedList::new));
    }

    void generateGrassForOneDay(){
        generateGrassCenter();
        generateGrassAllMap();
    }

    void eating() {

        for (Animal anim : animals) {
            if (fields.get(anim.getPosition()) != null) {
                fields.remove(anim.getPosition());
                List<Animal> list = status.getAnimalWithHighestEnergy(anim.getPosition());
                for (Animal elem : list) {
                    elem.energy += plantEnergy / list.size();
                    status.addElement(elem);
                }
            }
        }
    }

    void reproduction() {

        Jungle mapForChildren = new Jungle(UpperRight.x, UpperRight.y, 0);
        ArrayList<Animal> usedParents = new ArrayList<Animal>();
        for (Vector2d vector : status.vectorToAnimals.keySet()) {
            List<Animal> parents = status.getParents(vector, startEnergy / 2);
            if (parents != null) {
                usedParents.addAll(parents);
                Animal child = new Animal(mapForChildren, parents.get(0).energy / 4 + parents.get(1).energy / 4, vector.x, vector.y, childrenGene(parents));
                child.move();
            }
        }
        for (Animal elem : mapForChildren.animals) {
            status.addElement(elem);
            animals.add(elem);
        }
        for (Animal elem : usedParents) {
            status.energyChanged(elem,elem.energy*3/4);

        }

    }

    Gene childrenGene(List<Animal> parents) {
        return parents.get(0).gen.getChildrenGene(parents.get(1).gen);
    }

    public Vector2d getLowerLeft() {
        return LowerLeft;
    }

    public Vector2d getUpperRight() {
        return UpperRight;
    }

    private void generateGrassCenter() {
        Vector2d vector = new Vector2d().generatePosition((int) (getUpperRight().x * jungleRatio),
                (int) (getUpperRight().y * jungleRatio), this);
        fields.put(vector, new Grass(vector));
    }

    private void generateGrassAllMap() {
        Vector2d vector = new Vector2d().generatePosition(0, 0, this);
        fields.put(vector, new Grass(vector));
    }

    public ArrayList<Vector2d> getFields(){
        return new ArrayList<>(fields.keySet());
    }
    public ArrayList<Animal> getAnimals(){
        return new ArrayList<Animal>(animals);
    }
}
