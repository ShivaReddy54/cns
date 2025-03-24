import java.io.*;
import java.net.*;

public class server_file {
    public static void main(String[] args) {
        int port = 5000; // Port to listen on
        String saveFilePath = "received_file.txt"; // File to save

        try (DatagramSocket serverSocket = new DatagramSocket(port);
             FileOutputStream fileOut = new FileOutputStream(saveFilePath)) {

            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("UDP Server is waiting for file transfer on port " + port + "...");

            while (true) {
                serverSocket.receive(packet);
                int bytesReceived = packet.getLength();

                // Stop receiving if "END" message is received
                String message = new String(packet.getData(), 0, bytesReceived);
                if (message.equals("END")) {
                    System.out.println("File transfer completed. Saved as " + saveFilePath);
                    break;
                }

                fileOut.write(packet.getData(), 0, bytesReceived);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
