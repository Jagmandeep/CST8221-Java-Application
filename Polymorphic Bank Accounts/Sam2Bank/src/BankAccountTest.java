import java.text.DecimalFormat;   //place decimal format in package
/**
 * This class calls methods from abstract class and subclasses.
 * It contains monthlyProcess, display and main methods.
 * @author Jagmandeep Singh
 *
 */
public class BankAccountTest {	

	public BankAccountTest(BankAccount [] arr1){  //BankAccountTest constructor with one argument of an array.

		for(int i=0 ; i<30 ;i++){
			if (i<15){                            //assigns first 15 elements of array as saving accounts.
				arr1[i]= new SavingsAccount(Math.random()); 
			}
			else {                                //assigns remaining 15 elements of array as chequing account.
				arr1[i]= new ChequingAccount(Math.random());
			}

		}
	}
	public static void main(String[] args) {  //starting of main method.

		BankAccount [] account = new BankAccount[30];      //Declaring array with 30 elements.
		BankAccountTest c1=new BankAccountTest(account);   //creating reference with BankAccountTest constructor.

		for(int i=1; i<13; i++){
			System.out.print(i);   //gives the number of month from 1 - 12.
			System.out.println();
			c1.monthlyProcess(account);     //updates balance each month.
			System.out.println();
			c1.display(account);        //displays final balance at the end of each month.
			System.out.println();
		}
	}

	public void monthlyProcess(BankAccount [] array){

		for(int i=0 ; i<30 ;i++){
			array[i].calculateAndUpdateBalance();  //updates the balance for all 30 accounts after each month.
		}
	}
	public void display(BankAccount [] array){
		for(int i=0 ; i<30 ;i++){
			array[i].print();       //prints updated balance of all 30 accounts.
		}
	}  //end of main.
}   //end of class.
