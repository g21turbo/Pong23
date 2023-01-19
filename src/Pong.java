import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Pong extends JFrame implements KeyListener {
    int x = 0, y = 0, xa = 1, ya = 1;
    int xPaddle1 = 0, yPaddle1 = 150, paddleHeight = 50, paddleWidth = 10;
    int xPaddle2 = 290, yPaddle2 = 150, paddleSpeed = 2;

    public Pong() {
        setTitle("Pong");
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.BLUE);
        g.fillRect(xPaddle1, yPaddle1, paddleWidth, paddleHeight);
        g.fillRect(xPaddle2, yPaddle2, paddleWidth, paddleHeight);
    }

    public static void main(String[] args) {
        Pong pong = new Pong();
        while (true) {
            pong.move();
            pong.checkCollision();
            pong.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        x = x + xa;
        y = y + ya;
        if (y + ya < 0 || y + ya > getHeight() - 30) {
            ya = -ya;
        }
    }

    public void checkCollision() {
        if (x + xa < xPaddle1 + paddleWidth && x + xa > xPaddle1 && y + ya > yPaddle1 && y + ya < yPaddle1 + paddleHeight) {
            xa = -xa;
        }
        if (x + xa > xPaddle2 && x + xa < xPaddle2 + paddleWidth && y + ya > yPaddle2 && y + ya < yPaddle2 + paddleHeight) {
            xa = -xa;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            yPaddle2 -= paddleSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yPaddle2 += paddleSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            yPaddle1 -= paddleSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            yPaddle1 += paddleSpeed;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
