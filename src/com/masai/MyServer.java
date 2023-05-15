package com.masai;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {

	static Integer sq(Integer n) {
		return (int) Math.pow(n,2);
		
	}


	public static void connectToServer() {
		// Try connect to the server on an unused port eg 9991. A successful connection
		// will return a socket

		try (ServerSocket ss = new ServerSocket(9991)) {
			Socket socketconnection = ss.accept();

			// Create Input&Outputstreams for the connection

			InputStream inputStreamToserver = socketconnection.getInputStream();
			Scanner sc = new Scanner(inputStreamToserver, "UTF-8");
			
			OutputStream outputstreamfromserver = socketconnection.getOutputStream();
			PrintWriter serverprintout = new PrintWriter(new OutputStreamWriter(outputstreamfromserver),true);

			serverprintout.println("Hello Plese Enter No! Enter Peace to exit.");

			// Have the server take input from the client and echo it back
			// This should be placed in a loop that listens for a terminator text e.g. bye

			boolean check = false;
			while (!check && sc.hasNextLine()) {
				String Line = sc.nextLine();

			   Integer nn=Integer.parseInt(Line);
			   Integer res=sq( nn);
				
				serverprintout.println("    Squra root of " + Line+"  is   "+res);

				if (Line.toLowerCase().equals("peace")) {
					check = true;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		connectToServer();
	}
}
