package com.snakeGame.project;

import java.awt.*;

public class Egg {

    private int x, y, score;
    private Snake snake;

    public Egg(Snake s) {
        this.snake = s;
        changeEggPosition();
    }

    public void changeEggPosition() {
        this.x = (int) (Math.random() * 380);
        this.y = (int) (Math.random() * 380);
    }

    public void snakeEggCollision() {
        int snakeX = snake.getSnakePoints().get(0).getX();
        int snakeY = snake.getSnakePoints().get(0).getY();

        if(snakeX >= x-1 && snakeX <= (x + 6)) {
            if(snakeY >= y-1 && snakeY <= (y + 6)) {
                changeEggPosition();;
                score++;
                snake.setElongate(true);
            }
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 6, 6);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
