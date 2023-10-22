import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client {
	private static final String serverIP = "localhost"; 
	private static final int server_port = 1100; 
	
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(serverIP, server_port);
        Interface stub_interface = (Interface) registry.lookup("Calculadora"); //Busca en el registro RMI el objeto calculadora.
        Scanner sc = new Scanner(System.in);
        int eleccion;
        float numero1, numero2, resultado = 0;
        String menu = "\n[-1] Salir\n[0] Sumar\n[1] Restar\n[2] Multiplicar\n[3] Dividir\nElige: ";
        do {
            System.out.println(menu);

            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                eleccion = -1;
            }

            if(eleccion != -1){

            	System.out.println("Ingresa el número 1: ");
            	try {
                	numero1 = Float.parseFloat(sc.nextLine());
            	} catch(NumberFormatException e){
            		numero1 = 0;
            	}

            	System.out.println("Ingresa el número 2: ");
            	try {
                	numero2 = Float.parseFloat(sc.nextLine());
            	} catch(NumberFormatException e){
            		numero2 = 0;
            	}
                
                switch (eleccion) {
	                case 0:
	                    resultado = stub_interface.sumar(numero1, numero2);
	                    break;
	                case 1:
	                    resultado = stub_interface.restar(numero1, numero2);
	                    break;
	                case 2:
	                    resultado = stub_interface.multiplicar(numero1, numero2);
	                    break;
	                case 3:
	                    resultado = stub_interface.dividir(numero1, numero2);
	                    break;
	            }

                System.out.println("Resultado = " + String.valueOf(resultado));
                System.out.println("Presiona ENTER para continuar");
                sc.nextLine();
            }
        } while (eleccion != -1);
    }
}