import static java.lang.Thread.sleep;
//thread to implement access card for each access card using runnable
//composition over inheritance
public class Card implements Runnable {
	public int netBalance = 0;
	public int id;
	public Account account;
	//create constructor setting params id/account
	public Card(int id, Account account){
		this.id = id;
		this.account = account;
	}
	public void run() {
		for (int i = 0; i < 20; i++){
			int amount = (int)(Math.random() * 10);
			boolean coinFlip = Math.random() > 0.5;
			if(coinFlip){
				account.withdraw(amount, id);
				netBalance -= amount; //amount taken from netbalance
			} else {
				account.deposit(amount, id);
				netBalance += amount; //amount added to netbalance
			}
			try {
				sleep(200);
			}catch (Exception e){
				System.err.print(e.toString());
			}
		}
		//print out netBalance to correct format.
		System.out.println("Card Holder " + id + " complete and Net Balance: " + netBalance);

	}
}