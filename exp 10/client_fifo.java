import java.io.*;
import java.net.*;

public class client_fifo {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter message to send: ");
            String message = console.readLine();
            writer.write(message + "\n");
            writer.flush();
            System.out.println("Message sent: " + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
