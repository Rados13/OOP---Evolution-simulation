package logic;

import java.util.*;

public class Gene {
    private ArrayList<Integer> genoType;

    Gene() {
        genoType = new ArrayList<Integer>(
                Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7));

    }

    Gene(ArrayList<Integer> array, boolean possibleScaling) {
        if (array.size() == 32) {
            Collections.sort(array);
            genoType = array;
        } else {
            if (possibleScaling) {
                Collections.sort(array);
                genoType=scaling(array);
            } else {
                throw new IllegalArgumentException("Size of array is not equal to 32 " + array.size());
            }
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

    ArrayList<Integer> getNPartsFromgenDivedOnThreeParts(int n) {
        int endFirstPart = (int) Math.round(Math.random() * 29) + 1;
        int endSecondPart = (int) Math.round(Math.random() * (31 - endFirstPart)) + endFirstPart;

        ArrayList<List<Integer>> partedGene = new ArrayList<List<Integer>>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        partedGene.add(genoType.subList(0,endFirstPart));
        partedGene.add(genoType.subList(endFirstPart,endSecondPart));
        partedGene.add(genoType.subList(endSecondPart,32));

        int randomPick = (int) Math.round(Math.random()*2);

        result.addAll(partedGene.get(randomPick));

        if(n==2){
            int secondRandomPick = (int) Math.round(Math.random()*2);
            while(randomPick==secondRandomPick){
                secondRandomPick = (int) Math.round(Math.random()*2);
            }
            result.addAll(partedGene.get(secondRandomPick));
        }

        return result;
    }


    ArrayList<Integer> scaling(ArrayList<Integer> array){
        ArrayList<Double> probabilityOfMove = new ArrayList<Double>();
        for(int i=0;i<8;i++){
            probabilityOfMove.add(0.0);
        }
        for(Integer elem : array){
            probabilityOfMove.set(elem,probabilityOfMove.get(elem)+1);
        }
        int sum=0;
        for (Double elem : probabilityOfMove){
            if(elem==0){
                elem+=1;
            }
            sum+=elem;
        }
        for(Double elem : probabilityOfMove){
            elem/=sum;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<8;i++){
            for(int j=0;j<probabilityOfMove.get(i)*32;j++){
                result.add(i);
            }
        }
        Collections.sort(result);

        return result;
    }

}
