import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player {
    int playerX = 264;
    int playerY = 864;
    int playerSpeed = 4;
    Image playerImageRight;
    Image playerImageLeft;
    Image playerImageUp;
    Image playerImageDown;
    Image currentImage;
    Rectangle playerRectangle;

    public Player() {
        playerRectangle = new Rectangle(playerX, playerY, 16 * 3, 16 * 3);

        // nacitanie obrazkov
        playerImageRight = new ImageIcon("./img/player_right.png").getImage();
        playerImageLeft = new ImageIcon("./img/player_left.png").getImage();
        playerImageUp = new ImageIcon("./img/player_up.png").getImage();
        playerImageDown = new ImageIcon("./img/player_down.png").getImage();

        currentImage = playerImageUp;
    }

    public void update(int x, int y) {
        playerRectangle.x += x;
        playerRectangle.y += y;

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
        graphics2D.drawImage(currentImage, playerRectangle.x, playerRectangle.y, 16 * 3, 16 * 3, null);
    }

    public void move(int offsetX, int offsetY) {
        this.playerRectangle.x += offsetX;
        this.playerRectangle.y += offsetY;
        // Similar move method for the Car class
    }

}
