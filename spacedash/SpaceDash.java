package spacedash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mobiles.Grunt;
import mobiles.Player;
import mobiles.Projectile;
import sun.applet.Main;

public class SpaceDash {

    JFrame gameWindow;
    JPanel gamePanel;
    BufferedImage frame;
    int lastKeys = -1;
    
    Player player;
    LinkedList<Projectile> projectiles;
    LinkedList<Grunt> grunts;

    public SpaceDash() {
        // Set up game window
        setUpGameWindow();
        frame = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        
        // Set up mobiles
        player = new Player();
        projectiles = new LinkedList();
        grunts = new LinkedList();
        
        Grunt g = new Grunt();
        g.setX(400);
        g.setY(20);
        
        grunts.add(g);

        // Enter game loop
        while (true) {
            try {
                Thread.sleep(1000 / Constants.FRAMES_PER_SECOND);
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
        // Handle keypresses
        if(lastKeys != -1){
            handleKeypress(lastKeys);
            lastKeys = -1;
        }
        
        // Progress projectiles
        for (Iterator<Projectile> it = projectiles.iterator(); it.hasNext();) {
            Projectile projectile = it.next();
            int currentX = projectile.getX();
            int currentY = projectile.getY();
            int bearing = projectile.getBearing();
            
            
            float dirX = (float) Math.cos(Math.toRadians(bearing));
            float dirY = (float) Math.sin(Math.toRadians(bearing));
            
            float yDiff = dirY * Constants.PLAYER_MISSILE_SPEED;
            float xDiff = dirX * Constants.PLAYER_MISSILE_SPEED;
            
            projectile.setY(currentY - (int)yDiff);
            projectile.setX(currentX + (int)xDiff);
        
        }
    }
    
    private void handleKeypress(int keys){
        //System.out.println("Key pressed: " + keys);
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
            case Constants.KEY_SPACE:
                Projectile p = new Projectile(90, 1, "projectile1.png");
                p.setX(player.getX());
                p.setY(player.getY());
                projectiles.add(p);
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
        for (Iterator<Grunt> it = grunts.iterator(); it.hasNext();) {
            Grunt grunt = it.next();
            g.drawImage(grunt.getGraphic(), grunt.getX(), grunt.getY(), null);
        }

        // Do missiles
        for (Iterator<Projectile> it = projectiles.iterator(); it.hasNext();) {
            Projectile projectile = it.next();
            g.drawImage(projectile.getGraphic(), projectile.getX(), projectile.getY(), null);
        }

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
