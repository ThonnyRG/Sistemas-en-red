package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPersona extends Remote{
    int getId() throws RemoteException;
    void setId(int id) throws RemoteException;

    String getNombre() throws RemoteException;
    void setNombre(String nombre) throws RemoteException;

    String getEmail() throws RemoteException;
    void setEmail(String Email) throws RemoteException;

    String getTelefono() throws RemoteException;
    void setTelefono(String Telefono) throws RemoteException;

    String getString() throws RemoteException;

}
