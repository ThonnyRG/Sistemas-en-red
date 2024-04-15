package Act19;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interfaces.IPersona;

public class Actividad19 {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IPersona persona1 = new Persona(1, "Pepe", "Pepe@mail.com", "92312345");
            Naming.rebind("rmi://localhost/Persona", persona1);
            System.out.println("escuchando");
        } catch (RemoteException ex) {
                Logger.getLogger(Actividad19.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Actividad19.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    
    }
}
