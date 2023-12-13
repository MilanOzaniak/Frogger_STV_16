import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player {
    int playerX = 240;
    int playerY = 864;
    int playerSpeed = 48;
    int movementSpeed = 250;
    int stepsCount = 0;

    Image playerImageRight;
    Image playerImageLeft;
    Image playerImageUp;
    Image playerImageDown;
    Image currentImage;
    Rectangle playerRectangle;
    long lastRunTime = System.currentTimeMillis();

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
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastRunTime >= movementSpeed) {
            lastRunTime = currentTime;

            playerRectangle.x += x;
            playerRectangle.y += y;

            if (x > 0) {
                currentImage = playerImageRight;
            } else if (x < 0) {
                currentImage = playerImageLeft;
            } else if (y > 0) {
                currentImage = playerImageDown;
            } else if (y < 0) {
                currentImage = playerImageUp;
            }

            if (x != 0 || y != 0) { //rata pocet krokov
                stepsCount++;
            }
        }

        // Aktualizovanie obrazkov
    }

    public void updateOnLog(int x, int y){
        playerRectangle.x += x;
        playerRectangle.y += y;
    }

    public void setPositionStart() {
        playerRectangle.x = 240;
        playerRectangle.y = 864;

        currentImage = playerImageUp;
        stepsCount = 0;
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(currentImage, playerRectangle.x, playerRectangle.y, 16 * 3, 16 * 3, null);
    }

    public int getStepsCount() {
        return stepsCount;
    }
}
