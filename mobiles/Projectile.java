package mobiles;

public class Projectile extends Mobile {
    
    int bearing, damage;
    
    public Projectile(int bearing, int damage, String image){
        this.bearing = bearing;
        this.damage = damage;
        setGraphic(image);
    }
    
    public int getBearing(){
        return bearing;
    }
    
}
