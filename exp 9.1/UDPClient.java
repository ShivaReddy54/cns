import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change to server IP if needed
        int port = 5000;
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.println("Connected to UDP server. Type a sentence to reverse:");

            while (true) {
                System.out.print("Enter text: ");
                String input = scanner.nextLine();
                byte[] sendBuffer = input.getBytes();

                // Send data to the server
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                        InetAddress.getByName(serverAddress), port);
                clientSocket.send(sendPacket);

                // Receive the reversed string from the server
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String reversedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Reversed: " + reversedText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
