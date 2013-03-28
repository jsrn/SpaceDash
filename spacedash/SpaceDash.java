package spacedash;

import java.util.logging.Level;
import java.util.logging.Logger;
import sun.applet.Main;

public class SpaceDash {

    public SpaceDash() {
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
