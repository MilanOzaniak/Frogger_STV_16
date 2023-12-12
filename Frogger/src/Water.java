import javax.swing.*;
import java.awt.*;

public class Water {
    Image waterImage;
    Rectangle waterRectangle;

    public Water(int x, int y) {
        waterRectangle = new Rectangle(x, y, (16 * 3) * 12, (16 * 3) * 3);

        // nacitanie obrazkov
        waterImage = new ImageIcon("./img/waterPanel.png").getImage();
    }

    public boolean hasCollided(Rectangle player) {
        if(waterRectangle.intersects(player)) {
            return true;
        }
        return false;
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(waterImage, waterRectangle.x, waterRectangle.y, (16 * 3) * 12, (16 * 3) * 3, null);

    }

}