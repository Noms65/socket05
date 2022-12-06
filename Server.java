package server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import javazoom.jl.player.Player;

public class Server {
    // static ServerSocket variable
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

            // FileInputStream fileInputStream = new
            // FileInputStream("E:/zik/Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3");

            // FileInputStream fileInputStream = new FileInputStream("E:/zik/"+message);
            // System.out.println("You chose to open this file: "
            // +file.getSelectedFile().getName());
            // Player player = new Player(fileInputStream);

            // player.play();
            // String[] listMp3 = new String[2];
            // listMp3[0] = "E:/zik/Andriamanitra (Dadah Rabel - Francia et Rija
            // Ramanantoanina.mp3";
            // listMp3[1] = "E:/zik/Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3";
            // listMp3[2] = "E:/zik/FF9 isaorana anie JEHOVAH.wav";

            if(message != null && Integer.valueOf(message)<4){
            try {
                
                String [] song=new String[4];
                song[0] = "";
                song[1] = "E:/zik/Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3";
                song[2] = "E:/zik/Andriamanitra (Dadah Rabel - Francia et Rija Ramanantoanina.mp3";
                song[3] ="E:/zik/RAHA TSINJONAO PB.mp3";
                for (int i = 0; i < song.length; i++) {
                    if(i == Integer.valueOf(message)){
                        byte[] bytes = Files.readAllBytes(Paths.get(song[i]));
                        //ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
                System.out.println(bytes.length);
                //Player player = new Player(bytein);
                //player.play();

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