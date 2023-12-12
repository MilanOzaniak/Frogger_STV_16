import javax.swing.*;
import java.awt.*;

public class Grass {
    Image grassImage;
    Rectangle grassRectangle;

    public Grass(int x, int y) {
        grassRectangle = new Rectangle(x, y, (16 * 3) * 12, (16 * 3) * 2);

        // nacitanie obrazkov
        grassImage = new ImageIcon("./img/grassPanel.png").getImage();
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(grassImage, grassRectangle.x, grassRectangle.y, (16 * 3) * 12, (16 * 3) * 2, null);
    }

}