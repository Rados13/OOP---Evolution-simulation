package GUI;

import logic.Animal;
import logic.Jungle;
import logic.ReadJson;
import logic.Vector2d;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MapPanel extends JPanel implements MouseListener {

    private Jungle map;

    private int ratioOfScale;

    private Animal markedOne = null;

    boolean highlight = false;

    private double scaleOfElement = 0.1;

    private ISimulationChangeListener listener;

    Graphics2D g2d;

    MapPanel(Jungle map, int n, ISimulationChangeListener listener) {
        this.map = map;
        this.ratioOfScale = n;
        addMouseListener(this);
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.g2d = g2d;
        double jungleRatio = ReadJson.getJungleRatio();


        g2d.setColor(new Color(0, 212, 7));
        g2d.fillRect(map.getJungleBeginX() * ratioOfScale, map.getJungleBeginY() * ratioOfScale,
                map.getJungleWidth() * ratioOfScale, map.getJungleHeight() * ratioOfScale);

        for (int i = 0; i <= map.getUpperRight().x * ratioOfScale; i += ratioOfScale) {
            for (int j = 0; j <= map.getUpperRight().y * ratioOfScale; j += ratioOfScale) {
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawRect(i, j, ratioOfScale, ratioOfScale);
            }
        }

        BufferedImage grassImage = ReadImage.getGrassBufferedImage();
        for (Vector2d grassPosition : map.getFields()) {
            g2d.drawImage(grassImage, grassPosition.x * ratioOfScale, grassPosition.y * ratioOfScale,
                    ratioOfScale, ratioOfScale, new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
        }

        if (markedOne != null && map.getDeadAgeOfMarkedOne() < 0) {
            g2d.setColor(new Color(255, 0, 0));
            fillRect(markedOne);
        }

        g2d.setColor(new Color(0, 13, 255));
        if (highlight) {
            for (Animal anim : map.getAnimalsWithDominantGenotype()) {
                fillRect(anim);
            }
        }


        BufferedImage animalImage = ReadImage.getAnimalBufferedImage();
        for (Animal animal : map.getAnimals()) {
//            g2d.setColor(Color.getHSBColor((float) animal.getEnergy(), 0, (float) animal.getEnergy()));
//            g2d.fillRect((int) ((animal.getPosition().x ) * ratioOfScale + 0.1 * ratioOfScale),
//                    (int) ((animal.getPosition().y ) * ratioOfScale + 0.1*ratioOfScale),
//                    (int) (0.8 * ratioOfScale), (int) (0.8*ratioOfScale));
            g2d.drawImage(animalImage, (int) ((animal.getPosition().x + scaleOfElement) * ratioOfScale),
                    (int) ((animal.getPosition().y + scaleOfElement) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale), new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
        }

    }

    Animal getMarkedOne() {
        return markedOne;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getUpperRight().x * ratioOfScale, map.getUpperRight().y * ratioOfScale);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Animal potentialMarked = map.getAnimalWithHighestEnergy(new Vector2d(Math.round(e.getX() / ratioOfScale), Math.round(e.getY() / ratioOfScale)));
        if (potentialMarked != null && potentialMarked.equals(markedOne)) {
            markedOne = null;
        } else {
            markedOne = potentialMarked;
        }
        listener.refreshMarkedOne();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void highlightAnimalsDominantGenotype() {
        this.highlight = !this.highlight;
    }

    private void fillRect(Animal anim) {
        g2d.fillRect(anim.getPosition().x * ratioOfScale,
                anim.getPosition().y * ratioOfScale, ratioOfScale, ratioOfScale);

    }

}
