package org.example.minesweeper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {

    @FXML
    private Button playGameButton;

    @FXML
    private Button playOnlineButton;

    @FXML
    private Button exitButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void goGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        LevelPageController levelPageController = fxmlLoader.getController();
        levelPageController.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
    public void goOnlineGame(){

    }
    public void exit(){
        stage.close();
    }

}
