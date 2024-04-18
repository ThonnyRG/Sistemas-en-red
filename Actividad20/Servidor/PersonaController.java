package Servidor;

import Interfaces.IPersona;

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
    public int add(IPersona persona) throws RemoteException {
        personas.add(persona);
        return 0;
    }

    @Override
    public IPersona newInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newInstance'");
    }

    @Override
    public int delete(IPersona persona) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public IPersona findOne(int idPersona) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }
}

