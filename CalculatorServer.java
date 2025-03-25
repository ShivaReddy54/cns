import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create remote object
            CalculatorImpl calc = new CalculatorImpl();

            // Start RMI registry (if not started already)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object
            registry.rebind("CalculatorService", calc);

            System.out.println("Calculator Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
