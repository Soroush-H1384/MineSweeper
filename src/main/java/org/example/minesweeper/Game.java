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
            int iRandom = (int) (Math.random() * getHeight());
            int jRandom = (int) (Math.random() * getHeight());
            if (getBombArray()[iRandom][jRandom] == 0) {
                getBombArray()[iRandom][jRandom] = 9;
            }
            for (int k = -1; k < 2; k++) {
                for (int j = -1; j < 2; j++) {
                    if (height > (iRandom + k) && (iRandom + k) >= 0 && height > (jRandom + j) && (jRandom + j) >= 0){
                        if (bombArray[iRandom+k][jRandom+j] == 9){
                            continue;
                        }
                        else {
                            increaseBombArray(iRandom + k, jRandom + j);
                        }
                    }
                }
            }
        }
    }

    public void checkAround(int i, int j) {
        i--;
        j--;
        for (int k = -1; k < 1; k++) {
            for (int l = -1; l < 1; l++) {
                if (i + k < 0 || i + k > height || j + k < 0 || j + k > height) {
                    continue;
                } else if (getBombArray()[i + k][j + l] == 0) {
                    checkAround(i + k, j + l);
                }
            }

        }
    }

    public int[][] getBombArray() {
        return bombArray;
    }
    public void setOneOfBombArray(int i,int j,int number){
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
}
