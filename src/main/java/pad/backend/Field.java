package pad.backend;

//import javafx.scene.media.MediaView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
    private final int TILE_SIZE;
    private final Random random;
    private final int width;
    private final int height;
    private final List<Tile> border;

    private Mouse mouse;

    private final Snake snake;
    private int score;

//    private MediaView gameOverMediaView;



    public Field(){
        this.TILE_SIZE = 25;
        this.random = new Random();
        this.width = 800;
        this.height = 650;
        this.border = this.generateBorder();
        //The snake starts off with a body of 3 tiles.
        Tile[] initialTiles = new Tile[]{new Tile(350, 350), new Tile(375, 350), new Tile(400, 350)};
        this.snake = new Snake(initialTiles);
        //The score starts at 0.
        this.score = 0;
        //Once the game starts, a mouse is spawned.
        this.spawnMouse();
    }

    /*
     *Creating a border that wraps around the game field
     */
    private List<Tile> generateBorder() {
        List<Tile> border = new ArrayList<>();
        // First create the border on top
        for (int i = 0; i < this.width ; i+=25) {
            border.add(new Tile(i, 0));
        }
        // Create border at the bottom
        for (int i = 0; i < this.width ; i+=25) {
            border.add(new Tile(i, this.height - 25));
        }
        // Create border on the left
        for (int i = 0; i < this.height ; i+=25) {
            Tile tile = new Tile(0, i);
            if (!border.contains(tile)) {
                border.add(tile);
            }
        }
        // Create border on right
        for (int i = 0; i < this.height ; i+=25) {
            Tile tile = new Tile(this.width - 25, i);
            if (!border.contains(tile)) {
                border.add(tile);
            }
        }
        return border;
    }


    /*
     * First check the direction of the snake,
     * then add a tile to in front of the first tile of the snake (head tile)
     * and remove the last tile of the snake tail.
     */
    public void moveSnake(){
        if (snake.getDirection() != null){
            Tile tile;
            if (snake.getDirection().equals(Direction.UP)){
                tile = new Tile(snake.getSnakeBody().get(0).x(), snake.getSnakeBody().get(0).y() - TILE_SIZE);
            } else if (snake.getDirection().equals(Direction.DOWN)) {
                tile = new Tile(snake.getSnakeBody().get(0).x(), snake.getSnakeBody().get(0).y() + TILE_SIZE);
            } else if (snake.getDirection().equals(Direction.RIGHT)) {
                tile = new Tile(snake.getSnakeBody().get(0).x() + TILE_SIZE, snake.getSnakeBody().get(0).y());
            } else {
                tile = new Tile(snake.getSnakeBody().get(0).x() - TILE_SIZE, snake.getSnakeBody().get(0).y());
            }
            snake.getSnakeBody().remove(snake.getSnakeBody().get(snake.getSnakeBody().size()-1));
            snake.getSnakeBody().add(0, tile);

        }
    }

    /*
     * The up(), down(), right(), left() methods make it impossible to move the snake
     * the opposite of its current direction.
     */
    public void up(){
        if(snake.getDirection() != Direction.DOWN){
            snake.setDirection(Direction.UP);
        }
    }

    public void down(){
        if(snake.getDirection() != Direction.UP){
            snake.setDirection(Direction.DOWN);
        }
    }

    public void right(){
        if(snake.getDirection() != Direction.LEFT){
            snake.setDirection(Direction.RIGHT);
        }
    }

    public void left(){
        if(snake.getDirection() != Direction.RIGHT){
            snake.setDirection(Direction.LEFT);
        }
    }

    /*
     * This method spawns a mouse at a random position in the field.
     * The mouse can not pe spawned at the same position as the snake.
     */
    public void spawnMouse() {
        //If mouse spawn position is at snake position then redo the process until you find a position not on the snake
        Tile tile;
        do {
            int x = random.nextInt(this.width / TILE_SIZE) * TILE_SIZE;
            int y = random.nextInt(this.height / TILE_SIZE) * TILE_SIZE;
            tile = new Tile(x, y);
        } while (this.snake.getSnakeBody().contains(tile) || this.border.contains(tile));
        this.mouse = new Mouse(tile);
    }

    /*
     * The growth() function adds a tile to the snake body
     * at the position of the last tile of the tail.
     */
    public void grow()  {
        Tile tile;
        tile = new Tile(snake.getSnakeBody().get(snake.getSnakeBody().size()-1).x(), snake.getSnakeBody().get(snake.getSnakeBody().size()-1).y());
        snake.getSnakeBody().add(snake.getSnakeBody().size()-1, tile);
    }
    /*
     * If the snake head and the mouse are at the same position,
     * the snake "eats" the mouse, grows, another mouse is spawned
     * and the score is increased by one point.
     */
    public void eatMouse() throws URISyntaxException {
        if (snake.getSnakeBody().get(0).equals(mouse.tile())) {
            this.grow();
            spawnMouse();
            score++;
        }
    }

    public void update() throws URISyntaxException {
        // Move the snake by one tile
        moveSnake();
        // Check if snake ate mouse
        eatMouse();
        // Check whether game is over
        gameOver();
    }

    /*
     * The game is over once the snake collides with the borders or with itself.
     */
    public boolean gameOver() throws URISyntaxException {



        // Check if the head of the snake collide with the border, if yes return true.
        for (Tile tile : this.border) {
            if (tile.equals(snake.getSnakeBody().get(0))) {
                return true;
            }
        }
        // Check if the head of the snake collide with the snake body, if yes return true.
        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            if (snake.getSnakeBody().get(0).equals(snake.getSnakeBody().get(i))) {
                return true;
            }
        }
        return false;
    }

    public Tile getMouseTile() {
        return this.mouse.tile();
    }

    public Snake getSnake() {
        return this.snake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Tile> getBorder() {
        return border;
    }

    public int getTotalScore(){
        return score;
    }
}

