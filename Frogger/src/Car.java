import javax.swing.*;
import java.awt.*;

public class Car {

    int carSpeed = 4;
    Image playerImageRight;
    Image playerImageLeft;
    Image playerImageUp;
    Image playerImageDown;
    Image currentImage;
    Rectangle carRectangle;

    public Car(int x, int y, int speed) {
        carSpeed = speed;
        carRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);

        // nacitanie obrazkov
        playerImageRight = new ImageIcon("./img/car2_right.png").getImage();
        playerImageLeft = new ImageIcon("./img/car2_left.png").getImage();
        playerImageUp = new ImageIcon("./img/car2_up.png").getImage();
        playerImageDown = new ImageIcon("./img/car2_down.png").getImage();

        currentImage = playerImageRight;
    }


    public void update(int x, int y) {
        carRectangle.x += x;
        carRectangle.y += y;

        // Aktualizovanie obrazkov
        if (x > 0) {
            currentImage = playerImageRight;
        } else if (x < 0) {
            currentImage = playerImageLeft;
        } else if (y > 0) {
            currentImage = playerImageDown;
        } else if (y < 0) {
            currentImage = playerImageUp;
        }
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        //toto som tu zatial nechal, aby bolo vidiet realnu velkost
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(carRectangle.x, carRectangle.y, 16 * 3, 16 * 3);

        graphics2D.drawImage(currentImage, carRectangle.x, carRectangle.y, 16 * 3, 16 * 3, null);
    }

    public boolean hasCollided(Rectangle player) {
        if(carRectangle.intersects(player)) {
            return true;
        }
       return false;
    }


}
