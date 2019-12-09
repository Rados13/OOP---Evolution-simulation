package logic;

import java.util.*;

public class Gene {
    final public ArrayList<Integer> genoType;

    Gene() {
        genoType = new ArrayList<Integer>(
                Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7));

    }

    Gene(ArrayList<Integer> array) {
        if (array.size() == 32) {
            genoType = makeAllMovesExist(array);
        } else {
            throw new IllegalArgumentException("Size of array is not equal to 32 " + array.size());
        }
    }

    int getMoveFromGene() {
        int rand = (int) (Math.random() * 32);
        return genoType.get(rand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gene)) return false;
        Gene that = (Gene) o;
        return Arrays.equals(this.genoType.stream().mapToInt(i -> i).toArray(), that.genoType.stream().mapToInt(i -> i).toArray());
    }

    Gene getChildrenGene(Gene secondParentGene) {
        int endFirstPart = (int) Math.round(Math.random() * 29) + 1;
        int endSecondPart = (int) Math.round(Math.random() * (31 - endFirstPart)) + endFirstPart;

        ArrayList<ArrayList<Integer>> genes = new ArrayList<ArrayList<Integer>>();
        genes.add(genoType);
        genes.add(secondParentGene.genoType);
        ArrayList<Integer> result = new ArrayList<Integer>();

        int rand = (int) Math.round(Math.random());
        int i = rand;

        result.addAll(genes.get(rand).subList(0, endFirstPart));
        rand = (int) Math.round(Math.random());
        i += rand;
        result.addAll(genes.get(rand).subList(endFirstPart, endSecondPart));
        if (i == 2) {
            result.addAll(genes.get(0).subList(endSecondPart, 32));
        }
        if (i == 0) {
            result.addAll(genes.get(1).subList(endSecondPart, 32));
        } else {
            rand = (int) Math.round(Math.random());
            result.addAll(genes.get(1).subList(endSecondPart, 32));
        }

        return new Gene(result);
    }

    ArrayList<Integer> makeAllMovesExist(ArrayList<Integer> possibleGene) {
        Collections.sort(possibleGene);
        int[] probabilityOfMove = new int[8];
        for (Integer elem : possibleGene) {
            probabilityOfMove[elem]++;
        }
        int random;
        for (int elem : probabilityOfMove) {
            while (elem == 0) {
                random = (int) Math.round(Math.random() * 7);
                if(probabilityOfMove[random]>1){
                    probabilityOfMove[random]--;
                    elem++;
                }
            }
        }
        possibleGene.clear();
        for(int elem : probabilityOfMove){
            for(int j=elem;j>0;j--){
                possibleGene.add(elem);
            }
        }
        return possibleGene;
    }
}

