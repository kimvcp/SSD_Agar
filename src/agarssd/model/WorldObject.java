package agarssd.model;

import java.util.Random;

public abstract class WorldObject {

    // Properties need to be public for Kryo serialization
    public float positionX;
    public float positionY;
    public float size;

    public boolean intersect(WorldObject another) {
        return distance(another) < size + another.size;
    }

    public float distance(WorldObject another) {
        float diffX = positionX - another.positionX;
        float diffY = positionY - another.positionY;
        return (float) Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public boolean largerThan(WorldObject another) {
        return size > another.size;
    }

    public void randomPosition(float minX, float minY, float maxX, float maxY) {
        Random r = new Random();
        positionX = (int) minX + r.nextInt((int) (maxX - minX));
        positionY = (int) minY + r.nextInt((int) (maxY - minY));
    }

}
