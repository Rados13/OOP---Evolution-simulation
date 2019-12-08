package logic;

public interface IEnergyChangeObserver {
    void energyChanged(Animal prevState,Vector2d newPosition, MapDirection newOrientation);
}
