package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class ClientListener extends Thread {
      private Socket clientSocket;
      private Cache serverCache;
      
      public ClientListener(Socket clientSocket, Cache serverCache) {
    	  this.clientSocket = clientSocket;
    	  this.serverCache = serverCache;
      }
      
    @Override
  	public void run() {
  		try (BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
  			Request receivedRequest = new Request(socketReader.readLine());
  			Fetcher fetcher = FetcherFactory.get(receivedRequest, serverCache);
  			sendResponseToClient(fetcher.fetch());	
  		} catch (IOException e) {
  			System.out.println("Can't read from " + clientSocket.getInetAddress() + ".");
  		}
  	}
    
    private void sendResponseToClient(byte[] response) throws IOException {
    	try (OutputStream socketWriter = clientSocket.getOutputStream()) {
			socketWriter.write(response);
			socketWriter.flush();
			System.out.println("Response successfully sent to " + clientSocket.getInetAddress() + ".");
		} catch (IOException e) {
			System.out.println("Cannot send response to " + clientSocket.getInetAddress() + ".");
		} finally {
			clientSocket.close();
		}
    }
}
