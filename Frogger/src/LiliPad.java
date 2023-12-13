import javax.swing.*;
import java.awt.*;

public class LiliPad {

    Rectangle liliPadRectangle;

    public LiliPad(int x, int y) {
        liliPadRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);


    }

    public boolean hasCollided(Rectangle player) {
        if(liliPadRectangle.intersects(player)) {
            return true;
        }
        return false;
    }
    public void paintComponent(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawRect(liliPadRectangle.x, liliPadRectangle.y, 16 * 3, 16 * 3);
    }


}