import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {



    int currentNumber;
    
    PhilosopherOverseer overseer;

    public Philosopher (int currentNumber, PhilosopherOverseer overseer) {
        this.currentNumber = currentNumber;
        this.overseer = overseer;
    }

    public void run () {
        while (true) {
            System.out.println("Thinking : " + currentNumber);
            try {
                Thread.sleep(( long ) (7000 * Math.random()));
            }
            catch (InterruptedException ex) {
            }
            overseer.getFork()[currentNumber].acquireUninterruptibly();
            overseer.getFork()[(currentNumber+1)%overseer.getMAX()].acquireUninterruptibly();
            System.out.println("Starting Eating " + currentNumber);
            try {
                Thread.sleep( (long) (5000 * Math.random()));
            }
            catch (InterruptedException ex){
            }
            System.out.println("Finishing eating " + currentNumber);
            overseer.getFork() [currentNumber].release();
            overseer.getFork() [(currentNumber + 1) % overseer.getMAX()].release();
        }
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < MAX; i++) {
//            fork [i] = new Semaphore(1);
//        }
//        for (int i = 0; i < MAX; i++) {
//            new Philosopher(i).start();
//        }
//    }
}
