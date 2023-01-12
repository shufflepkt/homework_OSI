package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    out.println("Write your name");
                    String userName = in.readLine();

                    out.println("Are you child? (yes/no)");
                    String usersAnswer = in.readLine();

                    if("yes".equals(usersAnswer)) {
                        out.printf("Welcome to the kids area, %s! Let's play!", userName);
                    } else {
                        out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName);
                    }
                }
            }
        }
    }
}