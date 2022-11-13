public class Factory {

    private static final int WORK = 0;
    private static final int IN_CONTACT_WITH_DISTRIBUTOR = 1;
    private static final int ALL_DISTRIBUTORS_BUSY = 2;
    private static final int SHARE_GIVEN = 3;
    private static final int GOT_MERCHANDISE = 4;
    private static final int DIDNT_GOT_MERCH= 5;
    private static final int WAITING_FOR_DISTRIBUTOR= 6;
    private static final int PAYMENT_RECEIVED= 7;

    public Factory (int money, int pseudoephedrine, int acetone, int hydriodicAcid, int distributors){
        this.money = money;
        this.pseudoephedrine = pseudoephedrine;
        this.acetone = acetone;
        this. hydriodicAcid = hydriodicAcid;
        this.distributors = distributors;
        this.busyDistributors = 0;
    }

    //resources
    int money;
    int pseudoephedrine;
    int acetone;
    int hydriodicAcid;

    //workers


    int distributors;
    int busyDistributors;

    public synchronized int contactDistributor (Worker worker){
        try {
            Thread.currentThread().sleep(1000);
        }
        catch (Exception e){

        }
        if(busyDistributors <  distributors){
            busyDistributors ++;
            System.out.println(worker.name() + worker.number() + " is in contact with distributor");
            worker.setInContact(true);
            return IN_CONTACT_WITH_DISTRIBUTOR;
        }
        else{
            System.out.println(worker.name() + worker.number() + " is waiting for distributor to be available");
            return ALL_DISTRIBUTORS_BUSY;
        }
    }

    public synchronized int giveShare (Dealer dealer){
        money += 250;
        dealer.decreaseMoney();
        System.out.println("Dealer " + dealer.number() + " gave us our share. We have " + money + " money");
        return SHARE_GIVEN;
    }

    public synchronized int getMerchandise (Dealer dealer) {

        if(!dealer.isInContact() && busyDistributors < distributors){
            dealer.setInContact(true);
            busyDistributors ++;
        }

        if(dealer.isInContact()) {
            if (pseudoephedrine > 0 && acetone > 0 && hydriodicAcid > 0) {
                pseudoephedrine--;
                acetone--;
                hydriodicAcid--;
                System.out.println("Dealer " + dealer.number() + " got new merchandise");
                dealer.setInContact(false);
                busyDistributors--;
                return WORK;
            } else {
                System.out.println("Dealer " + dealer.number() + " couldn't get the merchandise");
                return SHARE_GIVEN;
            }
        }
        else {
            return SHARE_GIVEN;
        }
    }

    public synchronized int receiveSupply (Supplier supplier){
        pseudoephedrine ++;
        hydriodicAcid ++;
        acetone ++;
        money -= 100;

        System.out.println("Getting supply form supplier "+ supplier.number() + " factory have "+money+ " money");
        return PAYMENT_RECEIVED;
    }
}
