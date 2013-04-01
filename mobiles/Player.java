package mobiles;

public class Player extends Mobile{
    
    public Player(){
        super();
        
        setGraphic("player.png");
        setSize(50,50);
        setSpeed(25);
        setX(400);
        setY(500);
        setRateOfFire(500);
        
    }
    
}
