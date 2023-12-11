import java.awt.*;

public class Player {
<<<<<<< Updated upstream
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
=======
    int playerX = 200;
    int playerY = 860;
    int playerSpeed = 5;
    Image playerImageRight;
    Image playerImageLeft;
    Image playerImageUp;
    Image playerImageDown;
    Image currentImage;
>>>>>>> Stashed changes
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
