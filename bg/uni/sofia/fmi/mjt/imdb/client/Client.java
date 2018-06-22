package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.exceptions.NoSuchMovieException;

public class Client {
	private static final int PORT_NUM = 9999;
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchFieldException, NoSuchMovieException {
		
		try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			Socket socket = new Socket(HOST_NAME, PORT_NUM);) {
			
			String input = console.readLine();
			
			sendRequestToServer(input, socket);
			
			Response response = receiveResponse(input, socket);
			response.process();

		} catch (UnknownHostException e) {
			System.out.println("Unknown host.");
		} catch (IOException e) {
			System.out.println("Cannot connect to " + HOST_NAME + " on port " + PORT_NUM);
		}
	}
	
	private static void sendRequestToServer(String request, Socket socket) {
		try (PrintWriter sender = new PrintWriter(socket.getOutputStream())) {
			sender.println(request);
			sender.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Response receiveResponse(String input, Socket socket) throws IOException {
		return ResponseFactory.get(new Request(input), socket.getInputStream());
	}

}
