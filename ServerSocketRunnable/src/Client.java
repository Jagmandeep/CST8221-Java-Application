import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Client {
public static void main(String[] args) {
		
		
		EventQueue.invokeLater( new Runnable(){
			@Override
			public void run() {
				/*
				 * New frame is created with specific minimum size
				 */
				JFrame frame = new JFrame("Jagman's Client");
				frame.setMinimumSize(new Dimension(610,550));
				frame.setContentPane(new ClientView());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
				
	}
}
