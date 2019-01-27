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

    public boolean addPlayer(Player p) {
        return players.add(p);
    }

    public boolean removePlayer(Player p) {
        return players.remove(p);
    }

    public void tick() {
        // Move Players
        for(Player p : players) {
            p.move();
        }
        // Detect Players X Items
        for(Player p : players) {
            for(Item i : items) {
                if(p.intersect(i)) {
                    i.randomPosition(0,0, size, size);
                }
            }
        }
        // TODO: Detect Players X Players
    }
}
