package GUI;

public interface ISimulationChangeListener {
    public void setParameters();
    public void stop();
    public void start();
    public void makeNTurn(int n);
    public void viewAnimalsList();
}
