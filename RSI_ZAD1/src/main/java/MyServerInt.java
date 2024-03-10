import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyServerInt extends Remote {
    String getDescription(String text)
        throws RemoteException;
    String doMath(String input)
        throws RemoteException;
}
