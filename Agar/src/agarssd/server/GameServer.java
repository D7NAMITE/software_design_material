package agarssd.server;

import agarssd.model.Item;
import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameServer {

    public static final int PORT = 54555;
    public static final int TICK_DELAY = 20;

    private Server kryoServer = new Server();
    private World world = new World();
    private Map<Connection, Player> connectedPlayers =
            new HashMap<Connection, Player>();
    private boolean running;
    private int lastId = 0;

    public void start() {
        startServer();
        startMainLoop();
    }

    private void startMainLoop() {
        running = true;
        while(running) {
            world.tick();
            kryoServer.sendToAllTCP(world);
            try {
                Thread.sleep(TICK_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startServer() {
        kryoServer.getKryo().register(World.class);
        kryoServer.getKryo().register(Player.class);
        kryoServer.getKryo().register(Item.class);
        kryoServer.getKryo().register(MoveCommand.class);
        kryoServer.getKryo().register(java.util.List.class);
        kryoServer.getKryo().register(java.util.ArrayList.class);
        kryoServer.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(object instanceof MoveCommand) {
                    MoveCommand move = (MoveCommand) object;
                    Player p = connectedPlayers.get(connection);
                    if(p != null) {
                        int border = 2;
                        p.moving = true;
                        p.destinationX = Math.min(Math.max(border, move.toX), world.size - border);
                        p.destinationY = Math.min(Math.max(border, move.toY), world.size - border);
                    }
                }
            }

            public void connected(Connection connection) {
                Player p = new Player();
                p.id = ++lastId;
                p.randomPosition(0, 0, world.size, world.size);
                connectedPlayers.put(connection, p);
                world.addPlayer(p);
                connection.sendTCP(p);
            }

            public void disconnected(Connection connection) {
                Player p = connectedPlayers.remove(connection);
                if(p != null) {
                    world.removePlayer(p);
                }
            }
        });
        kryoServer.start();
        try {
            kryoServer.bind(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }
}
