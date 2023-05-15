package pad.frontend;

import pad.SnakeGameMain;
import pad.backend.Field;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

/**
 * The GameLoop class contains the game loop, which is an infinite loop that runs continuously as long as the game
 * is running. It implements the Runnable interface which allows it to be executed by a thread.
 */

public class GameLoop implements Runnable {
    // value determining the number of updates per second (UPS)
    public static final int SPEED = 10;
    private final SnakeGame game;
    private final Field field;
    private final GraphicsContext context;
    // Time it should take to update and render if we want to have SPEED number of UPS
    private final double optimalUpdateTime;
    private boolean paused;
    private boolean keyDown;

    public GameLoop(SnakeGame game, Field field, GraphicsContext context) {
        this.game = game;
        this.field = field;
        this.context = context;
        this.optimalUpdateTime = (double) 1000 / SPEED;
        this.paused = false;
        this.keyDown = false;
    }

    /**
     * The run() method is from the Runnable interface. It contains the game loop.
     * The most important processes that occur in the game loop are checking if the game should be over, update, and
     * render. Each iteration of the loop takes a predefined amount of time, which makes the snake move at an even speed.
     * This is achieved by keeping track of the time it took to complete all tasks in an iteration and then comparing that
     * to the optimal time an iteration should take, calculating the difference, and waiting for that amount of time before
     * starting the next iteration.
     */
    @Override
    public void run() {
        while(!paused) {
            // Check the current time when starting the loop
            double timeAtStart = System.currentTimeMillis();

            // Check if the loop should be interrupted because the player died
            try {
                if (this.field.gameOver()) {
                    paused = true;
                    // if the game is over, it will pop up game over text
                    Platform.runLater(() -> {
                        try {
                            SnakeGameMain.stage.setScene(GameOverController.getScene(field, game));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    break;
                }
                field.update();
                Renderer.render(field, context);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Check how long it took to update

            // The time it actually took for the loop to update and render
            double updateTime = System.currentTimeMillis() - timeAtStart;
            // If it took less time than the optimal update time, check the difference, and wait for that amount of time
            long waitTime = (long) (optimalUpdateTime - updateTime);
            if (updateTime < optimalUpdateTime) {
                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            keyDown = false;
        }
    }

    public boolean isPaused(){
        return this.paused;
    }
    public void setPaused(boolean value) {
        this.paused = value;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public void setKeyDown() {
        if (!this.isPaused()) {
            keyDown = true;
        }
    }

}
