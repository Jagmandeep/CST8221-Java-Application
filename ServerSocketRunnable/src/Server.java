import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) {
		int port = 65535;/*port initialized as 65535 as default*/
		ServerSocket serverSocket ;
		if(args.length > 0){
			try{
				/*Converts argument to integer*/
				port = Integer.valueOf(args[0]);
				System.out.println("Using port:" + port);
				/*If port is out of range*/
				if(port>35565){
					port = 65535;
				}
				/*Checks for the number format exception
				 * if user enters wrong port
				 * */
			}catch (NumberFormatException e){
				System.out.println(args[0] + "Invalid port");
				System.out.println("Using default port: "+port);
			}	
		}
		if(port == 65535 ){
			System.out.println("Using default port: "+port);
		}
		else {
			System.out.println("Using port: " + port);
		}
		/*Setup the Execcutor service*/
		try {
			serverSocket = new ServerSocket(port);
			while(true){ 
				Socket clientSocket = serverSocket.accept();
				ServerSocketRunnable serverRunnable = new ServerSocketRunnable(clientSocket);
				Thread thread = new Thread(serverRunnable);
				System.out.println("Connecting to a client " + clientSocket.toString());	
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("Unable to create server socket. ");
		}			

	}//end of main
}//end of class
