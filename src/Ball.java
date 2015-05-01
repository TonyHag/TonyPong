/**
 * Created by tony on 29.04.15.
 */
import java.awt.*;

public class Ball {
    private static final int DIAMETER = 30;
    int x = 1;
    int y = 1;
    int xa = 1;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game= game;
    }

    void move() {
        if (x + xa < 0)
            game.gameOver();
        if (x + xa > game.getWidth() - DIAMETER)
            game.gameOver();
        if (y + ya < 0)
            game.gameOver();
        if (y + ya > game.getHeight() - DIAMETER)
            game.gameOver();
        if (collisionTop()){
            ya = 1;
            y = game.paddle.getBottomY() + DIAMETER;
            game.score++;
        }
        if (collisionLeft()){
            xa = 1;
            x = game.paddle.getRightX() + DIAMETER;
            game.score++;
        }
        if (collisionRight()){
            xa = -1;
            x = game.paddle.getLeftX() - DIAMETER;
            game.score++;
        }
        if (collision()){
            ya = -1;
            y = game.paddle.getTopY() - DIAMETER;
            game.score++;
        }
        x = x + xa;
        y = y + ya;
    }

    public void paint(Graphics2D g2d) {
        Graphics2D g = g2d;


        g.fillOval(x, y, DIAMETER, DIAMETER);

    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
    private boolean collision() {
        return game.paddle.getBounds().intersects(getBounds());
    }
    private boolean collisionTop() {
        return game.paddle.getBoundsTop().intersects(getBounds());
    }
    private boolean collisionLeft() {
        return game.paddle.getBoundsLeft().intersects(getBounds());
    }
    private boolean collisionRight() {
        return game.paddle.getBoundsRight().intersects(getBounds());
    }
}
