package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import Interfaces.IPersona;

public class Persona extends UnicastRemoteObject implements IPersona{

    private int id;
    private String nombre;
    private String email;
    private String telefono;

    public Persona(int id, String nombre, String email, String telefono) throws RemoteException{
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Persona() throws RemoteException{
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getString() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getString'");
    }

    public static IPersona fromMap(Map<String, Object> map) throws RemoteException{
        IPersona persona = new Persona();
        
        if(map.containsKey("IdPersona") )
        persona.setId((Integer) map.get("IdPersona") );
        if(map.containsKey("Nombre") ) persona.setNombre ((String) map.get("Nombre"));
        if (map.containsKey("Telefono") && map.get("Telefono") != null) persona.setTelefono ((String) map.get("Telefono") );
        if (map.containsKey("Email") ) persona.setEmail( (String) map.get("Email") );

        return persona;

    }
    
}
