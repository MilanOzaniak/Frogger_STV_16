import javax.swing.*;
import java.awt.*;

public class Log {

    Image logImage;
    Rectangle logRectangle;
    int logSpeed = 4;

    public Log(int x, int y) {
        logRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);

        // nacitanie obrazkov
        logImage = new ImageIcon("./img/log.png").getImage();

    }

    public void update(int x, int y) {
        logRectangle.x += x;
        logRectangle.y += y;

    }

    public boolean hasCollided(Rectangle player) {
        if(logRectangle.intersects(player)) {
            return true;
        }
        return false;
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(logImage, logRectangle.x, logRectangle.y, 16 * 3, 16 * 3, null);
    }


}