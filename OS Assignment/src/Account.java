import javax.management.monitor.Monitor;
import java.util.ArrayList;

/**
 * Created by Josh on 08/11/2015.
 */
public class Account extends Monitor {
    //transactions ArrayList to store each transaction
    public ArrayList<String> transactions = new ArrayList<>();

    int balance;
    int transaction = 1;
    //enum constant for TransactionType helps refactor for createTransaction
    public enum TransactionType{ DEPOSIT, WITHDRAW }

    public Account(int balance){
        this.balance = balance;
    }

    //withdraw to take away from balance using Monitor wait/notify so transactions are in sync
    public synchronized void withdraw(int money, int id){
        while(balance - money < 0){
            try{
                wait();
            } catch(InterruptedException e){}
        }
        balance = balance - money;
        createTransaction(TransactionType.WITHDRAW, money, id);

    }
    public synchronized void deposit(int money, int id) {
        balance = money + balance;
        notifyAll();
        createTransaction(TransactionType.DEPOSIT, money, id);
    }

    /**
     * formatting transaction using .format and enumeration types mapping keys
     * createTransaction to create but not print transactions
     * @param transactionType the type of transaction
     * @param money the amount of money
     * @param id the id of the card
     */
    public synchronized void createTransaction(TransactionType transactionType, int money, int id ) {
        String transactionText; //declare string variable to store transactions in ArrayList
        // and store each format in arrayList if dependant on which is needed
        if(transaction == 1){
            transactionText = String.format("| Trans No. | Deposited | Withdrawn | Balance \n");
            transactions.add(transactionText);
        }

        if (transactionType == transactionType.DEPOSIT){
            transactionText = String.format("| %03d(%s)\t| %s\t\t\t| %s\t\t\t| %s\n", transaction, id, money, "-", balance);
        } else {
            transactionText = String.format("| %03d(%s)\t| %s\t\t\t| %s\t\t\t| %s\n", transaction, id, "-", money, balance);
        }

        //adding the actual transactionText format to arrayList with transactions
        transactions.add(transactionText);
        transaction += 1;
    }


    //extended methods not used
    public void start(){

    }

    public void stop(){

    }
}
