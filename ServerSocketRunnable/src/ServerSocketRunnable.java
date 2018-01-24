import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerSocketRunnable implements Runnable {
	private String clientRequest;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private final SimpleDateFormat TIME;
	private final SimpleDateFormat DATE;
	private String commands[] = { "-quit", "-echo", "-time", "-date", "-help", "-clrs" };

	/* constructs a socket, date and time formats*/
	public ServerSocketRunnable(Socket socket) { 
		DATE = new SimpleDateFormat("dd MMMMM yyyy");
		TIME = new SimpleDateFormat("hh:mm:ss aaa");
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			/* Gets output and input streams */
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			/*Loops until user enters command quit*/
			while (true) {
				/*Gets command string*/
				clientRequest = (String) input.readObject(); 
				/*If the command is -quit*/
				if (clientRequest.startsWith(commands[0])) { 
					System.out.println("Server Socket: Closing client connection...");
					output.writeObject("SERVER>Connection Closed.");
					break;/*Ends the loop*/
					/*If the command is -echo*/
				} else if (clientRequest.startsWith(commands[1])) { 
					output.writeObject("SERVER>"+ clientRequest.replaceFirst("-echo-", "Echo:"));
					/* If the command is -time*/
				} else if (clientRequest.startsWith(commands[2])) { 
					output.writeObject("SERVER>TIME: " + TIME.format(new Date()));
					/*If the command is -date*/
				} else if (clientRequest.startsWith(commands[3])) { 
					output.writeObject("SERVER>DATE: " + DATE.format(new Date()));
					/*If the command is -help*/
				} else if (clientRequest.startsWith(commands[4])) { 
					output.writeObject("SERVER>AVAILABLE SERVICES:\n"
							+ "quit\n"
							+ "echo\n"
							+ "time\n"
							+ "date\n"
							+ "help\n"
							+ "clrs\n");
					/*If the command is -clrs*/
				} else if (clientRequest.startsWith(commands[5])) { 
					output.writeObject("CLRS:");
				} else { /*If the command is not valid*/
					output.writeObject("SERVER>ERROR: Unrecognized command");
				}
				Thread.sleep(100); 
			}
		} catch ( IOException | InterruptedException e) {
			System.out.println("Server Socket: Closing client connection...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}