package bg.uni.sofia.fmi.mjt.imdb.server;

import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class PosterClientProcessor implements ClientProcessor {
	private Socket clientSocket;
	private Request request;
	private Cache cache;
	
	public PosterClientProcessor(Socket clientSocket, Request request, Cache cache) {
		this.clientSocket = clientSocket;
		this.request = request;
		this.cache = cache;
	}
	
	@Override
	public void run() {
		ClientProcessor.super.sendResponse(clientSocket);
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public byte[] process() {
		return cache.fetchPoster(this);
	}

}
