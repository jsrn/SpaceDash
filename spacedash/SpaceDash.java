package spacedash;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sun.applet.Main;

public class SpaceDash {
    
    JFrame gameWindow;

    public SpaceDash() {
        // Set up game window
        setUpGameWindow();
        
        // Enter game loop
        while (true) {
            try {
                Thread.sleep(1000);
                updateGame();
                displayGame();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setUpGameWindow(){
        gameWindow = new JFrame("SpaceDash!");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {
        new SpaceDash();
    }

    private void updateGame() {
        System.out.println("Updating game");
    }

    private void displayGame() {
        System.out.println("Displaying game");
    }
}
