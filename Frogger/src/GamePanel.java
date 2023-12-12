import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();
    Car car1 = new Car(20, 346);
    Tree tree1 = new Tree(50, 20);
    Tree tree2 = new Tree(400, 12);

    Grass grass4 = new Grass(0, 0);
    Water water2 = new Water(0, 96);
    Grass grass3 = new Grass(0, 240);
    Road road1 = new Road(0, 336);
    Grass grass2 = new Grass(0, 480);
    Water water1 = new Water(0, 576);
    Grass grass1 = new Grass(0, 720);


    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Car> cars = new ArrayList<Car>();
    ArrayList<Tree> trees = new ArrayList<Tree>();
    ArrayList<Water> water = new ArrayList<Water>();
    ArrayList<Grass> grass = new ArrayList<Grass>();
    ArrayList<Road> road = new ArrayList<Road>();



    // Nastavenia GUI (48x48, 816x576 px)
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 17;
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

        trees.add(tree1);
        trees.add(tree2);

        water.add(water1);
        water.add(water2);
        grass.add(grass1);
        grass.add(grass2);
        grass.add(grass3);
        grass.add(grass4);
        road.add(road1);

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

        for(int i = 0; i < water.size(); i++) {
            if(water.get(i).hasCollided(player.playerRectangle)) {
                if(players != null) {
                    players.remove(0);
                    JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }

        //

        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).hasCollided(player.playerRectangle)) {
                if (controller.upPressed) {
                    player.playerRectangle.y = trees.get(i).treeRectangle.y + trees.get(i).treeRectangle.height;
                } else if (controller.downPressed) {
                    player.playerRectangle.y = trees.get(i).treeRectangle.y - player.playerRectangle.height;
                } else if (controller.leftPressed) {
                    player.playerRectangle.x = trees.get(i).treeRectangle.x + trees.get(i).treeRectangle.width;
                } else if (controller.rightPressed) {
                    player.playerRectangle.x = trees.get(i).treeRectangle.x - player.playerRectangle.width;
                }
            }

        }

    }

    public void paintComponent(Graphics graphics) {

        // tu kresliš na platno
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (int i = 0; i < water.size(); i++) {
            water.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < road.size(); i++) {
            road.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < grass.size(); i++) {
            grass.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < trees.size(); i++) {
            trees.get(i).paintComponent(graphics2D);
        }

        graphics2D.dispose();

    }
}
