package logic;

public interface IAnimalStatusChangeObserver {
    void energyChange(Animal prevState,double newEnergy);
    void positionChange(Animal prevState,Vector2d newPosition, MapDirection newOrientation,double newEnergy);
    void deadth(Animal prevState);
    void addElement(Animal anim);
}
