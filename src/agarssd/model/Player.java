package agarssd.model;

public class Player {

    public static final float SPEED = 1;

    // Properties need to be public for Kryo serialization
    public String name;
    public float positionX;
    public float positionY;
    public float destinationX;
    public float destinationY;

    public Player(String name, float x, float y) {
        this.name = name;
        this.positionX = x;
        this.destinationX = x;
        this.positionY = y;
        this.destinationY = y;
    }
}
