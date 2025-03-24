import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port + "...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                    String request = input.readLine();
                    String[] parts = request.split(" ");

                    if (parts.length == 3) {
                        String operation = parts[0];
                        int a = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);

                        int result = switch (operation) {
                            case "add" -> a + b;
                            case "subtract" -> a - b;
                            default -> 0;
                        };

                        output.println("Result: " + result);
                    } else {
                        output.println("Invalid Request");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
