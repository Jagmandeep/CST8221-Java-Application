
public abstract class BankAccount {
	double balance;
	/**
	 * This is abstract class.
	 * It contains two abstract methods those are calculateAndUpdateBalance and print.
	 * You cannot instantiate abstract classes.
	 * @param random
	 * @author Jagmandeep Singh
	 */



	public BankAccount(double random) {  //BankAccount of SavingsAccount with one parameter.

		balance = random;
	}

	public double getBalance()    //Method which returns balance value.

	{
		return balance;
	}
/**
 * abstract methods do not have parameters.
 * They are overridden in sub classes.
 */
	public abstract void calculateAndUpdateBalance();  //abstract method

	public abstract void print(); //abstract method
}
