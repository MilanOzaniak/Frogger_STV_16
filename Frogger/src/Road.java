import javax.swing.*;
import java.awt.*;

public class Road {
    Image roadImage;
    Rectangle roadRectangle;

    public Road(int x, int y) {
        roadRectangle = new Rectangle(x, y, (16 * 3) * 12, (16 * 3) * 3);

        // nacitanie obrazkov
        roadImage = new ImageIcon("./img/roadPanel.png").getImage();
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(roadImage, roadRectangle.x, roadRectangle.y, (16 * 3) * 12, (16 * 3) * 3, null);
    }

}