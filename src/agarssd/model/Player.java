package agarssd.model;

//NOTE: Don't touch this class
public class Player extends WorldObject{

    public static final int DEFAULT_SIZE = 10;
    public static final float SPEED = 2;

    // Properties need to be public for Kryo serialization
    public String name;
    public float destinationX;
    public float destinationY;
    public boolean moving;

    public Player() {
        size = DEFAULT_SIZE;
    }

    public void move() {
        if(!moving) {
            return;
        }
        float distance = distance(destinationX, destinationY);
        positionX += (destinationX - positionX) / distance;
        positionY += (destinationY - positionY) / distance;
    }
}
