package GUI;

import logic.Animal;
import logic.Jungle;
import logic.Vector2d;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MapPanel extends JPanel {

    Jungle map;

    int ratioOfScale = 40;

    MapPanel(Jungle map,int n){
        this.map=map;
        this.ratioOfScale=n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // prostokaty

        for(int i=0;i<map.getUpperRight().x*ratioOfScale;i+=ratioOfScale){
            for(int j=0;j<map.getUpperRight().y*ratioOfScale;j+=ratioOfScale)
                g2d.drawRect(i, j, ratioOfScale, ratioOfScale);
        }


        for(Vector2d grassPosition : map.getFields()){
            g2d.drawImage(ReadImage.getGrassBufferedImage(), grassPosition.x*ratioOfScale, grassPosition.y*ratioOfScale, ratioOfScale, ratioOfScale, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

        int i=0;
        for(Animal animal : map.getAnimals()){
            g2d.drawImage(ReadImage.getAnimalBufferedImage(), animal.getPosition().x * ratioOfScale, animal.getPosition().y * ratioOfScale, ratioOfScale, ratioOfScale, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getUpperRight().x*ratioOfScale, map.getUpperRight().y*ratioOfScale);
    }




}
