package logic;

import java.util.HashMap;
import java.util.Map;

public class GenotypeStatus implements IAnimalStatusChangeObserver {

    private Map<Genotype, Integer> genotypeToAmount = new HashMap<Genotype, Integer>();

    @Override
    public void energyChange(Animal prevState, double newEnergy) {
    }

    @Override
    public void positionChange(Animal prevState, Vector2d newPosition, MapDirection newOrientation, double newEnergy) {
    }

    @Override
    public void deadth(Animal prevState) {
        int amount = genotypeToAmount.get(prevState.getGen());
        amount--;
        if (amount == 0) genotypeToAmount.remove(prevState.getGen());
    }

    @Override
    public void addElement(Animal anim) {
        if(genotypeToAmount.containsKey(anim.getGen())){
            genotypeToAmount.replace(anim.getGen(),genotypeToAmount.get(anim.getGen())+1);
        }
        else{
            genotypeToAmount.put(anim.getGen(),1);
        }
    }

    public Genotype getDominantGenotype(){
        Genotype dominant = null;
        int maxAmount = -1;
        for(Genotype elem : genotypeToAmount.keySet()){
            int amount = genotypeToAmount.get(elem);
            if(maxAmount<=amount){
                maxAmount = amount;
                dominant = elem;
            }
        }
        return dominant;
    }
}
