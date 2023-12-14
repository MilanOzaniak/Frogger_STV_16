import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();

    //Vykreslenie mapy
    Car car1 = new Car(20, 346, "./img/car2_right.png", "./img/car2_left.png");
    Car car2 = new Car(230, 416, "./img/car3_right.png", "./img/car3_left.png");
    Tree tree1 = new Tree(50, 20);
    Tree tree2 = new Tree(400, 12);
    Tree tree3 = new Tree(72, 250);
    Tree tree4 = new Tree(220, 270);
    Tree tree5 = new Tree(64, 492);
    Tree tree6 = new Tree(350, 486);
    Tree tree7 = new Tree(400, 12);
    Tree tree8 = new Tree(180, 749);
    Tree tree9 = new Tree(500, 722);
    Log log1 = new Log(0, 672);
    Log log2 = new Log(120, 624);
    Log log3 = new Log(340, 576);
    Log log4 = new Log(30, 192);
    Log log5 = new Log(250, 144);
    Log log6 = new Log(400, 96);


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
    ArrayList<Log> logs = new ArrayList<Log>();

    //





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
        trees.add(tree3);
        trees.add(tree4);
        trees.add(tree5);
        trees.add(tree6);
        trees.add(tree7);
        trees.add(tree8);
        trees.add(tree9);

        water.add(water1);
        water.add(water2);
        grass.add(grass1);
        grass.add(grass2);
        grass.add(grass3);
        grass.add(grass4);
        road.add(road1);

        players.add(player);
        cars.add(car1);
        cars.add(car2);
        logs.add(log1);
        logs.add(log2);
        logs.add(log3);
        logs.add(log4);
        logs.add(log5);
        logs.add(log6);
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


        // AUTO
        for (int i = 0; i < cars.size(); i++) {
            // otačanie auta
            if (cars.get(i).carRectangle.x < 0 || cars.get(i).carRectangle.x + cars.get(i).carRectangle.width > screenWidth) {
                cars.get(i).carSpeed *= -1;
            }

            // updatovanie po X osi
            cars.get(i).update(cars.get(i).carSpeed, 0);

            // ak trafi auto playera
            if (cars.get(i).hasCollided(player.playerRectangle)) {
                player.setPositionStart();
                JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //

        // aby hrač nešiel za mapu

        player.playerRectangle.x = Math.max(0, Math.min(player.playerRectangle.x, screenWidth - tileSize));
        player.playerRectangle.y = Math.max(0, Math.min(player.playerRectangle.y, screenHeight - tileSize));


        // LOG
        for (int i = 0; i < logs.size(); i++) {
            logs.get(i).update(logs.get(i).logSpeed, 0);

            if (logs.get(i).logRectangle.x < 0 || logs.get(i).logRectangle.x + logs.get(i).logRectangle.width > screenWidth) {
                logs.get(i).logSpeed *= -1;
            }

            if (logs.get(i).hasCollided(player.playerRectangle)) {
                player.updateOnLog(logs.get(i).logSpeed, 0);
            }
        }
        //

        // WATER
        for (int i = 0; i < water.size(); i++) {
            boolean isPlayerOnLog = false;

            // test ci je na dreve
            for (int y = 0; y < logs.size(); y++) {
                if (logs.get(y).hasCollided(player.playerRectangle)) {
                    isPlayerOnLog = true;
                    break;
                }
            }

            // Ak trafi vodu
            if (water.get(i).hasCollided(player.playerRectangle) && !isPlayerOnLog) {
                if (players != null) {
                    player.setPositionStart();
                    JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }



        //

        // TREE
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
        //


        // KONIEC HRY WIN
        if(player.playerRectangle.y < 20) {
<<<<<<< Updated upstream
            JOptionPane.showMessageDialog(this, "Congratulations! You WON", "You WON", JOptionPane.INFORMATION_MESSAGE);
=======
            int steps = player.getStepsCount();
            long time = player.stopTime() / 1000;
            JOptionPane.showMessageDialog(this, "Congratulations! You WON\nNumber of steps needed: " + steps + "\nDuration time: " + time + " seconds", "You WON", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> Stashed changes
            System.exit(0);
        }

    }

    public void paintComponent(Graphics graphics) {

        // tu kresliš na platno
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (int i = 0; i < water.size(); i++) {
            water.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < logs.size(); i++) {
            logs.get(i).paintComponent(graphics2D);
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
