import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tony on 29.04.15.
 */
@Entity
public class Highscore {
    @Id
    int id;
    int score;

    public Highscore(){
        score = 0;
        id = 1;
    }
    public Highscore(int score, int id){
        this.score = score;
        this.id = id;
    }

    public void setScore(int score){this.score = score;}
    public int getScore() {return score;}
}
