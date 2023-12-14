import javax.swing.*;
import java.awt.*;

public class Car {

    int carSpeed = 4;
    Image carImageRight;
    Image carImageLeft;
    Image currentImage;
    Rectangle carRectangle;

    public Car(int x, int y, String imageRight, String imageLeft) {
        carRectangle = new Rectangle(x, y, 16 * 4, 16 * 3);

        // nacitanie obrazkov
        carImageRight = new ImageIcon(imageRight).getImage();
        carImageLeft = new ImageIcon(imageLeft).getImage();

        currentImage = carImageRight;
    }


    public void update(int x, int y) {
        carRectangle.x += x;
        carRectangle.y += y;

        // Aktualizovanie obrazkov
        if (x > 0) {
            currentImage = carImageRight;
        } else if (x < 0) {
            currentImage = carImageLeft;
        }
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.drawImage(currentImage, carRectangle.x, carRectangle.y, 16 * 4, 16 * 3, null);
    }

    public boolean hasCollided(Rectangle player) {
        if(carRectangle.intersects(player)) {
            return true;
        }
       return false;
    }


}
