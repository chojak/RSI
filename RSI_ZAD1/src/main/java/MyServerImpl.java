import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {
    int i = 0;
    protected MyServerImpl() throws RemoteException {
        super();
    }
    public String getDescription(String text) throws RemoteException {
        i++;
        System.out.println("MyServerImpl.getDescription: " + text + " " + i);
        return "getDescription: " + text + " " + i;
    }

    @Override
    public String doMath(String input) throws RemoteException {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 3)
            return "Podano zły ciąg znaków";

        Double firstNumber = Double.parseDouble(splitInput[0]);
        Double secondNumber = Double.parseDouble(splitInput[2]);
        String mathSymbol = splitInput[1];
        
        System.out.println();
        System.out.println("Przyjęto równanie: " + input);

        switch (mathSymbol) {
            case "+":
                Double result = firstNumber + secondNumber;
                System.out.println("Wynik: " + result);
                return Double.toString(result);
            case "-":
                result = firstNumber - secondNumber;
                System.out.println("Wynik: " + result);
                return Double.toString(result);
            case "*":
                result = firstNumber * secondNumber;
                System.out.println("Wynik: " + result);
                return Double.toString(firstNumber * secondNumber);
            case "/":
                if (secondNumber == 0)
                    return "Nie można dzielić przez 0";
                
                result = firstNumber * secondNumber;
                System.out.println("Wynik: " + result);
                return Double.toString(result);
            default:
                System.out.println("Podano nieobłużony znak");
                return "Podano nieobłużony znak";
        }
    }
}