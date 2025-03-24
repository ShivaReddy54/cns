import java.net.*;

public class server_udp {
    public static void main(String[] args) {
        int port = 5000; // Port number

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            byte[] receiveBuffer = new byte[1024];

            System.out.println("UDP Server is running on port " + port + "...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + receivedData);

                // Reverse the input
                String reversedData = new StringBuilder(receivedData).reverse().toString();

                // Send the reversed data back
                byte[] sendBuffer = reversedData.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
