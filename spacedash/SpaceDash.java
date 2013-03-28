package spacedash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mobiles.Player;
import sun.applet.Main;

public class SpaceDash {

    JFrame gameWindow;
    JPanel gamePanel;
    BufferedImage frame;
    int FRAMES_PER_SECOND = 25;
    int lastKeys = -1;
    
    Player player;

    public SpaceDash() {
        // Set up game window
        setUpGameWindow();
        frame = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        
        // Set up mobiles
        player = new Player();

        // Enter game loop
        while (true) {
            try {
                Thread.sleep(1000 / FRAMES_PER_SECOND);
                updateGame();
                displayGame();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setUpGameWindow() {

        // Set up JFrame
        gameWindow = new JFrame("SpaceDash!");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        
        // Set up panel
        gamePanel = new JPanel();
        gamePanel.setSize(800, 600);
        
        gameWindow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lastKeys = evt.getKeyCode();
            }
        });

        gameWindow.add(gamePanel);
        gameWindow.setVisible(true);

    }

    public static void main(String[] args) {
        new SpaceDash();
    }

    private void updateGame() {
        if(lastKeys != -1){
            handleKeypress(lastKeys);
            lastKeys = -1;
        }
    }
    
    private void handleKeypress(int keys){
        System.out.println("Key pressed: " + keys);
        switch(keys){
            case Constants.KEY_RIGHT:
                player.setX(player.getX() + player.getSpeed());
                break;
            case Constants.KEY_LEFT:
                player.setX(player.getX() - player.getSpeed());
                break;
            case Constants.KEY_DOWN:
                player.setY(player.getY() + player.getSpeed());
                break;
            case Constants.KEY_UP:
                player.setY(player.getY() - player.getSpeed());
                break;
        }
    }

    private void displayGame() {
        // Get frame's graphics object
        Graphics g = frame.getGraphics();

        // Do background
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);

        // Do stars
        g.setColor(Color.white);
        int x, y;
        for (int i = 0; i < 50; i++) {
            x = randX();
            y = randY();
            g.drawLine(x, y, x, y + 15);
        }

        // Do player
        g.drawImage(player.getGraphic(), player.getX(), player.getY(), null);

        // Do asteroids

        // Do monsters

        // Do missiles


        // Paint frame to window
        gamePanel.getGraphics().drawImage(frame, 0, 0, null);
    }

    private int randX() {
        return (int) (Math.random() * 800);
    }

    private int randY() {
        return (int) (Math.random() * 600);
    }
}
