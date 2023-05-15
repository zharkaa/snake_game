package pad.frontend;

import pad.Main;
import pad.SnakeGameMain;
import pad.backend.Field;
import pad.backend.ScoreHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * The SnakeGame class contains the main snake game. It includes the GameLoop, the Field as well as the ScoreHandler
 * via composition. It has methods for creating a snake game scene, resetting the game, and it also handles keypresses
 * via an event listener.
 */

public class SnakeGame {
    private GraphicsContext context;
    private final Font font;
    private GameLoop loop;
    private Field field;

    private Canvas canvas;
    private final ScoreHandler scoreHandler;

    public SnakeGame(){
        this.scoreHandler = new ScoreHandler();
        String fName = "/Pixeboy-z8XGD.ttf";
        InputStream is = Main.class.getResourceAsStream(fName);
        font = Font.loadFont(is,12);
    }

    /**
     * The getScene method creates the scene for the game, adds a back button, loads the css for styling, and finally
     * returns the scene.
     */
    public Scene getScene(){

        //Back to Menu button at the bottom of the screen
        Button backToMenu = new Button("Back to Menu");
        backToMenu.setTranslateX(660);
        backToMenu.setTranslateY(660);
        backToMenu.setFocusTraversable(false);
        backToMenu.setOnMousePressed(click -> {
            loop.setPaused(true);
            SnakeGameMain.showMenu();
        });

        Group root = new Group();

        canvas = new Canvas(800, 700);
        context = canvas.getGraphicsContext2D();
        context.setFont(font);
        root.getChildren().add(canvas);
        root.getChildren().add(backToMenu);

        Scene snakeGameScene = new Scene(root);

        // Set css file for the scene
        snakeGameScene.getStylesheets().add("SnakeGameStylesheet.css");

        return snakeGameScene;
    }

    /**
     * Resets the game, discards old Field and Loop, creates new ones, and renders changes
     * @throws Exception
     * The method render() from the Renderer can throw an exception
     */

    public void reset() throws Exception {
        field = new Field();
        loop = new GameLoop(this, field, context);
        Renderer.render(field, context);
    }

    /**
     * Resets the game, starts event listener and starts the GameLoop
     * @throws Exception
     * The reset() method can throw an exception.
     */
    public void startGame() throws Exception {
        this.reset();
        this.startEventListener();
        (new Thread(loop)).start();
    }

    /**
     * Starts the event listener for keyboard control
     */
    public void startEventListener(){
        this.canvas.setFocusTraversable(true);
        this.canvas.setOnKeyPressed(this::handleEvent);
    }

    /**
     * Handles keypress events to control the movement of the snake
     * @param e
     * The parameter e is the KeyEvent that's passed in, and is used to determine which key has been pressed.
     */
    public void handleEvent(KeyEvent e) {
        if (loop.isKeyDown()) {
            return;
        }
        switch (e.getCode()) {
            case UP, W -> field.up();
            case DOWN, S -> field.down();
            case LEFT, A -> field.left();
            case RIGHT, D -> field.right();
            case ENTER -> {
                if (loop.isPaused()) {
                    try {
                        reset();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        this.startGame();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case ESCAPE -> {
                loop.setPaused(true);
                SnakeGameMain.showMenu();
            }
        }
        loop.setKeyDown();
    }

    public ScoreHandler getScoreHandler() {
        return this.scoreHandler;
    }

    public GameLoop getLoop() {
        return loop;
    }


}
