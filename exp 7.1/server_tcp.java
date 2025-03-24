import java.io.*;
import java.net.*;

public class server_tcp {
    public static void main(String[] args) {
        int port = 5000; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Received: " + input);
                    String reversed = new StringBuilder(input).reverse().toString();
                    out.println(reversed);
                }

                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
