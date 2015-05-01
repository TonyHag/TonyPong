/**
 * Created by tony on 29.04.15.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    int x = 300;
    int xa = 0;
    int y = 400;
    int ya = 0;
    int yPosNed = 580;
    int yPosOpp = 20;
    int xPosHoy = 780;
    int xPosVen = 20;
    private Game game;

    public Paddle(Game game) {
        this.game= game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth()-60)
            x = x + xa;
    }
    public void moveSides(){
        if (y + ya > 0 && y + ya < game.getHeight()-60)
            y = y + ya;
    }

    public void paint(Graphics2D g2d) {
        Graphics2D g = g2d;
        g.fillRoundRect(x, yPosNed,100,10 ,10,10);
        g.fillRoundRect(x, yPosOpp, 100, 10, 10,10);
        g.fillRoundRect(xPosHoy, y, 10, 100,10,10);
        g.fillRoundRect(xPosVen, y, 10, 100,10,10);
    }


    public void keyReleased(KeyEvent e) {
        xa = 0;
        ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 2;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ya = -2;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 2;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, yPosNed, 100, 10);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle(x, yPosOpp, 100, 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle(xPosVen, y, 10, 100);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle(xPosHoy, y, 10, 100);
    }
    public int getTopY(){
        return 580;
    }
    public int getBottomY(){
        return yPosOpp - 10;
    }
    public int getRightX(){
        return xPosVen - 10;
    }
    public int getLeftX() {
        return 780;
    }
}
