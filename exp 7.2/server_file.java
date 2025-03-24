import java.io.*;
import java.net.*;

public class server_file {
    public static void main(String[] args) {
        int port = 5000; 
        String saveFilePath = "file.txt"; 

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is waiting for a connection on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Receiving file
                InputStream in = clientSocket.getInputStream();
                FileOutputStream fileOut = new FileOutputStream(saveFilePath);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                }

                fileOut.close();
                System.out.println("File received and saved as " + saveFilePath);

                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
