package pad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class SnakeGameMain extends Application {
    public static Stage stage;
    public static Scene menuScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon = new Image(Objects.requireNonNull(SnakeGameMain.class.getResource("/Images/icon.png")).openStream());
        primaryStage.getIcons().add(icon);

        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(SnakeGameMain.class.getResource("/FXMLfiles/Menu.fxml")));
        menuScene = new Scene(root);

        menuScene.getStylesheets().add("MenuStylesheet.css");
        primaryStage.setTitle("SnakeGame Menu");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> System.exit(0));


        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
    public static void showMenu(){
        stage.setScene(menuScene);
        stage.setTitle("Snake Game Menu");
    }

    public static void main(String[] args) {
        launch();
    }
}
