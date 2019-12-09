package logic;

public interface IAnimalStatusChangeObserver {
    void energyChanged(Animal prevState,double newEnergy);
    void positionChanged(Animal prevState,Vector2d newPosition, MapDirection newOrientation,double newEnergy);
}
