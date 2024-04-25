package agarssd.client;

import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;

import java.util.Random;

public class GameLogic {

    private Random random = new Random();
    private long lastCommand;

    public MoveCommand getNextMoveCommand(World world, Player myPlayer) {
        if(world == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - lastCommand;
        if(diff < 5000) {
            return null;
        }
        lastCommand = System.currentTimeMillis();
        MoveCommand command = new MoveCommand();
        command.toX = random.nextInt(world.size);
        command.toY = random.nextInt(world.size);
        return command;
    }
}
