package org.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane mainPage;

    @FXML
    private VBox mainVBox;

    @FXML
    private Label selectedBlockCounter;
    @FXML
    private Label flagCounter;
    @FXML
    private Label timeLabel;
    @FXML
    private Button timeButton;
    @FXML
    private Button blockShape;
    private Stage stage;
    private Game game;
    private int Bomb = 0;
    private int selectedBlock = 0;
    private int count = 0;
    private int flag = 0;
    private boolean isTiming = false;
    private Timeline timeLine;
    private Button[][] buttons ;
    private boolean[][] visited ;
    private boolean[][] isFlag;
    private int height;
    private int width;
    private int bomb;

    public HelloController(int height, int width,int bomb){
        this.height = height;
        this.width = width;
        this.bomb = bomb;
        this.game = new Game(height,width,bomb);
        this.buttons = new Button[game.getHeight()][game.getWidth()];
        this.visited = new boolean[game.getHeight()][game.getWidth()];
        this.isFlag = new boolean[game.getHeight()][game.getWidth()];
    }

    public void initialize() {
        clearVisited();
        for (int i = 0; i < game.getHeight(); i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < game.getWidth(); j++) {
                Button button = new Button();
                button.getStyleClass().addAll("block", "unselected-block");
                buttons[i][j] = button;
                visited[i][j] = false;
                isFlag[i][j] = false;
                int row = i;
                int column = j;
                buttons[i][j].setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY && !visited[row][column]) {
                        if (game.getBombArray()[row][column] == 9) {
                            button.getStyleClass().remove("unselected-block");
                            button.getStyleClass().add("bombing-block");
                            visited[row][column] = true;
                            try {
                                showAllBomb();

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        } else if (0 < game.getBombArray()[row][column] && game.getBombArray()[row][column] < 9) {
                            button.getStyleClass().remove("unselected-block");
                            button.getStyleClass().add("empty-block");
                            button.setText(Integer.toString(game.getBombArray()[row][column]));
                            selectedBlockCounter.setText(Integer.toString(++selectedBlock));
                            visited[row][column] = true;
                            if (selectedBlock == (game.getWidth() * game.getHeight()) - game.getBomb()){
                                scorePopup();
                            }

                        } else {
                            button.getStyleClass().remove("unselected-block");
                            button.getStyleClass().add("empty-block");
                            checkAround(row, column);
                            visited[row][column] = true;

                        }
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        if (!isFlag[row][column] && !visited[row][column]) {
                            isFlag[row][column] = true;
                            visited[row][column] = true;
                            button.getStyleClass().add("flag-block");
                            flagCounter.setText(Integer.toString(++flag));



                        } else if(visited[row][column] && isFlag[row][column]) {
                            isFlag[row][column] = false;
                            visited[row][column] = false;
                            button.getStyleClass().remove("flag-block");
                            flagCounter.setText(Integer.toString(--flag));
                        }
                    }

                });
                hBox.getChildren().add(button);

            }
            mainVBox.getChildren().add(hBox);
        }
    }

    public void checkAround(int row, int column) {
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (k == 0 && l == 0) {
                    continue;
                }
                if (row + k < 0 || column + l >= game.getHeight() || column + l < 0 || row + k >= game.getHeight()) {
                    continue;
                } else if (game.getBombArray()[row + k][column + l] == 0 && !visited[row + k][column + l]) {
                    buttons[row + k][column + l].getStyleClass().addAll("empty-block");
                    selectedBlockCounter.setText(Integer.toString(++selectedBlock));
                    visited[row + k][column + l] = true;
                    checkAround(row + k, column + l);

                } else if (game.getBombArray()[row + k][column + l] != 9 && !visited[row + k][column + l]) {
                    buttons[row + k][column + l].setText(Integer.toString(game.getBombArray()[row + k][column + l]));
                    buttons[row + k][column + l].getStyleClass().addAll("empty-block");
                    selectedBlockCounter.setText(Integer.toString(++selectedBlock));
                    visited[row + k][column + l] = true;
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
                        timeButton.setText(Integer.toString(count));
                    })
            );
        }
        timeLine.setCycleCount(Timeline.INDEFINITE);
        if (isTiming == true) {
            timeLine.play();
//            timeButton.setText("Stop");

        } else {
            timeLine.pause();
//            timeButton.setText("Start");
        }


    }

    public void showAllBomb() throws IOException {
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                if (game.getBombArray()[i][j] == 9) {
                    buttons[i][j].getStyleClass().add("bombing-block");
                    visited[i][j] = true;
                }
            }
        }
        scorePopup();
    }

    public void scorePopup() {
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("score-board.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ScoreBoardController scoreBoardController = fxmlLoader.getController();
            scoreBoardController.setHelloController(this);
            scoreBoardController.setStage(stage);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clearVisited(){
        game.deleteBombArray();
        mainVBox.getChildren().clear();
        game.makeBombArray();
        flag = 0;
        selectedBlock = 0;
        flagCounter.setText(Integer.toString(flag));
        selectedBlockCounter.setText(Integer.toString(selectedBlock));



    }


}