package agarssd.model;

import java.util.ArrayList;
import java.util.List;

public class World {

    // Properties need to be public for Kryo serialization

    public List<Player> players;
    public List<Item> items;
    public int size;

    public World(int size) {
        this.size = size;
        this.players = new ArrayList<Player>();
        this.items = new ArrayList<Item>();
    }
}
