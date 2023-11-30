import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    // pre obrazky
    BufferedImage imgUp;
    BufferedImage imgDown;
    BufferedImage imgRight;
    BufferedImage imgLeft;
    BufferedImage imgActual;

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
        System.out.println(screenWidth + " " + screenHeight);
    }

    //
    public void loadImageFiles() {
        try {
            imgUp = ImageIO.read(new File(".\\img\\up.png"));
            imgDown = ImageIO.read(new File(".\\img\\down.png"));
            imgRight = ImageIO.read(new File(".\\img\\right.png"));
            imgLeft = ImageIO.read(new File(".\\img\\left.png"));
            imgActual = imgUp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        loadImageFiles();

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
            imgActual = imgUp;
        }else if (controller.downPressed) {
            playerY += playerSpeed;
            imgActual = imgDown;
        }else if (controller.leftPressed) {
            playerX -= playerSpeed;
            imgActual = imgLeft;
        }else if (controller.rightPressed) {
            playerX += playerSpeed;
            imgActual = imgRight;
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
        graphics2D.drawImage(imgActual, null, playerX, playerY);
        graphics2D.dispose();

        //
    }
}
