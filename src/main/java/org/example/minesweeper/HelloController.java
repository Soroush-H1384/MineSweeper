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
import java.util.List;

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

    private Game game = new Game(9, 10);
    private int Bomb = 0;
    private int empty = 0;
    private int count = 0;
    private boolean isTiming = false;
    private Timeline timeLine;
    private Button[][] buttons = new Button[game.getHeight()][game.getHeight()];
    private boolean[][] visited = new boolean[game.getHeight()][game.getHeight()];


    public void initialize() {

        game.makeBombArray();
        for (int i = 0; i < game.getHeight(); i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < game.getHeight(); j++) {
                Button button = new Button();
                button.getStyleClass().addAll("block", "unselected-block");
                buttons[i][j] = button;
                visited[i][j] = false;
                int row = i;
                int column = j;
                buttons[i][j].setOnAction(e -> {
                    if (game.getBombArray()[row][column] == 9) {
                        button.getStyleClass().remove("unselected-block");
                        button.getStyleClass().add("bombing-block");
                        scoreBoardOne.setText(Integer.toString(++Bomb));

                    } else {
                        button.getStyleClass().remove("unselected-block");
                        button.getStyleClass().add("empty-block");
                        scoreBoardTwo.setText(Integer.toString(++empty));
                        button.setText(Integer.toString(game.getBombArray()[row][column]));
                        checkAround(row,column);
                    }
                });
                hBox.getChildren().add(button);

            }
            mainVBox.getChildren().add(hBox);
        }
    }

    public void checkAround(int row,int column){
        for (int k=-1;k< 2;k++){
            for (int l=-1;l<2;l++){
                if (k==0 && l==0){
                    continue;
                }
                if (row + k < 0 || column + l >= game.getHeight() || column + l < 0 || row + k >= game.getHeight()) {
                    continue;
                } else if (game.getBombArray()[row+k][column+l]==0 && !visited[row+k][column+l]){
                    buttons[row+k][column+l].getStyleClass().addAll("empty-block");
                    visited[row+k][column+l] = true;
                    checkAround(row + k, column + l);

                }
                else if(game.getBombArray()[row+k][column+l]!=9 && !visited[row+k][column+l]){
                    buttons[row+k][column+l].setText(Integer.toString(game.getBombArray()[row+k][column+l]));
                    buttons[row+k][column+l].getStyleClass().addAll("empty-block");
                    visited[row+k][column+l] = true;
                }
            }
        }
    }

    public void startTime() {
        isTiming = !isTiming;
        if (timeLine == null) {
            timeLine = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        count++;
                        timeLabel.setText(Integer.toString(count));
                    })
            );
        }
        timeLine.setCycleCount(Timeline.INDEFINITE);
        if (isTiming == true) {
            timeLine.play();
            timeButton.setText("Stop");

        } else {
            timeLine.pause();
            timeButton.setText("Start");
        }

    }


}