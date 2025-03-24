import java.io.*;
import java.net.*;

public class client_file {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change if needed
        int port = 5000;
        String filePath = "sample.txt"; // File to send

        try (DatagramSocket clientSocket = new DatagramSocket();
             FileInputStream fileIn = new FileInputStream(filePath)) {

            InetAddress serverIP = InetAddress.getByName(serverAddress);
            byte[] buffer = new byte[4096];

            System.out.println("Sending file to UDP server...");

            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, serverIP, port);
                clientSocket.send(packet);
            }

            // Send "END" message to indicate file transfer completion
            byte[] endMessage = "END".getBytes();
            DatagramPacket endPacket = new DatagramPacket(endMessage, endMessage.length, serverIP, port);
            clientSocket.send(endPacket);

            System.out.println("File sent successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
