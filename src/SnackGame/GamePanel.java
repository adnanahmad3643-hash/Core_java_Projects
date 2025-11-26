package SnackGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    static final int WIDTH = 1200;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
    static final int DELAY = 100;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    int bodyParts = 6;
    int applesEaten;
    int appleX, appleY;

    char direction = 'R';
    boolean running = false;

    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            // apple
            g.setColor(Color.yellow);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // score
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            FontMetrics f = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (WIDTH - f.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {

        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
        case 'U':
            y[0] -= UNIT_SIZE;
            break;
        case 'D':
            y[0] += UNIT_SIZE;
            break;
        case 'L':
            x[0] -= UNIT_SIZE;
            break;
        case 'R':
            x[0] += UNIT_SIZE;
            break;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // self collision
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // border collision
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // final score
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics f1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - f1.stringWidth("Game Over")) / 2, HEIGHT / 2);

        // score at game over
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics f2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,
                (WIDTH - f2.stringWidth("Score: " + applesEaten)) / 2,
                HEIGHT / 2 + 50);

        g.drawString("Press R to Restart",
                (WIDTH - f2.stringWidth("Press R to Restart")) / 2,
                HEIGHT / 2 + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                if (direction != 'R')
                    direction = 'L';
                break;

            case KeyEvent.VK_RIGHT:
                if (direction != 'L')
                    direction = 'R';
                break;

            case KeyEvent.VK_UP:
                if (direction != 'D')
                    direction = 'U';
                break;

            case KeyEvent.VK_DOWN:
                if (direction != 'U')
                    direction = 'D';
                break;

            case KeyEvent.VK_R:
                // restart game
                if (!running) {
                    bodyParts = 6;
                    applesEaten = 0;
                    direction = 'R';
                    for (int i = 0; i < bodyParts; i++) {
                        x[i] = 0;
                        y[i] = 0;
                    }
                    startGame();
                }
                break;
            }
        }
    }
}