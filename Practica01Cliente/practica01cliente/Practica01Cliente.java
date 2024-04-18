import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interfaces.IPersona;
import Interfaces.IPersonaController;

public class Practica01Cliente {
    public static void main(String[] args) {
        try {
            IPersonaController personaController = (IPersonaController) Naming.lookup("rmi://localhost/PersonaController");
            IPersona persona = personaController.newInstance(); 
            persona.setEmail("alex@mail.com");
            List<IPersona> lista = personaController.list();

            for (IPersona personaTemp : lista){
                System.out.println(personaTemp.getString());
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Practica01Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
