package GUI;

import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel {
    public MyPanel() {
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // prostokat

        for(int i=0;i<400;i+=25){
            for(int j=0;j<400;j+=25)
                g2d.drawRect(i+1, j+1, 50, 50);
        }



    }
}
