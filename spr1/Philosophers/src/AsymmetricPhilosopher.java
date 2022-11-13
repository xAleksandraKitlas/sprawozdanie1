import java.util.concurrent.Semaphore;

public class AsymmetricPhilosopher extends Thread {


    int currentNumber;

    PhilosopherOverseer overseer;

    public AsymmetricPhilosopher (int currentNumber, PhilosopherOverseer overseer) {
        this.currentNumber = currentNumber;
        this.overseer = overseer;
    }

    public void run () {
        while (true) {
            System.out.println("Thinking: "+currentNumber);
            try {
                Thread.sleep( (long) (5000 * Math.random()));
            }
            catch (InterruptedException ex){
            }

            if (currentNumber == 0) {
                overseer.getFork() [(currentNumber + 1) % overseer.getMAX()].acquireUninterruptibly();
                overseer.getFork() [currentNumber].acquireUninterruptibly();
            }
            else {
                overseer.getFork() [currentNumber].acquireUninterruptibly();
                overseer.getFork() [(currentNumber + 1) % overseer.getMAX()].acquireUninterruptibly();
            }
            System.out.println("Starting eating: " + currentNumber);
            try {
                Thread.sleep( (long) (3000 * Math.random()));
            }
            catch (InterruptedException ex){
            }

            System.out.println("Finished eating: " + currentNumber);

            overseer.getFork()[currentNumber].release();
            overseer.getFork() [(currentNumber + 1) % overseer.getMAX()].release();
        }
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < MAX; i++) {
//            fork [i] = new Semaphore(1);
//        }
//        for (int i = 0; i < MAX; i++) {
//            new AsymmetricPhilosopher(i).start();
//        }
//    }

}
