package pad.frontend;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import pad.SnakeGameMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class AboutMenuController {
    public Button about_btn;
    public ImageView img1;

    // Creates and returns a new scene
    public static Scene getScene() throws Exception {
        //Loading the fxml file that has the design of the scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HowToPlayController.class.getResource("/FXMLfiles/About.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }

    //If the back to menu button is clicked, goes back to the menu
    public void backToMenuButtonClicked(){
        SnakeGameMain.showMenu();
    }
}
