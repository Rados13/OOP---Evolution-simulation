package logic;

import java.util.*;

public class Jungle extends AbstractWorldMap {
    Map<Vector2d, Grass> fields = new LinkedHashMap<Vector2d, Grass>();
    private final Vector2d LowerLeft = new Vector2d(0, 0);
    private final Vector2d UpperRight;
    private double startEnergy;
    private double plantEnergy;
    private double jungleRatio;

    Jungle(int x, int y, double moveEnergy, int id) {
        UpperRight = new Vector2d(x - 1, y - 1);
        this.moveEnergy = moveEnergy;
        this.numberOfAnimals = id;
    }

    Jungle(ArrayList<Double> parameters) {
        if (Math.round(parameters.get(0)) < 0 || Math.round(parameters.get(1)) < 0) {
            throw new IllegalArgumentException(Math.round(parameters.get(0)) + "  " + Math.round(parameters.get(1)) + " are not legal map size");
        }
        UpperRight = new Vector2d((int) Math.round(parameters.get(0)) - 1, (int) Math.round(parameters.get(1)) - 1);


        this.startEnergy = parameters.get(2);
        this.moveEnergy = parameters.get(3);
        this.plantEnergy = parameters.get(4);
        this.jungleRatio = parameters.get(5);


        if (parameters.get(6).intValue() < 0 || parameters.get(6) >= parameters.get(0).intValue() * parameters.get(1).intValue()) {
            throw new IllegalArgumentException(parameters.get(6) + " is not legal number of grass");
        }
        int n = (int) parameters.get(6).longValue();

        for (int i = 0; i < n / 2; i++) {
            Generate.generateGrassInsideJungle(this);
        }
        for (int i = n/2; i < n ; i++) {
            Generate.generateGrassSavanna(this);
        }


        for (int i = 0; i < parameters.get(7); i++) {
            new Animal(this, startEnergy);
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


    public void run() {
        for (Animal anim : animals) {
            anim.move();
        }
    }

    void clearMapOfDeadths() {
        for (Animal anim : new LinkedList<>(animals)) {
            if(anim.isDead()){
                animals.remove(anim);
            }
        }
    }

    void generateGrassForOneDay() {
        Generate.generateGrassInsideJungle(this);
        Generate.generateGrassSavanna(this);
    }

    void eating() {
        for (Animal anim : animals) {
            if (fields.get(anim.getPosition()) != null) {
                fields.remove(anim.getPosition());
                List<Animal> list = animalsStatus.getAnimalWithHighestEnergy(anim.getPosition());
                for (Animal elem : list) {
                    elem.energyChange( plantEnergy / list.size());
                }
            }
        }
    }

    void reproduction() {
        ArrayList<Animal> usedParents = new ArrayList<Animal>();
        for (Vector2d vector : new ArrayList<Vector2d>(animalsStatus.vectorToAnimals.keySet())) {
            List<Animal> parents = animalsStatus.getParents(vector, startEnergy / 2);
            if (parents != null) {
                usedParents.addAll(parents);
                Vector2d position = Generate.generateFreeSpace(vector, this);
                Animal child = new Animal(this, parents.get(0).energy / 4 + parents.get(1).energy / 4, position.x, position.y, childrenGene(parents));
            }
        }


        for (Animal elem : usedParents) {
            elem.energyChange(elem.energy - this.startEnergy / 2);
            elem.newChildren();
        }

    }

    Genotype childrenGene(List<Animal> parents) {
        return parents.get(0).gen.getChildrenGene(parents.get(1).gen);
    }

    public Vector2d getLowerLeft() {
        return LowerLeft;
    }

    public Vector2d getUpperRight() {
        return UpperRight;
    }

    public ArrayList<Vector2d> getFields() {
        return new ArrayList<>(fields.keySet());
    }

    public ArrayList<Animal> getAnimals() {
        return new ArrayList<Animal>(animals);
    }

    public double getJungleRatio() {
        return jungleRatio;
    }

    public int getJungleWidth() {
        return (int) Math.round(UpperRight.x * jungleRatio);
    }

    public int getJungleHeight() {
        return (int) Math.round(UpperRight.y * jungleRatio);
    }

    public int getJungleBeginX() {
        return (UpperRight.x - getJungleWidth()) / 2 + 1;
    }

    public int getJungleBeginY() {
        return (UpperRight.y - getJungleHeight()) / 2 + 1;
    }

    public int getSavannaWidth() {
        return UpperRight.x - getJungleWidth();
    }

    public int getSavannaHeight() {
        return UpperRight.y - getJungleHeight();
    }

    public int getSavannaSecondBeginX() {
        return UpperRight.x - getSavannaWidth() / 2;
    }

    public int getSavannaSecondBeginY() {
        return UpperRight.y - getSavannaHeight() / 2;
    }

}
