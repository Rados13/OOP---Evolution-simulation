package logic;

import java.util.ArrayList;

public class AncestorStatus implements IAnimalStatusChangeObserver {


    private Animal markedOne;

    private int deadAge;

    private ArrayList<Animal> heirs;

    void setMarkedOne(Animal markedOne){
        this.markedOne=markedOne;
        this.heirs = new ArrayList<Animal>();
        this.deadAge = -1;
        addElement(markedOne);
    }

    @Override
    public void energyChange(Animal prevState, double newEnergy) {}

    @Override
    public void positionChange(Animal prevState, Vector2d newPosition, MapDirection newOrientation, double newEnergy) {}

    @Override
    public void deadth(Animal prevState) {
        if(prevState.equals(markedOne)){
            this.deadAge = prevState.map.getAge();
        }
    }

    @Override
    public void addElement(Animal anim) {
        if(markedOne!=null) {
            if (!heirs.contains(anim)) {
                heirs.add(anim);
                anim.addObserver(this);
            }
        }
    }

    boolean heirOFMarked(Animal anim){
        if(markedOne==null)return false;
        return heirs.contains(anim);
    }

    int getNumberOfHeirs(){
        return heirs.size()-1;
    }

    int getDeadAge(){
        return this.deadAge;
    }

}
