package com.snakeGame.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Canvas extends JPanel implements Runnable, KeyListener {
    private JFrame frame;
    private Snake snake;
    private Egg egg;
    private Thread thread;
    private boolean gameOver = false;

    public Canvas() {
        thread = new Thread(this);

        snake = new Snake();
        egg = new Egg(snake);

        frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 400, 400);

        if(!gameOver) {
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Poppins",Font.BOLD, 50));
            g2d.drawString(String.valueOf(egg.getScore()), 340, 50);
            snake.draw(g2d);
            egg.draw(g2d);
        }
        else {
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Poppins", Font.BOLD, 20));
            g2d.drawString("GAME OVER..!", 120, 150);
        }
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
            egg.snakeEggCollision();

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

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(snake.getXDir() != 1) {
                snake.setXDir(-1);
                snake.setYDir(0);
                snake.setMoving(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
