import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implementing the remote interface
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    // Constructor
    protected CalculatorImpl() throws RemoteException {
        super();
    }

    // Implementation of add method
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    // Implementation of subtract method
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}
