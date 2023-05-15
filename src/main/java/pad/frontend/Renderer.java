package pad.frontend;

import pad.SnakeGameMain;
import pad.backend.Field;
import pad.backend.Snake;
import pad.backend.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

/**
 * The Renderer class draws the elements of the snake game (snake, mouse, borders, score) to the canvas created in
 * the SnakeGame class.
 * The render() function is called in every iteration of the game loop, so it redraws the screen every time.
 */
public class Renderer {
    public static Image mouseImage;

    /**
     * The function for rendering the elements of the game using GraphicsContext.
     * @param field
     * The field parameter is used to determine the size of the Field that needs to be drawn, as well as accessing the
     * border and the snake
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to draw the elements of the game.
     * @throws Exception
     * The method renderMouseImageTile can throw an exception
     */
    public static void render(Field field, GraphicsContext gc) throws Exception {
        // First render the field, paint the background black
        Color darkGray = Color.web("#1a1a1a");
        gc.setFill(darkGray);
        gc.fillRect(0,0, field.getWidth(), field.getHeight());

        // Render the border
        Color grey = Color.web("#524F4F");
        gc.setFill(grey);
        field.getBorder().forEach(tile -> renderBorderTile(tile, gc));

        // Render bottom bar
        gc.setFill(darkGray);
        gc.fillRect(0, field.getHeight(), field.getWidth(), 50);

        //setting the image for the mouse
        renderMouseImageTile(field, gc);

        Snake snake = field.getSnake();
        // Render snake head
        renderSnakeHead(field, snake.getSnakeBody().get(0), gc);
        //render the snake
        setFillGradient(gc);
        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            renderSnakeTile(snake.getSnakeBody().get(i), gc);
        }

        // Render the score
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + field.getTotalScore(), 15, field.getHeight() + 33);
        gc.setFont(Font.font("Pixeboy", FontWeight.BOLD, 25));
    }

    /**
     * Renders a single tile for the snake's body
     * @param tile
     * The tile paramter is used to access the coordinates of the tile that's to be rendered.
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to draw the tile.
     */
    private static void renderSnakeTile(Tile tile, GraphicsContext gc) {
        gc.fillRoundRect(tile.x(), tile.y(), 25, 25, 15, 25);
    }

    /**
     * Renders a single border tile. Compared to the renderSnakeTile() method this one draws a rectangle.
     * @param tile
     * The tile paramter is used to access the coordinates of the tile that's to be rendered.
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to draw the tile.
     */
    private static void renderBorderTile(Tile tile, GraphicsContext gc) {
        gc.fillRect(tile.x(), tile.y(), 30, 30);
    }

    /**
     * Renders the image of the mouse. First it loads the image if it's not loaded yet.
     * @param field
     * The field parameter is used to determine the location where the mouse should be rendered.
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to draw the mouse image.
     * @throws Exception
     * In case the mouseImage file cannot be found the method throws an exception.
     */
    private static void renderMouseImageTile(Field field, GraphicsContext gc) throws Exception {
        // First checks if the image for the mouse has been loaded or not
        if (mouseImage == null) {
            // If not, loads the image
            mouseImage = new Image(Objects.requireNonNull(SnakeGameMain.class.getResource("/Images/mouse.png")).openStream(), 30, 30, false, false);
        }
        // Renders the image
        gc.drawImage(mouseImage, field.getMouseTile().x(), field.getMouseTile().y());
    }

    /**
     * Renders the snake head to have eyes, changes the fill depending on the direction of the snake
     * @param field
     * The field parameter is used to determine the direction of the snake.
     * @param tile
     * The tile parameter corresponds to the head of the snake, it is used to determine the location of the tile.
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to draw the snake's head.
     */
    private static void renderSnakeHead(Field field, Tile tile, GraphicsContext gc){
        setFillGradient(gc);
        gc.fillRoundRect(tile.x(), tile.y(), 25, 25, 15, 25);
        gc.setFill(Color.WHITE);
        switch (field.getSnake().getDirection()) {
            case LEFT -> {
                gc.fillRect(tile.x() + 6, tile.y() + 7, 3, 3);
                gc.fillRect(tile.x() + 6, tile.y() + 15, 3, 3);
            }
            case RIGHT -> {
                gc.fillRect(tile.x() + 19, tile.y() + 7, 3, 3);
                gc.fillRect(tile.x() + 19, tile.y() + 15, 3, 3);
            }
            case DOWN -> {
                gc.fillRect(tile.x() + 7, tile.y() + 19, 3, 3);
                gc.fillRect(tile.x() + 15, tile.y() + 19, 3, 3);
            }
            case UP -> {
                gc.fillRect(tile.x() + 7, tile.y() + 6, 3, 3);
                gc.fillRect(tile.x() + 15, tile.y() + 6, 3, 3);
            }
        }
    }

    /**
     * Uses the setFill() method to set the fill to a radial gradient which is used to draw the snake's body and head.
     * @param gc
     * The gc parameter is for the GraphicsContext, that is used to set the gradient.
     */
    public static void setFillGradient(GraphicsContext gc) {
        Stop[] stops1 = new Stop[] { new Stop(0, Color.web("#B5FBD2")),
                new Stop(1, Color.web("#242926"))};

        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true,
                CycleMethod.NO_CYCLE, stops1);

        gc.setFill(radialGradient);
    }
}
