package agarssd.model;

import java.util.Random;

//NOTE: Don't touch this class
public abstract class WorldObject {

    // Properties need to be public for Kryo serialization
    public float positionX;
    public float positionY;
    public float size;

    public boolean intersect(WorldObject another) {
        return distance(another) < size + another.size;
    }

    public float distance(WorldObject another) {
        return distance(another.positionX, another.positionY);
    }

    public float distance(float x, float y) {
        float diffX = positionX - x;
        float diffY = positionY - y;
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
