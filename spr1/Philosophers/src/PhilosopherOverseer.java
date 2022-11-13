import java.util.concurrent.Semaphore;

public class PhilosopherOverseer {

    private  int MAX;
    private Semaphore[] fork;


    public int getMAX() {
        return MAX;
    }

    public Semaphore[] getFork() {
        return fork;
    }

    public PhilosopherOverseer(int MAX) {
        this.MAX = MAX;
        fork = new Semaphore[MAX];
        for(int i = 0; i < fork.length; i++){
            fork[i] = new Semaphore(1);
        }
    }
}
