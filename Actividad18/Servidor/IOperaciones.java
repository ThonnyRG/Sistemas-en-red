
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperaciones extends Remote{

    double sumar(double numero1, double numero2) throws RemoteException;
    
    double restar(double numero1, double numero2) throws RemoteException;

    double multiplicar(double numero1, double numero2) throws RemoteException;

    double dividir(double numero1, double numero2) throws RemoteException;

}
