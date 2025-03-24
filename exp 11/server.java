import java.io.*;
import java.util.concurrent.*;

public class server {
    private static final int CHUNK_SIZE = 1024; // 1 KB
    private static final BlockingQueue<byte[]> queue = new ArrayBlockingQueue<>(10);
    private static final String INPUT_FILE = "sample.txt";
    private static final String OUTPUT_FILE = "received_file.txt";
    private static final byte[] END_SIGNAL = new byte[0]; // Special signal to indicate EOF

    public static void main(String[] args) {
        Thread sender = new Thread(() -> sendFile(INPUT_FILE));
        Thread receiver = new Thread(() -> receiveFile(OUTPUT_FILE));

        sender.start();
        receiver.start();

        try {
            sender.join();
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(String filePath) {
        try (FileInputStream fileInput = new FileInputStream(filePath)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = fileInput.read(buffer)) != -1) {
                queue.put(buffer.clone()); // Put copy of buffer into queue
            }

            queue.put(END_SIGNAL); // End signal
            System.out.println("File Sent Successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(String filePath) {
        try (FileOutputStream fileOutput = new FileOutputStream(filePath)) {
            while (true) {
                byte[] data = queue.take(); // Take from queue

                if (data == END_SIGNAL) {
                    break; // End of file
                }

                fileOutput.write(data);
            }

            System.out.println("File Received and Saved as " + filePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
