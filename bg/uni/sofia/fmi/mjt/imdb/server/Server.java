package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private static final int PORT_NUM = 9999;
	
	public static void main(String[] args) throws InterruptedException {
		boolean isRunning = true;
		
		try (ServerSocket serverSocket = new ServerSocket(PORT_NUM)) {
			System.out.println("Listening on port " + PORT_NUM);
			Cache cache = new Cache();
		
			while (isRunning) {
				ClientListener client = new ClientListener(serverSocket.accept(), cache);
				client.start();
			}
			
		} catch (IOException e) {
			System.out.println("Can't create server!");
		} 
	}
}
