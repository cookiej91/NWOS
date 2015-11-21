import java.util.ArrayList;

public class AssignmentMain {
	private static int startingBalance;
	private static int numberOfAccessCards;
	public static void main(String[] args){
		// TODO Auto-generated method stub
		//variables that take the arguments passed in command line parsed as INT
		numberOfAccessCards = Integer.parseInt(args[0]);
		startingBalance = Integer.parseInt(args[1]);
		Account account = new Account(startingBalance);

		//create arrayList for cards to make sure they join
		ArrayList<Thread> cards = new ArrayList<>();
		for (int i = 1; i <= numberOfAccessCards; i++){
			Card card = new Card(i, account);
			Thread cardThread = new Thread(card);
			cards.add(cardThread);
			cardThread.start();
		}

		for (Thread cardThread : cards) {
			try {
				cardThread.join();
			} catch (Exception e) {
				System.err.print(e.toString());
			}
		}

		System.out.println("All Cards Complete");

		account.transactions.forEach(System.out::print);

//		account.transactions.forEach(transaction -> System.out.print(transaction));
//
// 		found a way to do different forEach loop the above only works in 1.7+
//		you can use the below code if it doesn't work
//
//		for (int i = 0; i < account.transactions.size(); i++){
//			System.out.print(account.transactions.get(i));
//		}

	}
}