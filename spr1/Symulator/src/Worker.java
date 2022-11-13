import java.util.Random;

public interface Worker {
     int WORK = 0;
     int IN_CONTACT_WITH_DISTRIBUTOR = 1;
     int ALL_DISTRIBUTORS_BUSY = 2;
     int SHARE_GIVEN = 3;
     int GOT_MERCHANDISE = 4;
     int DIDNT_GOT_MERCH= 5;
     int WAITING_FOR_DISTRIBUTOR= 6;
     int PAYMENT_RECEIVED= 7;

     int number();
     String name();

     void setInContact(boolean inContact);

}
