package com.java.net.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
//	ServerSocket server = new ServerSocket(1024,2);
	ServerSocket server;
	public Server() {
		 try {
			server = new ServerSocket(11111, 2);
//			server.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
