import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();
<<<<<<< Updated upstream
    Car car1 = new Car(20, 20);
=======

    Car car1 = new Car(0, 650, 5);
    Car car2 = new Car(420, 600, 5);
    Car car3 = new Car(0, 550, 5);

    Tree tree1 = new Tree(0, 700);
    Tree tree2 = new Tree(50, 700);
    Tree tree3 = new Tree(100, 700);
    Tree tree4 = new Tree(200, 700);
    Tree tree5 = new Tree(250, 700);
    Tree tree6 = new Tree(300, 700);
    Tree tree7 = new Tree(400, 700);

    Water water1 = new Water(350, 200);
>>>>>>> Stashed changes

    LilyPad lilyPad1 = new LilyPad(400, 200);

    Log log1 = new Log(600, 200);

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Car> cars = new ArrayList<Car>();
<<<<<<< Updated upstream
=======
    ArrayList<Tree> trees = new ArrayList<Tree>();
    ArrayList<Water> water = new ArrayList<Water>();
    ArrayList<Log> logs = new ArrayList<Log>();
    ArrayList<LilyPad> lilyPads = new ArrayList<LilyPad>();
>>>>>>> Stashed changes



    // Nastavenia GUI (48x48, 480x864 px)
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 10;
    final int maxScreenRow = 18;
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

<<<<<<< Updated upstream
=======
        trees.add(tree1);
        trees.add(tree2);
        trees.add(tree3);
        trees.add(tree4);
        trees.add(tree5);
        trees.add(tree6);
        trees.add(tree7);
        water.add(water1);

>>>>>>> Stashed changes
        players.add(player);

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
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
        }else if (controller.downPressed) {
            player.update(0, player.playerSpeed);
        }else if (controller.leftPressed) {
            player.update(-player.playerSpeed, 0);
        }else if (controller.rightPressed) {
            player.update(player.playerSpeed, 0);
        }

        // otačanie auta, updatovanie a zistovanie hitu

        for(int i  = 0; i < cars.size(); i++) {
            if (cars.get(i).carRectangle.x < 0 || cars.get(i).carRectangle.x + cars.get(i).carRectangle.width > screenWidth) {
                cars.get(i).carSpeed *= -1;
            }
            cars.get(i).update(cars.get(i).carSpeed, 0);

<<<<<<< Updated upstream
        // ak trafi auto playera
        if(car1.hasCollided(player.playerRectangle)) {
            players.remove(0);
            JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
=======
            if (cars.get(i).hasCollided(player.playerRectangle)) {
                players.remove(0);
                JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            }
>>>>>>> Stashed changes

        }

        // aby hrač nešiel za mapu

        player.playerRectangle.x = Math.max(0, Math.min(player.playerRectangle.x, screenWidth - tileSize));
        player.playerRectangle.y = Math.max(0, Math.min(player.playerRectangle.y, screenHeight - tileSize));

        //

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
<<<<<<< Updated upstream
=======

        for (int i = 0; i < trees.size(); i++) {
            trees.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < water.size(); i++) {
            water.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < logs.size(); i++) {
            logs.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < lilyPads.size(); i++) {
            lilyPads.get(i).paintComponent(graphics2D);
        }

>>>>>>> Stashed changes
        graphics2D.dispose();

        //
    }
}
