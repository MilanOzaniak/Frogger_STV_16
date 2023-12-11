import javax.swing.*;
import java.awt.*;

public class Log {

    Image logImage;
    Rectangle logRectangle;

    public Log(int x, int y) {
        logRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);

        // nacitanie obrazkov
        logImage = new ImageIcon("./img/log_in_water.png").getImage();

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
