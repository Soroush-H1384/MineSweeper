package org.example.minesweeper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LevelPageController {
    @FXML
    private Button easyLevelButton;

    @FXML
    private Button normalLevelButton;

    @FXML
    private Button hardLevelButton;


    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private int height;
    private int width;
    private int bomb;


    public void goGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        HelloController helloController = new HelloController(height,width,bomb);
        fxmlLoader.setController(helloController);
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        helloController.setStage(stage);

        stage.setScene(scene);
        stage.show();


    }

    public void easyLevel() throws IOException {
        height = 8;
        width = 8;
        bomb = 6;
        goGame();
    }

    public void normalLevel() throws IOException {
        height = 10;
        width = 10;
        bomb = 20;
        goGame();
    }

    public void hardLevel() throws IOException {
        height = 15;
        width = 15;
        bomb = 25;
        goGame();
    }
}
