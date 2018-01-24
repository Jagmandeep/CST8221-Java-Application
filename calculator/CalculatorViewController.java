package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class CalculatorViewController extends JPanel
{
	private JTextField display1; // the calculator display1 field reference
	private JTextField display2; // the calculator display2 field reference
	private JLabel error; // the mode/error display label reference
	private JButton dotButton;	// the calculator dotButton button 
	private JRadioButton button2 = new JRadioButton(".00");	//the calculator .00 radioButton 
	private JCheckBox check = new JCheckBox("Int");	//the calculator check button 
	CalculatorModel model = new CalculatorModel(); // Reference to model
	Controller controller = new Controller(); //only one controller used for my assignment

	/*
	 * Default constructor
	 * GUI is build inside this constructor
	 */
	public CalculatorViewController(){

		setLayout(new BorderLayout());
		/*
		 * A new panel called mainPanel is created with border layout
		 * This would hold display and radio buttons 
		 */
		JPanel mainPanel= new JPanel(new BorderLayout());
		/*
		 * Dimensions are created for border and 
		 * background of panel is set to black
		 */
		mainPanel.setBorder( BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		mainPanel.setBackground(Color.black);
		/*
		 * topPanel is created to hold label, two displays and backspace
		 */
		JPanel topPanel = new JPanel(new BorderLayout() );
		topPanel.setBackground(Color.black);
		/*
		 * F is a JLable
		 * It's dimensions are changed according to our requirements
		 * background is set to black and 
		 * border is set with specific dimensions
		 */
		error = new JLabel("F", SwingConstants.CENTER);
		error.setPreferredSize(new Dimension (35 , 55));
		error.setBackground(Color.YELLOW);
		error.setFont(new Font(null, Font.PLAIN, 20));
		error.setBorder( BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		error.setOpaque(true);
		/*
		 * display1 and display2 are JTextFields
		 * Since we want to write from right so we set JTextField alignment to right
		 * Background, dimensions and borders are set according to required display 
		 */
		display1 = new JTextField();
		display1.setHorizontalAlignment(JTextField.RIGHT);
		display1.setEditable(false);
		display1.setBackground(Color.WHITE);
		display1.setPreferredSize(new Dimension (16 , 30));
		display1.setBorder( BorderFactory.createEmptyBorder(0,0,0,0));
		display1.setOpaque(true);	

		display2 = new JTextField();
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setEditable(false);
		display2.setBackground(Color.WHITE);
		display2.setPreferredSize(new Dimension (16 , 30));		
		display2.setBorder( BorderFactory.createEmptyBorder(0,0,0,0));
		display2.setText("0.00");
		display2.setOpaque(true);
		/*
		 * Variable unicode holds unicode value for backspace 
		 */
		String unicode = "\u21b2";
		/*
		 * New JButton is created for backspace
		 * Font is changed to make it look broader
		 * Border and dimensions are added to button 
		 * Background is set to yellow
		 */
		JButton backspaceButton = new JButton( unicode );
		backspaceButton.setFont(new Font(null, Font.PLAIN, 25));
		backspaceButton.setBorder( BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		backspaceButton.setPreferredSize(new Dimension (35 , 55));
		backspaceButton.setBackground(Color.yellow);
		backspaceButton.setActionCommand(unicode);
		backspaceButton.addActionListener(controller);
		backspaceButton.setOpaque(true);
		/*
		 * JLable is added to west of top panel
		 */
		topPanel.add(error,BorderLayout.WEST);
		/*
		 * TestField is created to hold two text fields at positions north and south
		 */
		JPanel textField  = new JPanel(new BorderLayout());
		textField.add(display1, BorderLayout.NORTH);
		textField.add(display2, BorderLayout.SOUTH);
		/*
		 * textField is added at the center to top panel 
		 */
		topPanel.add(textField,BorderLayout.CENTER);
		/*
		 * Backspace button is added to east of top panel
		 */
		topPanel.add(backspaceButton,BorderLayout.EAST);
		/*
		 * Radio panel is created to hold our three radio buttons and one check box
		 * Background is set to black and border is set with specific  to match given sample 
		 */
		JPanel radioPanel = new JPanel(new FlowLayout());
		radioPanel.setBackground(Color.BLACK);
		radioPanel.setBorder( BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
		/*
		 * Three radio buttons button1, button2 and button3 are created.
		 * Background of button is set to yellow
		 */
		JRadioButton button1 = new JRadioButton(".0");
		button1.setBackground(Color.YELLOW);
		button1.setActionCommand(".0");
		button1.addActionListener( controller);
		button1.setOpaque(true);
		button2.setBackground(Color.YELLOW);
		button2.setActionCommand(".00");
		button2.addActionListener( controller);
		button2.setOpaque(true);
		JRadioButton button3 = new JRadioButton("Sci");	
		button3.setBackground(Color.YELLOW);
		button3.setActionCommand("Sci");
		button3.addActionListener(controller);
		button3.setOpaque(true);
		/*
		 * One check box is created with background green
		 */

		check.setBackground(Color.GREEN);
		check.setActionCommand("Int");
		check.addActionListener(controller);
		check.setOpaque(true);
		/*
		 * Button group is created to hold all four buttons so they can act as radio buttons
		 */
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(button1);
		radioGroup.add(button2);
		radioGroup.add(button3 );
		radioGroup.add(check);
		/*
		 * button2(.00) is set selected by default
		 */
		button2.setSelected(true);
		/*
		 * radio panel is created to hold our radio buttons in calculator
		 * Layout is set to grid
		 * Size of strut is decided by looking at alignment of text-fields and radio buttons
		 */
		JPanel radio = new JPanel(new GridLayout());
		radioPanel.add(check);
		radioPanel.add(Box.createHorizontalStrut(27));
		radio.add(button1);
		radio.add(button2);
		radio.add(button3);
		radioPanel.add(radio);
		/*
		 * center panel would hold all button of calculator including
		 * numbers from 0 to 9, C, arithmetic function(+,-,*,/) etc.
		 * borders is set and background is set to black
		 */
		JPanel center  = new JPanel(new BorderLayout());
		center.setBorder( BorderFactory.createMatteBorder(0, 5, 5, 5, Color.BLACK));
		center.setBackground(Color.BLACK);
		/*
		 * keypad panel is created to hold number buttons from 0-9 and 
		 * arithmetic function like +,-,*,/ and other buttons like C, =, +/-.
		 * 
		 */
		JPanel keyPad  = new JPanel(new BorderLayout());
		/*
		 * numbers panel is created with grid layout having four rows and four columns 
		 * with horizontal and vertical gap of two
		 * background is set to white
		 * Border is created with dimension one on all sides and color of border is set to white
		 */
		JPanel numbers  = new JPanel(new GridLayout(4,4,2,2));
		numbers.setBackground(Color.WHITE);
		numbers.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		String uni = "\u00b1";
		/*
		 *An  array called arr is created which holds names of all buttons
		 *which would be created in numbers panel 
		 */
		String arr[] = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".",uni,"+"};
		/*
		 * All buttons are created with specific values in this for loop
		 */
		for(int i = 0; i<16; i++){
			if(i == 14){
				numbers.add(createButton(arr[i], null, Color.BLACK, Color.PINK, controller));
			}
			else if(i == 13){
				dotButton = createButton(arr[i], null, Color.BLACK, Color.BLUE, controller);
				numbers.add(dotButton);
			}
			else if( i==3 || i==7 || i==11 || i==15){
				numbers.add(createButton(arr[i], null, Color.BLACK, Color.CYAN, controller));
			}
			else
				numbers.add(createButton(arr[i], null, Color.BLACK, Color.BLUE, controller));
		}
		/*
		 * c panel hold only C and = button
		 * It has two rows with no columns, horizontal and vertical gap is set to two
		 */
		JPanel c  = new JPanel(new GridLayout(2,0,2,2));
		c.setBackground(Color.WHITE);
		c.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		c.add(createButton("C", null, Color.BLACK, Color.RED, controller));
		c.add(createButton("=", null, Color.BLACK, Color.MAGENTA, controller));
		/*
		 * top panel is added to north and radio panel is added to
		 * south of main panel
		 * numbers panel is added to center and c panel is added to
		 * east of keyPad panel
		 * keyPad panel is added to center of center panel
		 */
		mainPanel.add(topPanel,  BorderLayout.NORTH);
		mainPanel.add(radioPanel, BorderLayout.SOUTH);
		keyPad.add(numbers,  BorderLayout.CENTER );
		keyPad.add( c ,  BorderLayout.EAST );
		center.add(keyPad,  BorderLayout.CENTER );
		/*
		 * mainPanel is added to north and center panel is added to
		 * center in our frame in main class
		 */
		this.add(mainPanel,  BorderLayout.NORTH );
		this.add(center,  BorderLayout.CENTER );

	}

	private JButton createButton (String text, String ac, Color fg, Color bg, Action handler)
	{
		/*
		 * new button is created with name as text which is passed through parameter.
		 */
		JButton button = new JButton(text);
		/*
		 * Action is set only if ac is not null
		 */


		if (ac != null){
			button.setActionCommand(ac);
		}
		/*Getting font for button*/
		Font font = button.getFont();
		String fontName = font.getFontName();
		int fontStyle = font.getStyle();
		button.setFont( new Font( fontName, fontStyle , 20 ) );
		button.setForeground(fg);
		button.setBackground(bg);
		button.addActionListener(handler);

		switch(text) {
		case "=":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), text);
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_MASK), text);

			break;
		case "+":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), text);

			break;
		case "/":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), text);

			break;
		case "-":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), text);

			break;
		case "*":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), text);
			break;
		case ".":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), text);
			
			break;
		case "c":
		case "C":
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), text);
			break;
		
		default:
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(text), text);
		}

		button.getActionMap().put(text, handler);
		return button;	
	}
	private class Controller extends AbstractAction {

		String clicked = "";// clicked variable would store anything pressed in calculator
		boolean IntSelected = false;//check for selection of check box 
		boolean FloatSelected = false;//check for selection of .00 radio button 


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			String actionCommand = e.getActionCommand();
			int num = -1;

			try{
				/*Converts my action command to integer and saved into my string*/
				num = Integer.parseInt(actionCommand);
			}catch (NumberFormatException e1){}
			if(num >= 0 && model.getErrState() == false){
				clicked += actionCommand;
				display2.setText(clicked);
			}
			switch(actionCommand){
			/*Updates Calculator according to action command*/
			case ".0":
				/*
				 *There is check for error state in all cases 
				 *So that all processes could be stopped untill user pressed C
				 */
				if(model.getErrState() == false){
					/*If user if switching mode from integer to float
					 * It would clear results*/
					if(model.getMode().equals("Int")){
						clicked = "";
						display2.setText("0");
						display1.setText("");
					}
					
					model.setMode(".0");
					error.setBackground(Color.YELLOW);
					error.setText("F");
					dotButton.setEnabled(true);
					dotButton.setBackground(Color.BLUE);
				}
				break;
			case ".00":
				if(model.getErrState() == false){
					switchMode(".00");
				}
				break;
			case "Int":
				if(model.getErrState() == false){
					switchMode("Int");
				}
				break;
			case "Sci":
				if(model.getErrState() == false){
					if(model.getMode().equals("Int")){
						clicked = "";
						display2.setText("0");
						display1.setText("");
					}
					model.setMode("Sci");
					error.setBackground(Color.YELLOW);
					error.setText("");
					error.setText("F");
					dotButton.setEnabled(true);
					dotButton.setBackground(Color.BLUE);
				}
				break;
			case "+":
				if(model.getErrState() == false){
					if(!clicked.isEmpty()){
						model.setOperand1(clicked);
						model.setOperand2(clicked);
					}
					model.setArithmaticOperation(actionCommand);
					display1.setText(model.getOperand1()+" +");
					clicked = "";
				}
				break;
			case "-":
				if(model.getErrState() == false){
					if(!clicked.isEmpty()){
						model.setOperand1(clicked);
						model.setOperand2(clicked);
					}
					model.setArithmaticOperation(actionCommand);
					display1.setText(model.getOperand1()+" -");
					clicked = "";
				}
				break;	
			case "*":
				if(model.getErrState() == false){
					if(!clicked.isEmpty()){
						model.setOperand1(clicked);
						model.setOperand2(clicked);
					}
					model.setArithmaticOperation(actionCommand);
					display1.setText(model.getOperand1()+" *");
					clicked = "";
				}
				break;
			case "/":
				if(model.getErrState() == false){
					if(!clicked.isEmpty()){
						model.setOperand1(clicked);
						model.setOperand2(clicked);
					}
					model.setArithmaticOperation(actionCommand);
					display1.setText(model.getOperand1()+" /");
					clicked = "";
				}
				break;
			case "=":
				if(model.getErrState() == false){
					if(model.getArithmaticOperation().isEmpty()){	//for case , pressing = after launch
						break;
					}
					/*Assigns operand2 only if user pressed something before pressing equals*/
					if(!clicked.isEmpty()){
						model.setOperand2(clicked);
					}
					/*Check for Error cases*/
					if(model.getArithmaticOperation().equals("/") && clicked.equals("0")){
						if(model.getOperand1().equals("0")){
							display2.setText("Result is undefined");
						}else{
							display2.setText("Cannot divide by 0");
						}
						error.setText("E");
						error.setBackground(Color.RED);
						model.setErrState(true);
						clicked = "";
					}else{
						display1.setText("");
						display2.setText(model.getResult());
						model.setOperand1(model.getResult());
						clicked = "";
					}
				}
				break;
			case "C":
				/*Resets Calculator according to specification*/
				model.clear();
				display1.setText("");
				if(model.getMode().equals("Int")){
					display2.setText("0");
				}
				else if(model.getMode().equals(".0")){
					display2.setText("0.0");
				}
				else if(model.getMode().equals(".00")){
					display2.setText("0.00");
				}
				else{
					display2.setText("0E00");
				}
				if(model.getMode().equals("Int")){
					error.setText("I");
					error.setBackground(Color.GREEN);
				}
				else if(model.getMode().equals("Sci")){
					error.setText("F");
					error.setBackground(Color.YELLOW);
				}
				else{
					error.setText("F");
					error.setBackground(Color.YELLOW);
				}
				model.setErrState(false);

				clicked = "";
				break;
			case "\u21b2":	//backspace
				int l = clicked.length();
				String back = "";
				if(l > 1){
					for(int i = 0 ; i < l-1 ; i++){
						back += clicked.charAt(i);
					}
					clicked = back;
					display2.setText(clicked);
				}
				if(clicked.equals("-") || l == 1){
					clicked = "";
					display2.setText("0");
				}
				break;
			case ".":
				if(clicked.contains("."))	return;
				clicked += ".";
				display2.setText(clicked);
				break;
			case "\u00b1":	// Handle signs
				if(model.getErrState() == false){
					if(clicked.isEmpty()){
						display1.setText("Negate(0)");
						display2.setText("0");
						return;
					}
					if (clicked.contains("-")){
						StringBuilder str = new StringBuilder(clicked);
						str.deleteCharAt(0);
						clicked = str.toString();
						display2.setText(clicked);
					}
					else{
						clicked = "-"+clicked;
						display2.setText(clicked);
					}
				}
				break;
			default:

			}
		}
		public void switchMode(String action){
			/*For bonus it would switch modes back and forth */
			switch(action){
			case "Int":

				if(IntSelected == true){
					button2.setSelected(true);
					IntSelected = false;
					FloatSelected = false;
					switchMode(".00");//recursion occurs to update calculator according to changed mode 
					return;
				}
				if(model.getMode().equals(".0")||model.getMode().equals(".00")||model.getMode().equals("Sci")){
					clicked = "";
					display2.setText("0");
					display1.setText("");
				}
				model.setMode("Int");
				error.setText("I");
				error.setBackground(Color.GREEN);
				dotButton.setEnabled(false);
				dotButton.setBackground(new Color(178,156,250));
				IntSelected = true;

				break;
			case ".00":

				if(FloatSelected == true){
					check.setSelected(true);
					FloatSelected = false;
					IntSelected = false;																																																																																																																																																																														
					switchMode("Int");
					return;
				}
				if(model.getMode().equals("Int")){
					clicked = "";
					display2.setText("0.00");
					display1.setText("");
				}
				model.setMode(".00");
				error.setBackground(Color.YELLOW);
				error.setText("F");
				dotButton.setEnabled(true);
				dotButton.setBackground(Color.BLUE);
				FloatSelected = true;

				break;
			}
		}
	}
}
