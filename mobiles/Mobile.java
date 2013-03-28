package mobiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spacedash.SpaceDash;

public class Mobile {
    private int x,y,w,h;
    private int speed;
    private BufferedImage graphic;
    
    
    public int getSpeed(){
        return speed;
    }
    
    public void setSpeed(int s){
        speed = s;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
    }
    
    public void setX(int newX){
        x = newX;
    }
    
    public void setY(int newY){
        y = newY;
    }
    
    public BufferedImage getGraphic(){
        return graphic;
    }
    
    public void setGraphic(String file){
        try {
            graphic = ImageIO.read(new File("src/art/" + file));
        } catch (IOException ex) {
            Logger.getLogger(SpaceDash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
