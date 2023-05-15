package pad.backend;

import java.util.ArrayList;
import java.util.Arrays;

    /*
     * The snake is a collection of tiles that moves around in the field and eats food.
     * The snake has a direction (up, down, right, left).
     */

public class Snake{

    private final ArrayList <Tile> snakeBody;
    private Direction direction;

    /*
     * The snake starts with a body of three tiles in the middle of the field.
     * The snake starts by going left.
     */
    public Snake(Tile[] initialTiles) {
        this.snakeBody = new ArrayList<>();
        this.snakeBody.addAll(Arrays.asList(initialTiles));
        this.direction = Direction.LEFT;
    }

    public ArrayList<Tile> getSnakeBody() {
        return snakeBody;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
