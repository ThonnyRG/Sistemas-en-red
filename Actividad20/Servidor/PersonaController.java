package Servidor;

import Interfaces.IPersona;
import Interfaces.IPersonaController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

public class PersonaController extends UnicastRemoteObject implements IPersonaController{
    
    private List<IPersona> personas;

    public PersonaController() throws RemoteException {
        personas = new ArrayList<>();
    }

    public List<IPersona> list() throws RemoteException {
        return personas;
    }
    public void update (IPersona persona) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    @Override
    public void add(IPersona persona) throws RemoteException {
        personas.add(persona);
    }
}

