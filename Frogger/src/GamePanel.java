import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();


    // Nastavenia GUI (48x48, 768x576 px)
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60;
    //

    // default pozicia a rychlosť "hrača"
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // nastavenia "Platna"
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
    }
    //

    public void startGameThread() {
        this.thread = new Thread(this) ;
        thread.start();

    }

    @Override
    public void run() {
        // nastavenie časovania
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
        //

    }

    public void update() {

        // väčšina logiky
        if (controller.upPressed) {
            playerY -= playerSpeed;
        }else if (controller.downPressed) {
            playerY += playerSpeed;
        }else if (controller.leftPressed) {
            playerX -= playerSpeed;
        }else if (controller.rightPressed) {
            playerX += playerSpeed;
        }

        playerX = Math.max(0, Math.min(playerX, screenWidth - tileSize));
        playerY = Math.max(0, Math.min(playerY, screenHeight - tileSize));

        //

    }

    public void paintComponent(Graphics graphics) {

        // tu kresliš na platno
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2D.dispose();

        //
    }
}
