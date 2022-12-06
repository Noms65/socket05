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
            
            try {
            
                    //FileInputStream fileInputStream = new FileInputStream(file.getSelectedFile());
                    //System.out.println("You chose to open this file: " +file.getSelectedFile().getName());
                    //Player player = new Player(fileInputStream);
                    
                    //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    //oos.writeObject("Hi Client :You chose to open this file: " +file.getSelectedFile().getName());
                    //System.out.println(fileInputStream.getChannel());
                    //player.play();
            
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
            
            // JFrame frame = new JFrame();
            //         frame.setSize(400, 400);
            //         frame.setLocationRelativeTo(null);
            //         JPanel panel = new JPanel();
            //         panel.setLayout(null);
            //         JLabel[] label = new JLabel[list.length];
            //         for(int i = 0;i<list.length;i++){
            //             label[i]=new JLabel(list[i]);
            //             label[i].setBounds(50, (i+1)*20, 500,20 );
            //             Clic mouse = new Clic(label[i]);
            //             panel.add(label[i]);
            //             panel.addMouseListener(mouse);
            //         }
            //         frame.add(panel);
            //         frame.setVisible(true);

            //close resources
            ois.close();
            oos.close();
            //Thread.sleep(1000);
            //Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3
    
    }
}