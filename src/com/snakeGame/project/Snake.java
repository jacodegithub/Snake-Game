package com.snakeGame.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Points> snakePoints;
    private int xDir, yDir;
    private int snakeLength = 10;
    private int STARTX = 150, STARTY = 150;
    private boolean isMoving, elongate;
    private boolean gameOver;

    public Snake() {
        snakePoints = new ArrayList<>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        elongate = false;
        gameOver = false;

        snakePoints.add(new Points(STARTX, STARTY));
        for(int i=1; i<snakeLength; ++i) {
            snakePoints.add(new Points(STARTX + i * 4, STARTY));
        }

//        System.out.println("points ->"+snakePoints);
//        snakePoints.forEach(p ->
//                        System.out.println(p.getX()+" "+p.getY())
//                );
    }

    //FOR COLLIDING IT SELF
    public boolean snakeCollision() {
        int x = snakePoints.get(0).getX();
        int y = snakePoints.get(0).getY();

        for(int i=1; i<snakePoints.size() - 1; ++i) {
            if(this.yDir != 0 && this.xDir != 0 && snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
                return true;
            }
        }

        return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        for(Points p: snakePoints) {
            g.fillRect(p.getX(), p.getY(), 4, 4);
        }
    }

    public void move() {
        if(isMoving) {
            Points head = snakePoints.get(0);
            Points tail = snakePoints.get(snakePoints.size() - 1);

            Points newHead = new Points(head.getX() + xDir * 4, head.getY() + yDir * 4);
            for(int i=snakeLength-1; i>0; --i) {
                snakePoints.set(i, snakePoints.get(i-1));
            }
            snakePoints.set(0, newHead);
        }
    }

    public List<Points> getSnakePoints() {
        return snakePoints;
    }

    public void setSnakePoints(List<Points> snakePoints) {
        this.snakePoints = snakePoints;
    }

    public int getXDir() {
        return xDir;
    }

    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isElongate() {
        return elongate;
    }

    public void setElongate(boolean elongate) {
        this.elongate = elongate;
    }
}
