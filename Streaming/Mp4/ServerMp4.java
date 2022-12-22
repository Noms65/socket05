package mp4;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ServerMp4 {
    private static ServerSocket server;
    // socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        // create the socket server object
        server = new ServerSocket(port);

        
        // keep listens indefinitely until receives 'exit' call or program terminates
        System.out.println("Waiting for the client request");
        // creating socket and waiting for client connection
        
        while (true) {
            Socket socket = server.accept();
            // read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            // create ObjectOutputStream object
            
            if(message != null && Integer.valueOf(message)<4){
            try {
                
                String[] choix1  = new String[4];
                choix1[0] ="";
                choix1[1] = "Niova Ianao (Nanie) - Tsanta Randri featuring Sity Rakoto.mp4";
                choix1[2] = "_REÇOIS_L_ADORATION_EXO_AU_MADAGASCAR(360p).mp4";
                choix1[3] = "Jazz Music Korea - Autumn Leaves.mp4";
                
                for (int i = 0; i < choix1.length; i++) {
                    if(i == Integer.valueOf(message)){
                        byte[] bytes = Files.readAllBytes(Paths.get(choix1[i]));
                    
                        //ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
                System.out.println(bytes.length);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(bytes);
                
                ois.close();
                oos.close();
                }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }

            socket.close();
        }
        }
    }
}
