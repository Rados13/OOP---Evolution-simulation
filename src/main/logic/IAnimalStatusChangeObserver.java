package logic;

public interface IAnimalStatusChangeObserver {
    void energyChanged(Animal prevState,int newEnergy);
    void positionChanged(Animal prevState,Vector2d newPosition, MapDirection newOrientation,int newEnergy);
}
