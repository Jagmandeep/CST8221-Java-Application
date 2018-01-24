import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ClientView extends JPanel {
	private JTextField hostDisp;/*Holds local host*/
	private JComboBox portBox;/*Holds port numbers*/
	private JTextField rqstDisp;/*takes commands from use*/
	private JTextArea terminalDisp;/*Displays all results*/
	private JLabel hostLabel; /*lable for host*/
	private JLabel port;/*lable for port*/
	private JButton connectButton;/*Button to connect to host*/
	private JButton send;/*Button to send request entered by user*/
	Controller controller = new Controller(); /*only one controller used for my assignment*/
	private Socket serverSocket = null;
	private ObjectOutputStream output;/*holds output stream*/
	private ObjectInputStream input;/*holds in-coming input stream*/
	private String[] portList = { "","8089", "65001", "65535" };/*ports for port box*/
	public ClientView(){

		setLayout(new BorderLayout());
		/*Pannel to hold connection and clientRequest panel*/
		JPanel topPanel= new JPanel(new BorderLayout());
		JPanel connectionPannel= new JPanel(new BorderLayout());
		LineBorder roundedLineBorder = new LineBorder(Color.RED, 10, true);
		TitledBorder roundedTitledBorder = new TitledBorder(roundedLineBorder, "CONNECTION");
		connectionPannel.setBorder(roundedTitledBorder);
		/*Specifications for host display*/
		hostDisp = new JTextField("localhost");
		hostDisp.setCaretPosition(0);
		hostDisp.setHorizontalAlignment(JTextField.LEFT);
		hostDisp.setBackground(Color.WHITE);
		/*hostDisp.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1,Color.GRAY));*/
		hostDisp.setMargin(new Insets(0, 5, 0, 5));
		/*Specifications for host lable*/
		hostLabel = new JLabel("Host:", SwingConstants.CENTER);
		hostLabel.setDisplayedMnemonic('H');
		hostLabel.setBorder( BorderFactory.createEmptyBorder(0,0,0,0));
		hostLabel.setPreferredSize(new Dimension (40 , 20));
		/*hostPannel holds host label and host display*/
		JPanel hostPannel= new JPanel(new BorderLayout(5,5));
		hostPannel.setBorder(new EmptyBorder(5, 0, 5, 0) );
		hostPannel.add(hostLabel,BorderLayout.WEST );
		hostPannel.add(hostDisp,BorderLayout.CENTER );
		/*Specification for port label*/
		port = new JLabel("Port:", SwingConstants.CENTER);
		port.setDisplayedMnemonic('P');
		port.setBorder( BorderFactory.createEmptyBorder(0,0,0,0));
		/*Specification for port box*/
		portBox = new JComboBox(portList);
		portBox.setBackground(Color.WHITE);
		portBox.setPreferredSize(new Dimension(100, 20));
		portBox.setEditable(true);
		/*Specification for connect button*/
		connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(100, 20));
		connectButton.setBackground(Color.RED);
		connectButton.addActionListener(new Controller());
		connectButton.setActionCommand("Connect");
		connectButton.setOpaque(true);
		/*port pannel holds port label, port box and */
		JPanel portPannel= new JPanel(new FlowLayout(FlowLayout.LEFT,9,9));
		portPannel.add(port);
		portPannel.add(portBox);
		portPannel.add(connectButton);

		connectionPannel.add(hostPannel,BorderLayout.NORTH );
		connectionPannel.add(portPannel,BorderLayout.SOUTH );
		/*Text field to enter commands for server*/
		rqstDisp = new JTextField("Type a request command",40);
		rqstDisp.setHorizontalAlignment(JTextField.LEFT);
		rqstDisp.setBackground(Color.WHITE);
		//rqstDisp.setPreferredSize( new Dimension (50 , 20) );
		rqstDisp.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1,Color.GRAY));
		rqstDisp.setOpaque(true);
		/*Specification for send button*/
		send = new JButton("Send");
		send.setMnemonic('S');			
		send.setPreferredSize( new Dimension (100 , 20) );
		send.addActionListener(new Controller());
		/*Adding action command to send button*/
		send.setActionCommand("Send");
		send.setOpaque(true);
		send.setEnabled(false);
		/*Client request panel has client request display and send button*/
		JPanel clientRequestPanel= new JPanel(new FlowLayout(FlowLayout.LEFT,9,9));
		LineBorder roundedBorder = new LineBorder(Color.BLUE, 10);
		TitledBorder titledBorder = new TitledBorder(roundedBorder, "CLIENT REQUEST");
		clientRequestPanel.setBorder( titledBorder );
		clientRequestPanel.add(rqstDisp,BorderLayout.CENTER);
		clientRequestPanel.add(send,BorderLayout.EAST);

		topPanel.add(connectionPannel, BorderLayout.NORTH );
		topPanel.add(clientRequestPanel, BorderLayout.SOUTH );
		/*terminal panel has terminal display with scroll bar attached to it*/
		JPanel terminal= new JPanel(new BorderLayout());
		roundedBorder = new LineBorder(Color.BLACK, 10, true);
		titledBorder = new TitledBorder(roundedBorder, "TERMINAL",2,2);
		terminal.setBorder( titledBorder );

		terminalDisp = new JTextArea();
		terminalDisp.setBackground(Color.WHITE);
		terminalDisp.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1,Color.GRAY));
		terminalDisp.setEditable(false);
		/*Adding scroll bar to our gui*/
		JScrollPane scroll = new JScrollPane(terminalDisp);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		/*scroll bar is attached to GUI */
		terminal.add(scroll,BorderLayout.CENTER);
		/*Top panel and terminal is added to frame in main class*/
		this.add(topPanel, BorderLayout.NORTH );
		this.add(terminal, BorderLayout.CENTER);
	}// end of constructor

	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String clintReqst;
			String sendReqst;
			String command = e.getActionCommand();/*Holds command entered by user*/
			int port;
			String validHost;
			/*When connect button is clicked*/
			if (command.equals("Connect")){
				try {	
					/*Conversion of port to integer*/
					port = Integer.parseInt((String) portBox.getSelectedItem());
				}catch(NumberFormatException ex){
					port = 65535;
				}
				/*gets text from host display where user enters commands*/
				validHost = hostDisp.getText();
				if (!validHost.equals("localhost")) { // Checks if hostName is valid
					terminalDisp.setText("CLIENT>ERROR: Unknown Host.");
					return;
				}
				try{
					serverSocket = new Socket();
					/*	serverSocket.connect(new InetSocketAddress(hostDisp.getText(), Integer.parseInt((String) portBox.getSelectedItem())));*/
					/*It connects socket*/
					InetSocketAddress address = new InetSocketAddress( validHost , port );
					serverSocket.connect(address, 10000);
					output = new ObjectOutputStream(serverSocket.getOutputStream());
					input = new ObjectInputStream(serverSocket.getInputStream());		
				}catch(IOException ex){
					terminalDisp.setText("CLIENT>ERROR: Connection refused: server is not available. Check port or restart server.\n");
					return;
				}
				/*Updates buttons after establishing connection*/
				connectButton.setEnabled(false);
				connectButton.setBackground(Color.BLUE);
				send.setEnabled(true);
				/* Prints on terminal connection details */
				terminalDisp.setText("Connected to " + serverSocket + "\n");	
			}
			/*Case to handle when send button is clicked */
			else if (command.equals("Send")){
				clintReqst = rqstDisp.getText();
				try { /* send request to server*/
					output.writeObject(clintReqst);
				} catch (IOException ex) {
					terminalDisp.append("SERVER>ERROR: an unexpected error has occured\n");
				}
				try {
					 /*Gets response from server*/
					clintReqst = (String) input.readObject();
					/* if response is CLRS command*/
					if (clintReqst.equals("CLRS:")) { 
						terminalDisp.setText("");
						return;
						/*If response is QUIT command*/
					} else if (clintReqst.equals("SERVER>Connection Closed.")) { 
						/*Closes streams and socket*/
						output.close();
						input.close();
						serverSocket.close();
						/* Display message*/
						terminalDisp.append(clintReqst);
						terminalDisp.append("\nCLIENT>Connection Closed.\n");
						/* reset buttons after closing connection*/
						send.setEnabled(false);
						connectButton.setEnabled(true);
						connectButton.setBackground(Color.RED);
						return;
					}
					// If other command, displays response
					terminalDisp.append(clintReqst + "\n");
					/*To handel unexpected erroes*/
				} catch (ClassNotFoundException | IOException ex) {
					terminalDisp.append("SERVER>ERROR: an unexpected error has occured\n");
				}
			}
		}
	}//end of inner class thread
}//end of class
