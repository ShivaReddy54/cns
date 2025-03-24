import java.io.*;

public class pipes {
    public static void main(String[] args) throws IOException {
        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn = new PipedInputStream(pipeOut);

        // Create a child thread (simulating another process)
        Thread child = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(pipeIn))) {
                String message = reader.readLine();
                System.out.println("Child Process Received: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        child.start();

        // Parent writes data to the pipe
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(pipeOut))) {
            writer.write("Hello from Parent Process!\n");
            writer.flush();
        }

        // Wait for child to finish
        try {
            child.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
