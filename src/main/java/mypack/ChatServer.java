package mypack;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for client...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Thread to read messages from client
        new Thread(() -> {
            String clientMsg;
            try {
                while ((clientMsg = in.readLine()) != null) {
                    System.out.println("Client: " + clientMsg);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }).start();

        // Main thread sends messages to client
        String serverMsg;
        while ((serverMsg = console.readLine()) != null) {
            out.println(serverMsg);
        }

        clientSocket.close();
        serverSocket.close();
    }
}
