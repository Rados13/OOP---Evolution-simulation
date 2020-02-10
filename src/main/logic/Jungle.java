package logic;

import java.util.*;

public class Jungle extends AbstractWorldMap {
    private Map<Vector2d, Grass> vectorToGrass = new LinkedHashMap<Vector2d, Grass>();
    private final Vector2d LowerLeft = new Vector2d(0, 0);
    private final Vector2d UpperRight;
    private double plantEnergy;
    private double jungleRatio;
    private MapObserver statistics;

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
            generateGrassForOneDay();
        }

        if (n % 2 == 1) {
            Vector2d potentialPosition = Generator.generatePositionInsideJungle(this);
            if (potentialPosition != null) vectorToGrass.put(potentialPosition, new Grass(potentialPosition));
        }

        for (int i = 0; i < parameters.get(7); i++) {
            new Animal(this, startEnergy);
        }
        this.statistics = new MapObserver(this);
    }

    public String toString() {
        return super.toString();
    }

    public Object objectAt(Vector2d position) {
        Object something = super.objectAt(position);
        if (something != null) {
            return something;
        }
        return vectorToGrass.get(position);
    }


    public void run() {
        for (Animal anim : animals) {
            anim.move();
        }
    }

    void clearMapOfDeadths() {
        for (Animal anim : new LinkedList<>(animals)) {
            if (anim.isDead()) {
                animals.remove(anim);
            }
        }
    }

    void generateGrassForOneDay() {
        Vector2d potentialPosition = Generator.generatePositionInsideJungle(this);
        if (potentialPosition != null && objectAt(potentialPosition) == null) {
            vectorToGrass.put(potentialPosition, new Grass(potentialPosition));
        }
        potentialPosition = Generator.generatePositionSavanna(this);
        if (potentialPosition != null && objectAt(potentialPosition) == null) {
            vectorToGrass.put(potentialPosition, new Grass(potentialPosition));
        }
    }

    void eating() {
        for (Animal anim : animals) {
            if (vectorToGrass.get(anim.getPosition()) != null) {
                vectorToGrass.remove(anim.getPosition());
                List<Animal> list = animalsStatus.getAnimalWithHighestEnergy(anim.getPosition());
                for (Animal elem : list) {
                    elem.energyChange(plantEnergy / list.size() + elem.getEnergy());
                }
            }
        }
    }

    void reproduction() {

        for (Vector2d vector : new ArrayList<Vector2d>(animalsStatus.vectorToAnimals.keySet())) {
            List<Animal> parents = animalsStatus.getParents(vector, startEnergy / 2);
            if (parents != null) {
                Vector2d position = Generator.generateFreeSpace(vector, this);
                if (position == null) continue;
                Animal child = new Animal(this, parents.get(0).energy / 4 + parents.get(1).energy / 4,
                        position.x, position.y, childrenGene(parents));
                for (Animal elem : new ArrayList<>(parents)) {
                    if (ancestorStatus.heirOFMarked(elem)) ancestorStatus.addElement(child);
                    elem.energyChange(elem.energy - elem.energy / 4);
                    elem.newChildren();
                }
            }
        }


    }

    private Genotype childrenGene(List<Animal> parents) {
        return parents.get(0).gen.getChildrenGene(parents.get(1).gen);
    }

    public Vector2d futurePosition(Vector2d addedVector) {
        int x = addedVector.x;
        int y = addedVector.y;
        if (x < 0) {
            x = getUpperRight().x;
        }
        if (x > getUpperRight().x) {
            x = 0;
        }
        if (y < 0) {
            y = getUpperRight().y;
        }

        if (y > getUpperRight().y) {
            y = 0;
        }
        return new Vector2d(x, y);
    }

    public Vector2d getLowerLeft() {
        return LowerLeft;
    }

    public Vector2d getUpperRight() {
        return UpperRight;
    }

    public ArrayList<Vector2d> getGrass() {
        return new ArrayList<>(vectorToGrass.keySet());
    }

    public ArrayList<Animal> getAnimals() {
        return new ArrayList<Animal>(animals);
    }

    double getJungleRatio() {
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

    int getSavannaWidth() {
        return UpperRight.x - getJungleWidth();
    }

    int getSavannaHeight() {
        return UpperRight.y - getJungleHeight();
    }

    int getSavannaSecondBeginX() {
        return UpperRight.x - getSavannaWidth() / 2;
    }

    int getSavannaSecondBeginY() {
        return UpperRight.y - getSavannaHeight() / 2;
    }

    public int getNumberOfGrass() {
        if (vectorToGrass == null) return 0;
        return vectorToGrass.size();
    }

    void updateStatistics() {
        statistics.update();
    }

    MapObserver getStatistics() {
        return statistics;
    }

}
