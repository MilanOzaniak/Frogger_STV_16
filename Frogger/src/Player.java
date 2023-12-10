import java.awt.*;

public class Player {
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    Rectangle playerRectangle;

    public Player() {
        playerRectangle = new Rectangle(playerX, playerY, 16 * 3, 16 * 3);
    }

    public void update(int x, int y) {
        playerRectangle.x += x;
        playerRectangle.y += y;

    }
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerRectangle.x, playerRectangle.y, 16 * 3, 16 * 3);

    }

}
