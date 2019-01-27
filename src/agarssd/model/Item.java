package agarssd.model;

public class Item {

    // Properties need to be public for Kryo serialization
    public float positionX;
    public float positionY;

    public Item(float x, float y) {
        this.positionX = x;
        this.positionY = y;
    }
}
