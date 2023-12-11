import javax.swing.*;
import java.awt.*;

public class LilyPad {

    Rectangle lilyPadRectangle;

    public LilyPad(int x, int y) {
        lilyPadRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);


    }

    public boolean hasCollided(Rectangle player) {
        if(lilyPadRectangle.intersects(player)) {
            return true;
        }
        return false;
    }
    public void paintComponent(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawRect(lilyPadRectangle.x, lilyPadRectangle.y, 16 * 3, 16 * 3);
    }


}

