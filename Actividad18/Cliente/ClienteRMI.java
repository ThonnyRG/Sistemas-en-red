

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi. NotBoundException;
import java.rmi.RemoteException;
import java.util.logging. Level;
import java.util.logging.Logger;


public class ClienteRMI {
    public static void main(String[] args) {
        try {
            IOperaciones objetoRemoto = (IOperaciones) Naming.lookup("rmi://localhost/Operaciones");
            double resultado = objetoRemoto.sumar(10, 20);
            System.out.println(resultado);
        }catch (NotBoundException ex) {
            Logger.getLogger (ClienteRMI.class.getName()).log (Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
            Logger.getLogger (ClienteRMI.class.getName()).log (Level. SEVERE, null, ex);
            } catch (RemoteException ex) {
            Logger.getLogger (ClienteRMI.class.getName()).log (Level. SEVERE, null, ex);
    }
}
}
