package Servidor;

import Interfaces.IPersona;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException; import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging. Logger;

public class Act20Cliente{
public static void main(String[] args) {
    try {
        IPersonaController personaController = 
            (IPersonaController) Naming.lookup("rmi://localhost/Personacontroller");

    IPersona persona = personaController.newInstance();
    persona.setId(1);
    persona.setNombre ("Beto");
    persona.setEmail("beto@mail.com");
    persona.setTelefono ("9211231234");

    System.out.println(personaController.list());
    personaController.add(persona); 
    System.out.println(personaController.list());
    }catch (NotBoundException ex) {
        Logger.getLogger (Act20Cliente.class.getName()).log (Level. SEVERE, null, ex);
    }catch (MalformedURLException ex) {
        Logger.getLogger (Act20Cliente.class.getName()).log (Level. SEVERE, null, ex);
    }catch (RemoteException ex) {
     Logger.getLogger (Act20Cliente.class.getName()).log (Level. SEVERE, null, ex);
                }
    }
}