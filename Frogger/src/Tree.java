import javax.swing.*;
import java.awt.*;

public class Tree {
    Image treeImage;
    Rectangle treeRectangle;

    public Tree(int x, int y) {
        treeRectangle = new Rectangle(x, y, 16 * 3, 16 * 3);

        // nacitanie obrazkov
        treeImage = new ImageIcon("./img/tree.png").getImage();
    }

    public boolean hasCollided(Rectangle player) {
        if(treeRectangle.intersects(player)) {
            return true;
        }
        return false;
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(treeImage, treeRectangle.x, treeRectangle.y, 16 * 3, 16 * 3, null);
    }


}