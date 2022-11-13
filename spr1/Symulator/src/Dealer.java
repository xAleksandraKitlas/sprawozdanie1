import java.util.Random;

public class Dealer extends Thread implements Worker{

    private int number;
    private int money;

    private int merchandise;

    private int stressLevel;

    private int state;

    private boolean inContact;

    private Factory factory;

    private Random random;

    public Dealer (int number, int money, int merchandise, Factory factory) {
        this.number = number;
        this.money = money;
        this.merchandise = merchandise;
        this.factory = factory;
        this.stressLevel = 0;
        this.state = WORK;
        this.inContact = false;
        this.random = new Random();
    }
    @Override
    public int number() {
        return number;
    }

    @Override
    public String name() {
        return "Dealer ";
    }

    public void decreaseMoney(){
        money -= 250;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public boolean isInContact () {
        return this.inContact;
    }

    public void run() {
        while (true) {
            if(state == WORK) {
                merchandise --;
                money += random.nextInt(50)+30;
                if (merchandise < 0){
                    state = ALL_DISTRIBUTORS_BUSY;
                }
            }
            else if ( state == ALL_DISTRIBUTORS_BUSY) {
                state = factory.contactDistributor(this);
            }
            else if(state == IN_CONTACT_WITH_DISTRIBUTOR) {
                state = factory.giveShare(this);
            }
            else if (state == SHARE_GIVEN) {
                state = factory.getMerchandise(this);
                if(money < 0 ){
                    stressLevel ++;
                }
            }
            if(stressLevel > 10){
                System.out.println("Dealer " + number + " caught by police");
                return;
            }
        }
    }
}
