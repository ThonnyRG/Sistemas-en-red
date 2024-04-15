package Act19Cliente.Act19Cliente;


import Interfaces.IPersona;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi. RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Act19Cliente {
public static void main(String[] args) {
    try {
        IPersona persona = (IPersona) Naming.lookup("rmi://localhost/Persona");
        System.out.printf("Id: %d, Nombre: %s, Email: %s, Telefono: %s:",
        persona.getId(),
        persona.getNombre(),
        persona.getEmail(),
        persona.getTelefono());
    } catch (NotBoundException ex) {
        Logger.getLogger (Act19Cliente.class.getName()).log (Level.SEVERE, null, ex);
    } catch (MalformedURLException ex) {
        Logger.getLogger (Act19Cliente.class.getName()).log (Level.SEVERE, null, ex);
    } catch (RemoteException ex) {
        Logger.getLogger (Act19Cliente.class.getName()).log (Level.SEVERE, null, ex);
    }

}
}
