<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.server;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public interface ClientProcessor extends Runnable {
	Request getRequest();
	byte[] process();
	
	default void sendResponse(Socket clientSocket) {
		OutputStream output;
		try {
			output = clientSocket.getOutputStream();
			output.write(process());
			output.flush();
			System.out.println("Data sent");
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Cannot send response to client.");
		}
	}
}
=======
package bg.uni.sofia.fmi.mjt.imdb.server;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public interface ClientProcessor extends Runnable {
	Request getRequest();
	byte[] process();
	
	default void sendResponse(Socket clientSocket) {
		OutputStream output;
		try {
			output = clientSocket.getOutputStream();
			output.write(process());
			output.flush();
			System.out.println("Data sent");
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Cannot send response to client.");
		}
	}
}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
