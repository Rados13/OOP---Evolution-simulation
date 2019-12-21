package GUI;

import logic.Animal;
import logic.Jungle;
import logic.ReadJson;
import logic.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class DrawMapPanel extends JPanel implements MouseListener {

    private Jungle map;

    private int ratioOfScale;

    private Animal markedOne = null;

    private boolean highlight = false;

    private double scaleOfElement = 0.1;

    private MapPanel listener;

    Graphics2D g2d;

    DrawMapPanel(Jungle map, int n, MapPanel listener) {
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

        g2d.setColor(new Color(255, 255, 115));
        g2d.fillRect(0, 0, (map.getUpperRight().x + 1) * ratioOfScale, (map.getUpperRight().y + 1) * ratioOfScale);
        g2d.setColor(new Color(62, 150, 0));
        g2d.fillRect(map.getJungleBeginX() * ratioOfScale, map.getJungleBeginY() * ratioOfScale,
                map.getJungleWidth() * ratioOfScale, map.getJungleHeight() * ratioOfScale);


        g2d.setColor(new Color(0, 13, 255));
        if (highlight && map.getAnimals().size() != 0) {
            for (Animal anim : map.getAnimalsWithDominantGenotype()) {
                fillRect(anim);
            }
        }

        if (markedOne != null && map.getDeadAgeOfMarkedOne() < 0) {
            g2d.setColor(new Color(255, 0, 0));
            fillRect(markedOne);
        }


        g2d.setColor(new Color(104, 255, 0));
        for (Vector2d grassPosition : map.getGrass()) {
            g2d.fillRect((int) ((grassPosition.x + scaleOfElement) * ratioOfScale),
                    (int) ((grassPosition.y + scaleOfElement) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale));
        }

        for (Animal animal : map.getAnimalsWithHighestEnergyForEachPosition()) {
            int redScale = 150;
            int greenScale = 75;
            if (animal.getEnergy() < map.getStartEnergy() / map.getMoveEnergy()) {
                redScale = (int) Math.round((animal.getEnergy() > 0 ? animal.getEnergy() : 0) *
                        map.getMoveEnergy() / map.getStartEnergy() * 150);
                System.out.println(redScale);
                greenScale = (int) Math.round((animal.getEnergy() > 0 ? animal.getEnergy() : 0) *
                        map.getMoveEnergy() / map.getStartEnergy() * 75);
                System.out.println(redScale);
            }
            g2d.setColor(new Color(redScale, greenScale, 0));
            g2d.fillOval((int) ((animal.getPosition().x + scaleOfElement) * ratioOfScale),
                    (int) ((animal.getPosition().y + scaleOfElement) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale),
                    (int) ((1 - scaleOfElement * 2) * ratioOfScale));
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
        Animal potentialMarked = map.getAnimalWithHighestEnergy(new Vector2d(Math.round(e.getX() / ratioOfScale),
                Math.round(e.getY() / ratioOfScale)));
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
