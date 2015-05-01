import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
    static int level = 1;
    static int score = 0;
    static int sleep = 9;
    Ball ball = new Ball(this);
    Paddle paddle = new Paddle(this);

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                paddle.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                paddle.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    private void move() {
        ball.move();
        paddle.move();
        paddle.moveSides();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        paddle.paint(g2d);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(this, "Score: " + score, "Score", JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }
    public boolean startgame(){
        JOptionPane.showMessageDialog(this, "Start Game?", "Start Game?", JOptionPane.QUESTION_MESSAGE);
        return true;
    }
    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eclipselink");
        EntityManager em = emf.createEntityManager();


        JFrame frame = new JFrame("TonyPong");

        Game game = new Game();
        frame.add(game);
        frame.setSize(850, 650);
        frame.setVisible(true);
        frame.setBackground(Color.blue);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(game.startgame()) {
            while (true) {
                if(score ==10) {sleep = 8; level = 2;}
                if(score ==25){sleep = 7; level = 3;}
                if(score == 45){sleep = 6;level = 4;}
                if(score == 65){sleep = 5;level = 5;}
                if(score == 100){sleep = 4; level = 6;}
                if(score == 200){sleep = 2;level = 7;}
                game.move();
                game.repaint();
                Thread.sleep(sleep);
            }



        }
        Highscore dbhs = em.find(Highscore.class, 1);


        try {
            em.getTransaction().begin();
            em.persist(dbhs);
            if(dbhs.getScore() < score){
                dbhs.setScore(score);
            }

            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
        }
        em.close();
    }
}