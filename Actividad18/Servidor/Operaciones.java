
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operaciones extends UnicastRemoteObject implements IOperaciones{
    public Operaciones() throws RemoteException{

    }
    @Override
    public double sumar(double numero1, double numero2) throws RemoteException {
       return numero1 + numero2;
    }

    @Override
    public double restar(double numero1, double numero2) throws RemoteException {
        return numero1 - numero2;
    }

    @Override
    public double multiplicar(double numero1, double numero2) throws RemoteException {
       return numero1 * numero2;
    }

    @Override
    public double dividir(double numero1, double numero2) throws RemoteException {
        return numero1 / numero2;
    }
    
}
