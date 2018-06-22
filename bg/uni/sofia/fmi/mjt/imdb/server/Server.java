<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class Server {
	private static final int PORT_NUM = 9999;
	
	public static void main(String[] args) throws InterruptedException {
		boolean isRunning = true;
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_NUM);
			System.out.println("Listening on port " + PORT_NUM);
			Cache cache = new Cache();
		
			while (isRunning) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connected");
				BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				Request request = new Request(input.readLine());
				Thread client = new Thread(ClientProcessors.get(clientSocket, request, cache));
				client.start();
			}
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Can't create server.");
		}
	}
	
}
=======
package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class Server {
	private static final int PORT_NUM = 9999;
	
	public static void main(String[] args) throws InterruptedException {
		boolean isRunning = true;
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_NUM);
			System.out.println("Listening on port " + PORT_NUM);
			Cache cache = new Cache();
		
			while (isRunning) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connected");
				BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				Request request = new Request(input.readLine());
				Thread client = new Thread(ClientProcessors.get(clientSocket, request, cache));
				client.start();
			}
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Can't create server.");
		}
	}
	
}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
