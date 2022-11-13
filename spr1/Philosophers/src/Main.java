import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int MAX = 5;
    static Semaphore[] fork = new Semaphore[MAX];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Philosopher problem ");
        System.out.println("1. Standard");
        System.out.println("2. With asymmetrical usage of fork");
        System.out.println("3. With coin throw");
        System.out.println("Select Option: ");
        int option = 0;
        while (option < 1 || option > 3){
            option = scanner.nextInt();
        }
        System.out.println("How many philosopher do you wanna use?");
        int countOfPhilosophers = 0;
        while(countOfPhilosophers < 1){
            countOfPhilosophers = scanner.nextInt();
        }
        PhilosopherOverseer overseer = new PhilosopherOverseer(countOfPhilosophers);
        switch (option){
            case 1:
                for (int i = 0; i < countOfPhilosophers; i++){
                    new Philosopher(i, overseer).start();
                }
                break;
            case 2:
                for (int i = 0; i < countOfPhilosophers; i++){
                    new AsymmetricPhilosopher(i, overseer).start();
                }
                break;
            case 3:
                for (int i = 0; i < countOfPhilosophers; i++){
                    new CoinPhilosopher(i,overseer).start();
                }
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}