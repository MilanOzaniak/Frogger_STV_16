import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();
    Car car1 = new Car(20, 20);
    Tree tree1 = new Tree(50, 100);
    Tree tree2 = new Tree(100, 100);
    Water water1 = new Water(350, 200);

    //prva travnata plocha
    Grass grass1 = new Grass(0, 720);
    Grass grass2 = new Grass(48, 720);
    Grass grass3 = new Grass(96, 720);
    Grass grass4 = new Grass(144, 720);
    Grass grass5 = new Grass(192, 720);
    Grass grass6 = new Grass(240, 720);
    Grass grass7 = new Grass(288, 720);
    Grass grass8 = new Grass(336, 720);
    Grass grass9 = new Grass(384, 720);
    Grass grass10 = new Grass(432, 720);
    Grass grass11 = new Grass(480, 720);
    Grass grass12 = new Grass(528, 720);
    Grass grass13 = new Grass(576, 720);

    Grass grass14 = new Grass(0, 768);
    Grass grass15 = new Grass(48, 768);
    Grass grass16 = new Grass(96, 768);
    Grass grass17 = new Grass(144, 768);
    Grass grass18 = new Grass(192, 768);
    Grass grass19 = new Grass(240, 768);
    Grass grass20 = new Grass(288, 768);
    Grass grass21 = new Grass(336, 768);
    Grass grass22 = new Grass(384, 768);
    Grass grass23 = new Grass(432, 768);
    Grass grass24 = new Grass(480, 768);
    Grass grass25 = new Grass(528, 768);
    Grass grass26 = new Grass(576, 768);


    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Car> cars = new ArrayList<Car>();
    ArrayList<Tree> trees = new ArrayList<Tree>();
    ArrayList<Water> water = new ArrayList<Water>();
    ArrayList<Grass> grass = new ArrayList<Grass>();



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
        grass.add(grass1);
        grass.add(grass2);
        grass.add(grass3);
        grass.add(grass4);
        grass.add(grass5);
        grass.add(grass6);
        grass.add(grass7);
        grass.add(grass8);
        grass.add(grass9);
        grass.add(grass10);
        grass.add(grass11);
        grass.add(grass12);
        grass.add(grass13);
        grass.add(grass14);
        grass.add(grass15);
        grass.add(grass16);
        grass.add(grass17);
        grass.add(grass18);
        grass.add(grass19);
        grass.add(grass20);
        grass.add(grass21);
        grass.add(grass22);
        grass.add(grass23);
        grass.add(grass24);
        grass.add(grass25);
        grass.add(grass26);


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

        for (int i = 0; i < water.size(); i++) {
            water.get(i).paintComponent(graphics2D);
        }

        graphics2D.dispose();

    }
}
