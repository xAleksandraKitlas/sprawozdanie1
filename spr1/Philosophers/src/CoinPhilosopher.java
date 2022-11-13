import java.util.Random;
import java.util.concurrent.Semaphore;

public class CoinPhilosopher extends Thread{

//    private static final int MAX = 5;
//    static Semaphore[] fork = new Semaphore[MAX];
    int currentNumber;
    Random draw;

    PhilosopherOverseer overseer;

    public CoinPhilosopher (int currentNumber, PhilosopherOverseer overseer) {
        this.currentNumber = currentNumber;
        this.overseer = overseer;
        draw = new Random(currentNumber);
    }

    public void run () {
        while (true) {
            System.out.println("Thinking: " + currentNumber);
            try {
                Thread.sleep( (long) (5000 * Math.random()));
            }
            catch (InterruptedException ex){
            }
            int side = draw.nextInt(2);
            boolean gotTwoForks = false;
            do{
                if(side == 0) {
                    overseer.getFork()[currentNumber].acquireUninterruptibly();
                    if (!(overseer.getFork()[(currentNumber+1)%Main.MAX].tryAcquire())) {
                        overseer.getFork()[currentNumber].release();
                    }
                    else {
                        gotTwoForks = true;
                    }
                }
                else {
                    overseer.getFork()[(currentNumber+1)%Main.MAX].acquireUninterruptibly();
                    if (!(overseer.getFork()[currentNumber].tryAcquire())) {
                        overseer.getFork()[(currentNumber+1)%Main.MAX].release();
                    }
                    else {
                        gotTwoForks = true;
                    }
                }
            } while (gotTwoForks == false);
            System.out.println("Starting eating: " + currentNumber);
            try {
                Thread.sleep( (long) (3000 * Math.random()));
            }
            catch (InterruptedException ex){
            }
            System.out.println("Finished Eating: " + currentNumber);

            overseer.getFork()[currentNumber].release();
            overseer.getFork() [(currentNumber + 1) % overseer.getMAX()].release();
        }
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < MAX; i++) {
//            fork [i] = new Semaphore(1);
//        }
//        for (int i = 0; i < MAX; i++) {
//            new CoinPhilosopher(i).start();
//        }
//    }
}
