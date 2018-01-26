import java.text.DecimalFormat;    //place decimal format in package.
/**
 * This is sub-class which extends BankAccount class.
 * This charges fee from chequing accounts.
 * This class contains override methods to update and print balance.
 * @author Jagmandeep Singh
 *
 */
public class ChequingAccount extends BankAccount {
	double fee = 10;      //monthly charge for chequing account.

	public ChequingAccount(double random) {  //constructor of ChequingAccount with one parameter.
		super(random);
	}

	@Override      //updates balance for chequing account.
	public void calculateAndUpdateBalance() {
		balance =(getBalance()-fee);

	}

	@Override     //prints balance for chequing account.
	public void print(){
		DecimalFormat df = new DecimalFormat("##.###");    //Restricts decimal format till 3 decimal places.
		System.out.println("Chequing acccount "+df.format(getBalance()));
	}

}
