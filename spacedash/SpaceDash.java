package spacedash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sun.applet.Main;

public class SpaceDash {
    
    JFrame gameWindow;
    JPanel gamePanel;
    BufferedImage frame;
    
    int FRAMES_PER_SECOND = 25;

    public SpaceDash() {
        // Set up game window
        setUpGameWindow();
        frame = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        
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
    
    private void setUpGameWindow(){
        // Set up JFrame
        gameWindow = new JFrame("SpaceDash!");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        
        // Set up panel
        gamePanel = new JPanel();
        gamePanel.setSize(800, 600);
        
        gameWindow.add(gamePanel);
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {
        new SpaceDash();
    }

    private void updateGame() {
        System.out.println("Updating game");
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
        for(int i = 0; i < 50; i++){
            x = randX();
            y = randY();
            g.drawLine(x, y, x, y+15);
        }
        
        // Do player
        
        // Do asteroids
        
        // Do monsters
        
        // Do missiles

        
        // Paint frame to window
        gamePanel.getGraphics().drawImage(frame, 0, 0, null);
    }
    
    private int randX(){
        return (int)(Math.random()*800);
    }
    
    private int randY(){
        return (int)(Math.random()*600);
    }
}
