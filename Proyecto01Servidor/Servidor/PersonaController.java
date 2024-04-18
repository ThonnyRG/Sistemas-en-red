package Servidor;

import Interfaces.IPersona;
import Interfaces.IPersonaController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonaController extends UnicastRemoteObject implements IPersonaController {

    private DbManager dbManager;
    private final static String TABLE = "Personas";

    public PersonaController() throws RemoteException {
        try {
            dbManager = new DbManager();
        } catch (Exception e) {
            // Manejar la excepción de inicialización de DbManager apropiadamente
            e.printStackTrace();
        }
    }

    public IPersona newInstance() throws RemoteException {
        return new Persona();
    }

    @Override
    public int add(IPersona persona) throws RemoteException {
        if (persona.getId() == 0) {
            return UPDATE_ID_NULO;
        }

        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", persona.getId());
        Map<String, Object> registro = dbManager.buscarUno(TABLE, where);
        if (!registro.isEmpty()) {
            return ADD_ID_DUPLICADO;
        }

        Map<String, Object> datos = Persona.toMap(persona);
        int respuesta = dbManager.insertar(TABLE, datos);
        return (respuesta > 0) ? ADD_EXITO : ADD_SIN_EXITO;
    }

    @Override
    public int update(IPersona persona) throws RemoteException {
        if (persona.getId() == 0) {
            return UPDATE_ID_NULO;
        }

        IPersona personaEncontrado = findOne(persona.getId());
        if (personaEncontrado == null) {
            return UPDATE_ID_INEXISTE;
        }

        Map<String, Object> datos = Persona.toMap(persona);
        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", persona.getId());
        int respuesta = dbManager.actualizar(TABLE, datos, where);

        return (respuesta > 0) ? UPDATE_EXITO : UPDATE_SIN_EXITO;
    }

    @Override
    public int delete(IPersona persona) throws RemoteException {
        IPersona personaTemp = findOne(persona.getId());
        if (personaTemp == null) {
            return DELETE_ID_INEXISTENTE;
        }

        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", persona.getId());
        int respuesta = dbManager.eliminar(TABLE, where);
        return (respuesta > 0) ? DELETE_EXITO : DELETE_SIN_EXITO;
    }

    @Override
    public List<IPersona> list() throws RemoteException {
        List<IPersona> listaPersona = new ArrayList<>();
        List<Map<String, Object>> registros = dbManager.listar(TABLE);
        for (Map<String, Object> registro : registros) {
            IPersona persona = Persona.fromMap(registro);
            if (persona != null) {
                listaPersona.add(persona);
            } else {
                // Manejar la conversión fallida de registro a IPersona apropiadamente
            }
        }
        return listaPersona;
    }

    @Override
    public IPersona findOne(int idPersona) throws RemoteException {
        Map<String, Object> where = new HashMap<>();
        where.put("IdPersona", idPersona);
        Map<String, Object> registro = dbManager.buscarUno(TABLE, where);
        return (registro != null && !registro.isEmpty()) ? Persona.fromMap(registro) : null;
    }

    @Override
    public List<IPersona> find(IPersona persona) throws RemoteException {
        List<IPersona> listaIPersona = new ArrayList<>();

        Map<String, Object> where = Persona.toMap(persona);
        List< Map<String, Object>> registros = dbManager.listar (TABLE, where);
        for (Map<String, Object> registro: registros ) {
            IPersona personaTemp = Persona.fromMap (registro);
            listaIPersona.add(personaTemp);
        } // Fin for
        return listaIPersona;
    }
}
