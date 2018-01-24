package calculator;
/* CST8221-JAP: LAB 02, Application Splash Screen
   File name: SplashScreenDemo.java
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

/**
 A simple class demonstrating how to create a splash screen for an application using JWindow as a container.
 The splash screen will appear on the screen for the duration given to the constructor.
 The class includes a main() method for testing purposes.
 Normally, this class should be used by the main() of an application.
 Note: Since JWindow uses a default frame when made visible it does not receives events.
       If you want to process events using JWindow, you must create an undecorated frame
       and attach JWindow to this frame using an appropriate JWindow constructor.
 @version 1.17.2
 @author Svillen Ranev
 @since Java 2
 */
public class CalculatorSplashScreen extends JWindow {
	/** Swing components are serializable and require serialVersionUID */
	private static final long serialVersionUID = 6248477390124803341L;
	/** Splash screen duration */
	private final int duration;
	private JProgressBar progressBar;
	public static final int PB_MINIMUM=0;
	public static final int PB_MAXIMUM=100;
	/**
  Default constructor. Sets the show time of the splash screen.
	 */
	public CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}
	/**
 Shows a splash screen in the center of the desktop
 for the amount of time given in the constructor.
	 */
	public void showSplashWindow() {
		//create content pane
		JPanel content = new JPanel(new BorderLayout());
		progressBar = new JProgressBar();
		progressBar.setMinimum(PB_MINIMUM);
		progressBar.setMaximum(PB_MAXIMUM);
		progressBar.setString("Loading calculator. Please wait...");
		progressBar.setForeground(Color.RED);
		progressBar.setStringPainted(true);
		// or use the window content pane
		//  JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.GRAY);

		// Set the window's bounds, position the window in the center of the screen
		int width =  534+10;
		int height = 263+10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		//set the location and the size of the window
		setBounds(x,y,width,height);

		// Create the splash screen
		// accessing an image in a package named splash without using getResource - needs an absolute path 

		// JLabel label = new JLabel(new ImageIcon("c:\\...\\SplashSwing.gif"));

		//image placed in the package source folder 
		//JLabel label = new JLabel(new ImageIcon(getClass().getResource("/splash/SplashSwing.gif"))); 

		//image placed in folder named resources placed into the source folder
		//JLabel label = new JLabel(new ImageIcon(getClass().getResource("/splash/resources/SplashSwing.gif")));

		//using URL with an image placed in resources folder in source folder
		//    URL imgURL = this.getClass().getResource("resources/SplashSwing.gif");
		//    Image img = Toolkit.getDefaultToolkit().getImage(imgURL);
		//    JLabel label = new JLabel(new ImageIcon(img));
		//accessing an image in the default packge
		//works if compiled and run from command prompt - not if run from Netbeans
		//JLabel label = new JLabel(new ImageIcon("SplashSwing.gif"));
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("javapic.png"))); 

		JLabel demo = new JLabel("Name: Jagmandeep Singh               StudentNumber: 040851451", JLabel.CENTER);
		demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));

		content.add(label, BorderLayout.CENTER);
		content.add(demo, BorderLayout.SOUTH);
		content.add(progressBar, BorderLayout.NORTH);
		// create custom RGB color
		Color customColor = new Color(44, 197, 211);
		content.setBorder(BorderFactory.createLineBorder(customColor, 10));

		//   Container contentPane = getContentPane();
		//   contentPane.add(content);
		//   add(content);
		//replace the window content pane with the content JPanel
		setContentPane(content);
		
		setVisible(true);
		//You can hide the splash window. The resources will not be released.
		//setVisible(false);
		for (int i = PB_MINIMUM; i <= PB_MAXIMUM; i++) {
			final int percent=i;
			//The lines below are required to keep the GUI responsive to the user
			//It will execute the code within the event dispatch thread
			try {
				progressBar.setValue(percent);
				//make the program inactive for a while so that the GUI thread can do its work
				java.lang.Thread.sleep(50);
			} catch (InterruptedException e) {;}
		} 
		dispose(); 
	}
}
