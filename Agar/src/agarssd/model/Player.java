package agarssd.model;

/**
 * A player who plays the game.
 */
public class Player extends WorldObject{

    public static final int SIZE = 10;

    // Properties need to be public for Kryo serialization
    public int id;
    public float destinationX;
    public float destinationY;
    public boolean moving;
    public boolean alive;

    public Player() {
        alive = true;
        size = SIZE;
    }

    public void move() {
        if(!moving) {
            return;
        }
        float distance = distance(destinationX, destinationY);

        // Prevent bouncing
        if(distance < 1) {
            return;
        }

        positionX += (destinationX - positionX) / distance;
        positionY += (destinationY - positionY) / distance;
    }

    public void die(){
        alive = false;
    }
}
