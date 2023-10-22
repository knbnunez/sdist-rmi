import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;


public class Server {
	private static final int port = 1100; // Recordar mantemer la consistencia en los nros de puerto. Este es el puerto de escucha del servidor, por tanto los clientes deberán apuntar a este para poder comunicarse. Ante alteraciones se debe cambiar en ambos extremos.
    
    // Función auxiliar para mostrar la IP desde la que se recibió la request
    private static void getIPRequest() {
        try {
            String clientIP = RemoteServer.getClientHost();
            System.out.println("Solicitud recibida de " + clientIP);
        } catch (ServerNotActiveException e) {
            System.out.println("Detalles de la petición no disponibles");
        }
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            //
            Remote remote = UnicastRemoteObject.exportObject(new Interface() {
                // Sobreesritura de los métodos declarados en la interface = implementación de métodos.
                @Override
                public float sumar(float numero1, float numero2) throws RemoteException {
                    getIPRequest();
                    return numero1 + numero2;
                };

                @Override
                public float restar(float numero1, float numero2) throws RemoteException {
                    getIPRequest();
                    return numero1 - numero2;
                };

                @Override
                public float multiplicar(float numero1, float numero2) throws RemoteException {
                    getIPRequest();
                    return numero1 * numero2;
                };

                @Override
                public float dividir(float numero1, float numero2) throws RemoteException {
                    getIPRequest();
                    if (numero2 == 0) {
                        return Float.NaN; // Valor especial para indicar división por cero
                    }
                    return numero1 / numero2;
                };
            }, 0); // Fin implementación
        
            //
            Registry registry = LocateRegistry.createRegistry(port); // Creación del registro RMI en el servidor. Los clientes van a solicitar y acceder a objetos registrados en este registro RMI.
            System.out.println("Servidor escuchando en el puerto " + String.valueOf(port)); // Print local
            registry.bind("Calculadora", remote); // Registrar el registro RMI calculadora en el servidor.
    
        } catch (RemoteException e) { 
            e.printStackTrace(); 
        } catch (AlreadyBoundException e) { 
            e.printStackTrace(); 
        }
    }
}