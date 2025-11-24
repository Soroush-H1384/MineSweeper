package org.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HelloController {
    @FXML
    private BorderPane mainPage;

    @FXML
    private VBox mainVBox;

    @FXML
    private Label scoreBoardOne;
    @FXML
    private Label scoreBoardTwo;
    @FXML
    private Label timeLabel;

    @FXML
    private Button timeButton;

    private Game game = new Game(5, 8);
    private int Bomb = 0;
    private int empty = 0;
    private int count = 0;
    boolean isTiming = false;
    private Timeline timeLine;

    public void initialize() {

        game.makeBombArray();
        for (int i = 0; i < game.getHeight(); i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < game.getHeight(); j++) {
                Button button = new Button();

                final int row = i;
                final int column = j;
                button.getStyleClass().addAll("block", "unselected-block");
                button.setOnAction(e -> {
                    if (game.getBombArray()[row][column] == 1) {
                        button.getStyleClass().remove("unselected-block");
                        button.getStyleClass().add("bombing-block");
                        scoreBoardOne.setText(Integer.toString(++Bomb));

                    } else {
                        button.getStyleClass().remove("unselected-block");
                        button.getStyleClass().add("empty-block");
                        scoreBoardTwo.setText(Integer.toString(++empty));
                    }
                });
                hBox.getChildren().add(button);

            }
            mainVBox.getChildren().add(hBox);
        }
    }

    public void startTime(){
        isTiming = !isTiming;
        if (timeLine == null){
            timeLine = new Timeline(
                    new KeyFrame(Duration.seconds(1),event -> {
                        count++;
                        timeLabel.setText(Integer.toString(count));
                    })
            );
        }
            timeLine.setCycleCount(Timeline.INDEFINITE);
        if (isTiming == true){
            timeLine.play();
            timeButton.setText("Stop");

        }
        else{
            timeLine.pause();
            timeButton.setText("Start");
        }

    }


}