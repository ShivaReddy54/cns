import java.rmi.registry.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Locate the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup the remote object
            Calculator stub = (Calculator) registry.lookup("CalculatorService");

            // User input for numbers
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            // Call remote methods
            int sum = stub.add(num1, num2);
            int difference = stub.subtract(num1, num2);

            // Display results
            System.out.println("Addition Result: " + sum);
            System.out.println("Subtraction Result: " + difference);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
