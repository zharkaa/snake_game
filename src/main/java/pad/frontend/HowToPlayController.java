package pad.frontend;

import pad.SnakeGameMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class HowToPlayController {
    // Creates and returns a new scene
    public static Scene getScene() throws Exception {
        //Loading the fxml file that has the design of the scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HowToPlayController.class.getResource("/FXMLfiles/HowToPlay.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }

    //If the back to menu button is clicked, goes back to the menu
    public void backToMenuButtonClicked(){
        SnakeGameMain.showMenu();
    }
}
