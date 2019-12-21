package logic;

import java.util.*;

public class Genotype {
    final public ArrayList<Integer> genoType;


    Genotype() {
        ArrayList<Integer> randomGens = new ArrayList<Integer>();
        for (int i = 0; i < 32; i++) {
            randomGens.add((int) Math.round(Math.random() * 7));
        }
        genoType = makeAllMovesExist(randomGens);
    }

    Genotype(ArrayList<Integer> array) {
        if (array.size() == 32) {
            genoType = makeAllMovesExist(array);
        } else {
            throw new IllegalArgumentException("Size of array is not equal to 32 " + array.size());
        }
    }


    public String toString() {
        return genoType.toString();
    }

    int getMoveFromGene() {
        int rand = (int) (Math.random() * 32);
        return genoType.get(rand);
    }

    Genotype getChildrenGene(Genotype secondParentGenotype) {
        int endFirstPart = (int) Math.round(Math.random() * 29) + 1;
        int endSecondPart = (int) Math.round(Math.random() * (31 - endFirstPart)) + endFirstPart;

        ArrayList<ArrayList<Integer>> genes = new ArrayList<ArrayList<Integer>>();
        genes.add(genoType);
        genes.add(secondParentGenotype.genoType);
        ArrayList<Integer> result = new ArrayList<Integer>();

        int rand = (int) Math.round(Math.random());
        int i = rand;
        result.addAll(genes.get(rand).subList(0, endFirstPart));

        rand = (int) Math.round(Math.random());
        i += rand;
        result.addAll(genes.get(rand).subList(endFirstPart, endSecondPart));

        if (i == 2) {
            result.addAll(genes.get(0).subList(endSecondPart, 32));
        } else if (i == 0) {
            result.addAll(genes.get(1).subList(endSecondPart, 32));
        } else {
            rand = (int) Math.round(Math.random());
            result.addAll(genes.get(rand).subList(endSecondPart, 32));
        }


        return new Genotype(result);
    }

    private ArrayList<Integer> makeAllMovesExist(ArrayList<Integer> possibleGene) {
        Collections.sort(possibleGene);
        ArrayList<Integer> probabilityOfMove = new ArrayList<Integer>(8);
        for (int i = 0; i < 8; i++) {
            probabilityOfMove.add(0);
        }
        for (Integer elem : possibleGene) {
            probabilityOfMove.set(elem, probabilityOfMove.get(elem) + 1);
        }
        int random;
        for (int i = 0; i < probabilityOfMove.size(); i++) {
            while (probabilityOfMove.get(i) == 0) {
                random = (int) Math.round(Math.random() * 7);
                if (probabilityOfMove.get(random) > 1) {
                    probabilityOfMove.set(random, probabilityOfMove.get(random) - 1);
                    probabilityOfMove.set(i, probabilityOfMove.get(i) + 1);
                }
            }
        }
        possibleGene.clear();
        for (int i = 0; i < probabilityOfMove.size(); i++) {
            for (int j = probabilityOfMove.get(i); j > 0; j--) {
                possibleGene.add(i);
            }
        }
        return possibleGene;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genotype)) return false;
        Genotype that = (Genotype) o;
        return Arrays.equals(this.genoType.stream().mapToInt(i -> i).toArray(), that.genoType.stream().mapToInt(i -> i).toArray());
    }


    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < genoType.size(); i++) {
            sum += genoType.get(i) * (i + 1);
        }
        return sum;
    }
}

