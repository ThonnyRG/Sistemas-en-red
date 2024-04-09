import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servidorRMI {
    public static void main(String[] args) {
        try {
            // Create the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create an instance of the remote object
            IOperaciones interfaz = new Operaciones();

            // Bind the remote object to the RMI registry
            Naming.rebind("rmi://localhost/Operaciones", interfaz);

            System.out.println("Escuchando...");
        } catch (RemoteException ex) {
            Logger.getLogger(servidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(servidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
