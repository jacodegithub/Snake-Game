package com.snakeGame.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Canvas extends JPanel implements Runnable, KeyListener {
    private JFrame frame;
    private Snake snake;
    private Thread thread;
    private boolean gameOver = false;

    public Canvas() {
        thread = new Thread(this);

        snake = new Snake();

        frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);

        thread.start();
    }
//
//    public void update(Graphics g) {
//        paint(g);
//    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 400, 400);

        snake.draw(g2d);
    }

    public void checkGameOver() {
        Points head = snake.getSnakePoints().get(0);
        if(head.getX() < 0 || head.getX() > 396) {
            gameOver = true;
        }
        if(head.getY() < 0 || head.getY() > 356) {
            gameOver = true;
        }
        if(snake.snakeCollision()) {
            gameOver = true;
        }
    }

    @Override
    public void run() {

        while (true) {

            this.repaint();

            if(gameOver) {
                return;
            }

            snake.move();
            this.checkGameOver();


            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

//        if(snake.getXDir() == 0 && snake.getYDir() == 0 &&
//            snake.getSnakePoints().get(snake.getSnakePoints().size() - 1).getX() == 0 &&
//            snake.getSnakePoints().get(snake.getSnakePoints().size() - 1).getY() == 0
//        ) {
//
//        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(snake.getXDir() != 1) {
                snake.setXDir(-1);
                snake.setYDir(0);
                snake.setMoving(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println(snake.getXDir());
            if(snake.getXDir() != -1) {
                snake.setXDir(1);
                snake.setYDir(0);
                snake.setMoving(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(snake.getYDir() != 1) {
                snake.setYDir(-1);
                snake.setXDir(0);
                snake.setMoving(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(snake.getYDir() != -1) {
                snake.setYDir(1);
                snake.setXDir(0);
                snake.setMoving(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
