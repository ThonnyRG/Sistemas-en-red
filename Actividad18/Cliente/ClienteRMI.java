import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRMI {
    public static void main(String[] args) throws NotBoundException {
        try {
            IOperaciones objetoRemoto = (IOperaciones) Naming.lookup("rmi://localhost/Operaciones");
            double resultado = objetoRemoto.restar(10, 20);
            System.out.println(resultado);

            resultado = objetoRemoto.restar(10, 20);
            System.out.println(resultado);
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}
