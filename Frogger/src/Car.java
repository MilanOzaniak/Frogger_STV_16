import java.awt.*;

public class Car {

    int carSpeed = 4;
    Rectangle carRectangle;

    public Car(int x, int y) {
        carRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);
    }


    public void update(int x, int y) {
        carRectangle.x += x;
        carRectangle.y += y;

    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(carRectangle.x, carRectangle.y, 16 * 3, 16 * 3);

    }

    public boolean hasCollided(Rectangle player) {
        if(carRectangle.intersects(player)) {
            return true;
        }
       return false;
    }
}
