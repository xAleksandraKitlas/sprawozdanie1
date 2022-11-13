public class Main {

    static int DEALER = 100;
    static int SUPLIER = 100;
    static int DISTRIBUTOR = 60;

    public static void main(String[] args) {

        Factory factory = new Factory(20000, 10, 10,10, DISTRIBUTOR);
        for(int i=0; i < SUPLIER; i++){
            new Supplier(i, 100, factory).start();
            new Dealer(i, 0, 10, factory).start();
        }
    }
}