import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();
    Car car1 = new Car(20, 20);

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Car> cars = new ArrayList<Car>();



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


    // nastavenia "Platna"
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);

        players.add(player);
        cars.add(car1);
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
            player.update(0, -player.playerSpeed);
        } else if (controller.downPressed) {
            player.update(0, player.playerSpeed);
        } else if (controller.leftPressed) {
            player.update(-player.playerSpeed, 0);
        } else if (controller.rightPressed) {
            player.update(player.playerSpeed, 0);
        }

        // otačanie auta
        if (car1.carRectangle.x < 0 || car1.carRectangle.x + car1.carRectangle.width > screenWidth) {
            car1.carSpeed *= -1;
        }

        // updatovanie po X osi
        car1.update(car1.carSpeed, 0);

        // ak trafi auto playera
        if (car1.hasCollided(player.playerRectangle)) {
            players.remove(0);
            JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        }

        // aby hrač nešiel za mapu

        player.playerRectangle.x = Math.max(0, Math.min(player.playerRectangle.x, screenWidth - tileSize));
        player.playerRectangle.y = Math.max(0, Math.min(player.playerRectangle.y, screenHeight - tileSize));

        //
        int panelOffsetX = screenWidth / 2 - player.playerRectangle.x - tileSize / 2;
        int panelOffsetY = screenHeight / 2 - player.playerRectangle.y - tileSize / 2;
        for (Player p : players) {
            p.move(panelOffsetX, panelOffsetY);
        }

    }

    public void paintComponent(Graphics graphics) {

        // tu kresliš na platno
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (int i = 0; i < players.size(); i++) {
            players.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).paintComponent(graphics2D);
        }
        graphics2D.dispose();

        //
    }
}
