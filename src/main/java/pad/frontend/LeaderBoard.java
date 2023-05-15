package pad.frontend;

import pad.SnakeGameMain;
import pad.backend.ScoreHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.Map;

public class LeaderBoard {

        public static Scene getLeaderScene (){

        GridPane leaderLayout = new GridPane();
        leaderLayout.setPrefSize(700, 700);
        leaderLayout.setAlignment(Pos.CENTER);
        leaderLayout.getRowConstraints().add(new RowConstraints(0));

            //Styling the background of the scene.
            leaderLayout.setStyle("-fx-background-color:" +
                    "            linear-gradient(#3a3838, #ababab)," +
                    "            linear-gradient(#000000, #5b5550)," +
                    "            linear-gradient(from 0% 0% to 30% 50%, rgba(255, 255, 255, 0.9), rgba(0, 0, 0, 0));");


            //Creating a ScoreHandler in order to obtain a list of the highest results scored by users.
            ScoreHandler handler = new ScoreHandler();
            List<Map<String, Object>> LeaderList = handler.getHighScoreList(13);

            /*
             * For loop in order to display the highest scores, from the highest to the lowest.
             * Max. 13 results, or fewer, as obtained in the LeaderList, depending on the amount of saved scores.
             */
            for( int i = 0; i < LeaderList.size(); i++){
                String player = (String) LeaderList.get(i).get("name");
                int score = (Integer) LeaderList.get(i).get("score");

                /**
                 * Obtaining the position of the score in the list to display its position within all the results
                 * by adding 1 to i variable (there can be no score at #0). New integer since 1 has to be added to get
                 * an actual position of the result. It also allows to display just the saved scores instead of showing
                 * 13 but with some of them empty.
                 */
                int j=i+1;
                String display = ("#" + j +" " + player + ", " + score);
                Label result = new Label(display);
                result.setPrefSize(300, 35);
                leaderLayout.getChildren().addAll(result);

                GridPane.setConstraints(result, 1, j);
                leaderLayout.getRowConstraints().add(new RowConstraints(45));

                //Styling of one single result displayed.
                result.setStyle("-fx-background-color: #000000; " +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 30px;" +
                        "-fx-padding: 5 10 5 10; " +
                        "-fx-border-width: 3px;" +
                        "-fx-border-color: #83B799;" +
                        "-fx-font-family:'Pixeboy';" +
                        "src: url('Pixeboy-z8XGD.ttf');" +
                        "-fx-text-alignment: center;");
            }

            //Creating a "Back to Menu" button.
            Button backToMenu = new Button("Back To Menu");

            GridPane.setColumnIndex(backToMenu, 2);
            leaderLayout.getColumnConstraints().add(new ColumnConstraints(150));
            GridPane.setRowIndex(backToMenu, 14);

            backToMenu.setOnMousePressed(e -> {
                        try {
                            SnakeGameMain.showMenu();
                        } catch (Exception e1) {
                            System.out.println("Please try again later");
                        }
                    }
            );

            //Styling the "Back to Menu" button.
            backToMenu.setStyle(
                    "-fx-background-color:" +
                    "   linear-gradient(#83B799, #83B799)," +
                    "   linear-gradient(#83B799, #83B799)," +
                    "   linear-gradient(#83B799, #83B799)," +
                    "   linear-gradient(#000000 0%, #000000 50%, #050505 100%)," +
                    "   linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 30;" +
                    "    -fx-background-insets: 0,1,2,3,0;" +
                    "    -fx-text-fill: White;" +
                    "    -fx-font-weight: bold;" +
                    "    -fx-font-size: 20px;" +
                    "    -fx-padding: 15 20 15 20;" +
                    "    -fx-font-family: 'Pixeboy';" +
                    "    src: url('Pixeboy-z8XGD.ttf');");

            leaderLayout.getChildren().add(backToMenu);

        return new Scene(leaderLayout);
        }




}