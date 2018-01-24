package calculator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorModel {
	private static String operand1="";//saves first operand 
	private static String operand2 ="";//saves second operand 
	private boolean Estate = false;//to check error state
	private String mode=".00";//initialize mode as float in default conditions
	private String operation="";//takes operation for calculation

	public CalculatorModel(){//constructor

	}
	/*getter for operand 1*/
	public static String getOperand1() {
		return operand1;
	}
	/*getter for operand 2*/
	public static String getOperand2() {
		return operand2;
	}
	/*Setter for operand 1*/
	public void setOperand1(String toSet){
		this.operand1 = toSet;
	}
	/*Setter for operand 2*/
	public void setOperand2(String toSet){
		this.operand2 = toSet;
	}
	/*Setter for calculator mode*/
	public void setMode(String mode){
		this.mode = mode;
	}
	/*getter for calculator mode*/
	public String getMode(){
		return mode;
	}
	/*Setter for calculator operator*/
	public void setArithmaticOperation(String operation){
		this.operation = operation;
	}
	/*Setter for calculator operator*/
	public String getArithmaticOperation(){
		return operation;
	}
	public void setErrState(boolean Estate) {
		this.Estate = Estate;
	}
	public boolean getErrState() {
		return Estate;
	}
	private float calculate(){
		/*Check condition to handle crashes  */
		if (operand1.isEmpty()){
			operand1= "0";
		}
		if(operand2.isEmpty()){
			operand2 = operand1;
		}
		if(operation.isEmpty()){
			return Float.parseFloat(operand2);
		}
		if(operation.equals("+")){
			return Float.parseFloat(operand1)+ Float.parseFloat(operand2);

		}
		else if(operation.equals("-")){
			return Float.parseFloat(operand1)- Float.parseFloat(operand2);

		}
		else if(operation.equals("/")){
			return Float.parseFloat(operand1) / Float.parseFloat(operand2);
		}

		else if(operation.equals("*")){
			return Float.parseFloat(operand1) * Float.parseFloat(operand2);
		}

		return Float.parseFloat(operand1);
	}
	public String precision(){
		/*Modifies result according to current mode of calculator */
		DecimalFormat myFormat; 
		if(mode.equals(".0")){
			myFormat = new DecimalFormat("#0.0");
			return myFormat.format(calculate());
		}
		else if(mode.equals(".00")){
			myFormat = new DecimalFormat("#0.00");
			return myFormat.format(calculate());
		}
		else if(mode.equals("Int")){
			return String.valueOf(Math.round(calculate()));
		}
		else if(mode.equals("Sci")){
			myFormat = new DecimalFormat("#.######E00");
			return myFormat.format(calculate());
		}
		return "E";
	}
	public void clear(){
		operand1="";
		operand2="";
		operation = "";

	}
	public String getResult(){
		return (precision());
	}
}
