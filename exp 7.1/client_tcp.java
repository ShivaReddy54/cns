import java.io.*;
import java.net.*;

public class client_tcp {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server. Type a sentence to reverse:");

            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input); 
                String reversed = in.readLine(); 
                System.out.println("Reversed: " + reversed);
                System.out.println("Enter another sentence (or press Ctrl+C to exit):");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
