package agarssd.model;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static final int SIZE = 500;
    public static final int ITEM_COUNT = 30;

    // Properties need to be public for Kryo serialization
    public List<Player> players = new ArrayList<Player>();
    public List<Item> items = new ArrayList<Item>();
    public int size = SIZE;

    public World() {
        initItems();
    }

    public boolean addPlayer(Player p) {
        return players.add(p);
    }

    public boolean removePlayer(Player p) {
        return players.remove(p);
    }

    public void tick() {
        movePlayers();
        checkPlayersItemsCollisions();
        checkPlayersPlayersCollisions();
    }

    private void initItems() {
        for(int i = 0; i < ITEM_COUNT; i++) {
            Item item = new Item();
            item.randomPosition(0, 0, size, size);
            items.add(item);
        }
    }

    private void movePlayers() {
        for(Player p : players) {
            p.move();
        }
    }

    private void checkPlayersItemsCollisions() {
        for(Player p : players) {
            for(Item i : items) {
                if(p.intersect(i)) {
                    i.randomPosition(0,0, size, size);
                    p.size = Math.min(size/2, p.size + 1);
                }
            }
        }
    }

    private void checkPlayersPlayersCollisions() {
        for(Player p1 : players) {
            if(!p1.alive) {
                continue;
            }
            for(Player p2 : players) {
                if(p1 == p2) {
                    continue;
                }
                if(!p2.alive) {
                    continue;
                }
                if(!p1.intersect(p2)) {
                    continue;
                }
                // Hit
                if(p1.largerThan(p2)) {
                    p1.size = Math.min(size/2, p1.size + (int)Math.sqrt(p2.size));
                    p2.die();
                } else if(p2.largerThan(p1)) {
                    p2.size = Math.min(size/2, p2.size + (int)Math.sqrt(p1.size));
                    p1.die();
                } else {
                    p1.die();
                    p2.die();
                }
            }
        }
    }
}
