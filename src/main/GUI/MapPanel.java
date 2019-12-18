package GUI;

import logic.Animal;
import logic.Jungle;
import logic.ReadJson;
import logic.Vector2d;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MapPanel extends JPanel {

    Jungle map;

    int ratioOfScale;

    MapPanel(Jungle map, int n) {
        this.map = map;
        this.ratioOfScale = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

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

        BufferedImage animalImage = ReadImage.getAnimalBufferedImage();
        for (Animal animal : map.getAnimals()) {
//            g2d.setColor(Color.getHSBColor((float) animal.getEnergy(), 0, (float) animal.getEnergy()));
//            g2d.fillRect((int) ((animal.getPosition().x ) * ratioOfScale + 0.1 * ratioOfScale),
//                    (int) ((animal.getPosition().y ) * ratioOfScale + 0.1*ratioOfScale),
//                    (int) (0.8 * ratioOfScale), (int) (0.8*ratioOfScale));
            g2d.drawImage(animalImage, animal.getPosition().x * ratioOfScale,
                    animal.getPosition().y * ratioOfScale, ratioOfScale, ratioOfScale, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getUpperRight().x * ratioOfScale, map.getUpperRight().y * ratioOfScale);
    }


}
