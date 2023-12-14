import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton buttonPlay;
    private JLabel labelPanel;

    public Menu() {

        this.setTitle("Frogger");
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 300));
        contentPane.setBackground(new Color(192, 192, 192));

        this.labelPanel = new JLabel();
        this.labelPanel.setBounds(0, 0, 500, 300);
        this.labelPanel.setIcon(new ImageIcon("./img/menu.png"));
        this.labelPanel.setEnabled(true);
        this.labelPanel.setVisible(true);

        this.buttonPlay = new JButton();
        this.buttonPlay.setBounds(175, 130, 150, 40);
        this.buttonPlay.setText("PLAY");
        this.buttonPlay.setEnabled(true);
        this.buttonPlay.addActionListener(new PlayButtonActionListener());
        this.buttonPlay.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(this.buttonPlay);
        contentPane.add(this.labelPanel);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private class PlayButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame window = new JFrame();
            GamePanel gamePanel = new GamePanel();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Frogger");
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();

            dispose();
        }
    }

}
