package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenotypeStatus implements IAnimalStatusChangeObserver {

    private Map<Genotype, ArrayList<Animal>> genotypeToAmount = new HashMap<Genotype, ArrayList<Animal>>();

    @Override
    public void energyChange(Animal prevState, double newEnergy) {
    }

    @Override
    public void positionChange(Animal prevState, Vector2d newPosition, MapDirection newOrientation, double newEnergy) {
    }

    @Override
    public void deadth(Animal prevState) {
        ArrayList<Animal> array = genotypeToAmount.get(prevState.getGen());
        array.remove(prevState);
        if (array.size() == 0) genotypeToAmount.remove(prevState.getGen());
    }

    @Override
    public void addElement(Animal anim) {
        if(genotypeToAmount.containsKey(anim.getGen())){
            genotypeToAmount.get(anim.getGen()).add(anim);
        }
        else{
            genotypeToAmount.put(anim.getGen(),new ArrayList<Animal>(Collections.singleton(anim)));
        }
    }

    Genotype getDominantGenotype(){
        Genotype dominant = null;
        int maxAmount = -1;
        for(Genotype elem : genotypeToAmount.keySet()){
            int amount = genotypeToAmount.get(elem).size();
            if(maxAmount<=amount){
                maxAmount = amount;
                dominant = elem;
            }
        }
        return dominant;
    }

    ArrayList<Animal> getAnimalsWithDominantGenotype(){
        return genotypeToAmount.get(getDominantGenotype());
    }

}
