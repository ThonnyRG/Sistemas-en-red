package Servidor;

import Interfaces.IPersona;
import Interfaces.IPersonaController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonaController extends UnicastRemoteObject implements IPersonaController{
    
    private DbManager dbManager;
    private final static String TABLE = "Personas";

    public PersonaController() throws RemoteException {
        dbManager = new DbManager();
    }    
    
    public IPersona newInstance() throws RemoteException{
        return new Persona();
    }
    
    @Override
    public int add(IPersona persona) throws RemoteException {
        boolean existe = false;
        if (persona.getId() != 0) {
            Map<String, Object> where = new HashMap<>();
            where.put("IdPersona", persona.getId());
            Map<String, Object> registro = dbManager.buscarUno (TABLE, where); 
            existe = registro.size() > 0;
        }
        if (existe ) {
            return ADD_ID_DUPLICADO;
        }
        Map<String, Object> datos = new HashMap<>();
        /*if (persona.getId() != 0) {
            datos.put("IdPersona", persona.getId() );
        }
        if (persona.getNombre() != null) {
            datos.put("Nombre",
            persona.getNombre());
        }
        if (persona.getTelefono() != null) {
            datos.put("Telefono", persona.getTelefono() );
        }
        if (persona.getEmail() != null) {
            datos.put("Email", persona.getEmail());
            }
        
        */
        int respuesta = dbManager.insertar (TABLE, datos);
        return (respuesta > 0) ? ADD_EXITO : ADD_SIN_EXITO;
    }

    @Override
    public int update(IPersona persona) throws RemoteException {
        if(persona.getId() != 0) {
            return UPDATE_ID_NULO;
        }
        
        // Verificar que existe persona con ID recibido
        IPersona personaEncontrado = findOne (persona.getId());
        if (personaEncontrado.getId() == 0) {
            return UPDATE_ID_INEXISTE;
        }
        Map<String, Object> datos = Persona.toMap(persona);
        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", persona.getId() );
        int respuesta = dbManager.actualizar (TABLE, datos, where);

        return (respuesta > 0 ) ? UPDATE_EXITO : UPDATE_SIN_EXITO;
    }

    @Override
    public int delete(IPersona persona) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<IPersona> list() throws RemoteException {
        List<IPersona> listalPersona = new ArrayList<>();
        List< Map<String, Object> > registros = dbManager.listar (TABLE);
        for (Map<String, Object> registro : registros ) {
        IPersona persona = Persona.fromMap(registro);
        listalPersona.add(persona);
        } // Fin for
        return listalPersona;
    }

    @Override
    public IPersona findOne(int idPersona) throws RemoteException {
        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", idPersona);
        Map<String, Object> registro = dbManager.buscarUno (TABLE, where);
        return Persona.fromMap(registro);
    }

}
