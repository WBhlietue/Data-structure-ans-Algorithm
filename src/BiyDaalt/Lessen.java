package BiyDaalt;

public class Lessen {
    private Subject learned;
    private int score;

    public Lessen(Subject sub, int sc) {
        learned = sub;
        if(sc > 100){
            sc = 100;
        }else if(sc < 0){
            sc = 0;
        }
        score = sc;
    }

    public int getScore() {
        return score;
    }

    public Subject getLearned() {
        return learned;
    }

    public void setLearned(Subject learned) {
        this.learned = learned;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static Lessen FromString(String str) {
        String[] s = str.split(",");
        return new Lessen(Subject.FromString(s[0]), Integer.parseInt(s[1]));
    }

    public String ConvertString() {
        return learned.ConvertString() + "," + score;
    }
}
