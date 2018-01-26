import java.text.DecimalFormat;  //place decimal format in package.
import java.util.Random;         //places random package in class.
/**
 * This is sub-class which extends BankAccount class.
 * This adds interest on savings accounts.
 * This class contains override methods to update and print balance.
 * @author Jagmandeep Singh
 *
 */
class SavingsAccount extends BankAccount {
	double interestRate = 120;  //yearly interest rate.



	public SavingsAccount(double random) { //constructor of SavingsAccount with one parameter.
		super(random);

	}



	@Override     //updates balance for savings account.
	public void calculateAndUpdateBalance() {
		balance = getBalance()+interestRate/12;

	}


	@Override         //prints balance for savings account.
	public void print() {
		DecimalFormat df = new DecimalFormat("##.###");
		System.out.println("Saving acccount "+df.format(getBalance()));

	}
}
