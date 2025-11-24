package org.example.minesweeper;

public class Game {
    private int[][] bombArray;
    private int bomb;
    private int height;

    public Game(int height, int bomb) {
        this.setHeight(height);
        this.setBomb(bomb);
        this.setBombArray(new int[height][height]);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                getBombArray()[i][j] = 0;
            }
        }

    }

    public void makeBombArray() {
        for (int i = 0; i < getBomb(); i++) {
            int iRandom = (int) (Math.random() * getHeight() );
            int jRandom = (int) (Math.random() * getHeight() );
            if (getBombArray()[iRandom][jRandom] == 0) {
                getBombArray()[iRandom][jRandom] = 1;
            }
        }
    }

    public int[][] getBombArray() {
        return bombArray;
    }

    public void setBombArray(int[][] bombArray) {
        this.bombArray = bombArray;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
