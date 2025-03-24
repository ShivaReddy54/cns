import java.io.*;
import java.net.*;

public class server_fifo {
    public static void main(String[] args) {
        int port = 5000; // Use any available port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is waiting for connection on port " + port);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = reader.readLine();
            System.out.println("Received message: " + message);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
