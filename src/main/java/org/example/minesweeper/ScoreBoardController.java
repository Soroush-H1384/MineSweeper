package org.example.minesweeper;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreBoardController {
    @FXML
    private Button playAgainButton;

    private VBox mainVbox;
    private Stage stage;
    private int height;
    private int width;
    private int bomb;

    private HelloController helloController;


    public void playAgain(Event e){
        helloController.clearVisited();
        getHelloController().initialize();
        stage.close();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }
    public void setMainVbox(VBox mainVbox){
        this.mainVbox = mainVbox;
    }

    public HelloController getHelloController() {
        return helloController;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
