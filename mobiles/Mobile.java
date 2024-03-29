package mobiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spacedash.SpaceDash;

public class Mobile {

    private int x, y, w, h;
    private int speed;
    private BufferedImage graphic;
    private double rateOfFire = 0.5;
    private long lastFired;

    public Mobile() {
        lastFired = (int) (System.currentTimeMillis() / 1000L);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }
    
    public void setRateOfFire(double r) {
        rateOfFire = r;
    }

    public long getLastFiredTime() {
        return lastFired;
    }

    public void setLastFiredTime(long t) {
        lastFired = t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }
    
    public boolean checkCollision(int missileX, int missileY){
        int lowX = x;
        int lowY = y;
        int highX = x + w;
        int highY = y + h;
        
        boolean withinX = missileX > lowX && missileX < highX;
        boolean withinY = missileY > lowY && missileY < highY;
        if(withinX && withinY ){
            return true;
        }
        return false;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public BufferedImage getGraphic() {
        return graphic;
    }

    public void setGraphic(String file) {
        try {
            graphic = ImageIO.read(new File("src/art/" + file));
        } catch (IOException ex) {
            Logger.getLogger(SpaceDash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
