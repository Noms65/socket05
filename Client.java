package client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javazoom.jl.player.Player;

public class Client{
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            System.out.println("choisir 1,2,3");
            
            try {
            
                    BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                    String req = buff.readLine();
                     oos.writeObject(req);
    
                
            } catch (Exception e) {
                System.out.println(e);
            }
          
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            try {
                
                byte[] bytes = (byte[]) ois.readObject();
                ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
                 
                Player player = new Player(bytein);
                player.play();
                
                System.out.println("Message: " + bytes.length);   
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e);
            }
            
            ois.close();
            oos.close();
            //Thread.sleep(1000);
            //Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3
    
    }
}