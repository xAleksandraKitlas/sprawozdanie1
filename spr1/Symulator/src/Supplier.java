import java.util.Random;

public class Supplier extends Thread  implements Worker {

   private int number;
   private int money;
   private int stressLevel;
   private int state;
   private boolean pseudoephedrine;
   private boolean acetone;
   private boolean hydriodicAcid;

   private Factory factory;

   Random random;

   public Supplier(int number, int money, Factory factory){
       this.number = number;
       this.money = money;
       this.factory = factory;
       this.stressLevel = 0;
       this.state = WORK;
       this.pseudoephedrine = false;
       this.acetone = false;
       this.hydriodicAcid = false;
       random = new Random();
   }


    @Override
    public int number() {
        return number;
    }

    @Override
    public String name() {
        return "Supplier ";
    }

    @Override
    public void setInContact(boolean inContact) {

    }

    public void run (){
        while (true) {
            if (state == WORK){
                int resource = random.nextInt(3);
                switch (resource) {
                    case 0:
                        if(!pseudoephedrine){
                            money -= 30;
                        }
                        pseudoephedrine = true;
                        break;
                    case 1:
                        if(!acetone){
                            money -= 30;
                        }
                        acetone = true;
                        break;
                    case 2:
                        if(!hydriodicAcid){
                            money -= 30;
                        }
                        hydriodicAcid = true;
                        break;
                    default:
                        break;

                }
                if(money < 0){
                    stressLevel --;
                }
                if(pseudoephedrine && acetone && hydriodicAcid){
                    System.out.println("Suplier "+ number + " got all the supplies");
                    state = ALL_DISTRIBUTORS_BUSY;
                }
            }
            else if(state == ALL_DISTRIBUTORS_BUSY) {
                this.state = factory.contactDistributor(this);
            }
            else if (state == IN_CONTACT_WITH_DISTRIBUTOR) {
                this.state = factory.receiveSupply(this);
            }
            else if (state == PAYMENT_RECEIVED) {
                this.state = WORK;
                stressLevel -= 2;
            }

            if(stressLevel > 10){
                System.out.println("Supplier " + number + " caught by police");
                return;
            }
        }
    }
}
