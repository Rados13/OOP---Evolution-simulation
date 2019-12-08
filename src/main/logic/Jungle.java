package logic;

import java.util.*;

public class Jungle extends AbstractWorldMap {
    Map<Vector2d, Grass> fields = new LinkedHashMap<Vector2d, Grass>();
    private Vector2d LowerLeft = new Vector2d(0, 0);
    private Vector2d UpperRight = new Vector2d(0, 0);
    int startEnergy = 100;
    int moveEnergy = 10;
    int plantEnergy = 50;
    double jungleRatio = 0.4;
    Jungle(int n, int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(x + y + " are not legal map size");
        }
        UpperRight = UpperRight.add(new Vector2d(x - 1, y - 1));
        if (n < 0 || n >= x * y) {
            throw new IllegalArgumentException(n + " is not legal number of grass");
        }


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

    void clearMapOfDeadths() {
        for (Animal anim : animals) {
            if (anim.energy <= 0) {
                animals.remove(anim);
                status.removeElement(anim);
            }
        }
    }

    void eating() {

        for(Animal anim : animals) {
            if (fields.get(anim.getPosition()) != null) {
                fields.remove(anim.getPosition());
                List<Animal> list=status.getAnimalWithHighestEnergy(anim.getPosition());
                for(Animal elem : list){
                    elem.energy+=plantEnergy/list.size();
                    status.addElement(elem);
                }
            }
        }
    }

    void reproduction(){

        Jungle mapForChildren=new Jungle(0,100,100);
        for (Vector2d vector : status.vectorToAnimals.keySet()){
            List<Animal> parents = status.getParents(vector, startEnergy/2);
            if(parents!=null){
                Animal child = new Animal(mapForChildren,parents.get(0).energy/4+parents.get(1).energy/4+moveEnergy,vector.x,vector.y,childrenGene(parents));
                child.move();
            }
        }
        for(Animal elem : mapForChildren.animals){
            status.addElement(elem);
            animals.add(elem);
        }

    }

    Gene childrenGene(List<Animal> parents){
        int biggerPartOfGene = (int) Math.round(Math.random());
        ArrayList<Integer> newSchemeOfGene = new ArrayList<Integer>();
        newSchemeOfGene.addAll(parents.get(biggerPartOfGene).gen.getNPartsFromgenDivedOnThreeParts(2));
        newSchemeOfGene.addAll(parents.get(1-biggerPartOfGene).gen.getNPartsFromgenDivedOnThreeParts(1));
        return new Gene(newSchemeOfGene,true);
    }

    Vector2d getLowerLeft() {
        return LowerLeft;
    }

    Vector2d getUpperRight() {
        return UpperRight;
    }

    private void generateGrassCenter() {
        Vector2d vector = new Vector2d().generatePosition((int)(getUpperRight().x *  jungleRatio),
                (int)(getUpperRight().y * jungleRatio), this);
        fields.put(vector, new Grass(vector));
    }

    private void generateGrassAllMap() {
        Vector2d vector = new Vector2d().generatePosition(0, 0, this);
        fields.put(vector, new Grass(vector));
    }

}
