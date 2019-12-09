package GUI;

public interface ISimulationChangeListener {
    public void goBackToMenu();
    public void makeMove();
    public void makeNMoves(int n);
    public void eat();
    public void clearDead();
    public void reproduction();
    public void generateGrass();
    public void viewAnimalsList();
}
