package pad.frontend;

import pad.SnakeGameMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
//import javafx.scene.image.Image;
//import javafx.scene.media.AudioClip;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//
//import java.net.URISyntaxException;
//import java.util.Objects;


public class MenuController {

    @FXML
    private ImageView musicImageView;

    SnakeGame game = new SnakeGame();
    private MediaView mediaView;

    public MenuController(){
    }


    public void startGame() throws Exception {
        SnakeGameMain.stage.setScene(game.getScene());
        SnakeGameMain.stage.setTitle("Snake Game");
        game.startGame();
    }

    public void leaderBoard(){
        SnakeGameMain.stage.setScene(LeaderBoard.getLeaderScene());
        SnakeGameMain.stage.setTitle("Leader Board");
    }

    public void aboutMenu() {
        try {
            SnakeGameMain.stage.setScene(AboutMenuController.getScene());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  void exitGame(){
        System.exit(0);

    }

    public void howToPlay() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HowToPlayController.class.getResource("/FXMLfiles/HowToPlay.fxml"));
        Parent root = loader.load();
        Scene howToPlayScene = new Scene(root);
        SnakeGameMain.stage.setScene(howToPlayScene);
        SnakeGameMain.stage.setTitle("How To Play");

    }
}
