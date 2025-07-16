package mypack;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Thread to read from server
        new Thread(() -> {
            String serverMsg;
            try {
                while ((serverMsg = in.readLine()) != null) {
                    System.out.println("Server: " + serverMsg);
                }
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            }
        }).start();

        // Main thread sends message to server
        String clientMsg;
        while ((clientMsg = console.readLine()) != null) {
            out.println(clientMsg);
        }

        socket.close();
    }
}