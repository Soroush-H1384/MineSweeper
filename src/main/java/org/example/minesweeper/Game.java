package org.example.minesweeper;

public class Game {
    private int[][] bombArray;
    private int bomb;
    private int height;
    private int width;

    public Game(int height,int width, int bomb) {
        this.setHeight(height);
        this.setBomb(bomb);
        this.setWidth(width);
        this.setBombArray(new int[height][height]);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                setOneOfBombArray(i, j, 0);
            }
        }

    }

    public void makeBombArray() {
        int countBomb = 0;
        while(countBomb < getBomb()) {
            int iRandom = (int) (Math.random() * getHeight());
            int jRandom = (int) (Math.random() * getHeight());
            if (getBombArray()[iRandom][jRandom] == 0) {
                setOneOfBombArray(iRandom,jRandom,9);
                countBomb++;
                for (int k = -1; k < 2; k++) {
                    for (int j = -1; j < 2; j++) {
                        if (k == 0 && j == 0) {
                            continue;

                        }
                        if (height > iRandom + k && iRandom + k >= 0 && height > jRandom + j && jRandom + j >= 0) {

                            increaseBombArray(iRandom + k, jRandom + j);
                        }
                    }
                }
            }
        }
    }



    public int[][] getBombArray() {
        return bombArray;
    }

    public void setOneOfBombArray(int i, int j, int number) {
        this.bombArray[i][j] = number;
    }

    public void increaseBombArray(int i, int j) {
        this.bombArray[i][j]++;
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

    public void setBombArray(int[][] bombArray) {
        this.bombArray = bombArray;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
