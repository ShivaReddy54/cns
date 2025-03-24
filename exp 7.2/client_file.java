import java.io.*;
import java.net.*;

public class client_file {
    public static void main(String[] args) {
        String serverAddress = "localhost"; 
        int port = 5000;
        String filePath = "recever.txt"; 

        try (Socket socket = new Socket(serverAddress, port);
             FileInputStream fileIn = new FileInputStream(filePath);
             OutputStream out = socket.getOutputStream()) {

            System.out.println("Connected to server. Sending file...");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
