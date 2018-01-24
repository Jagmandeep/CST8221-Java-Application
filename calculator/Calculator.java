package calculator;
/*
 *  Author Jagmandeep Singh
 *  Student number : 
 *  Lab Section : 303
 *  Assignment 2
 *  Date November 29, 2017
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Calculator{
	public static void main(String[] args) {
		
		final int duration = 5000;
		/*
		 * Splash screen would appear on screen 
		 * Time depends on value duration variable is having
		 * in our case its 5000 which is equal to 5 seconds
		 */
		CalculatorSplashScreen screen = new CalculatorSplashScreen(duration);
		screen.showSplashWindow();
		EventQueue.invokeLater( new Runnable(){
			@Override
			public void run() {
				/*
				 * New frame is created with specific minimum size
				 */
				JFrame frame = new JFrame("Calculator");
				frame.setMinimumSize(new Dimension(300,460));
				frame.setContentPane(new CalculatorViewController());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
				
	}
}
