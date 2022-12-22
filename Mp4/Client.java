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

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.lang.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

//import mouse.Click;

public class Client {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        // get the localhost IP address, if server is running on some other IP, you need
        // to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        // establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);
        // write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
        System.out.println("choisir 1 , 2 , 3");
        try {

            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String req = buff.readLine();
            oos.writeObject(req);

        } catch (Exception e) {
            System.out.println(e);
        }

        // read the server response message
        ois = new ObjectInputStream(socket.getInputStream());
        try {
            byte[] bytes = (byte[]) ois.readObject();

            ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
            System.out.println(bytes.length);
            JFrame f = new JFrame();
            f.setLocationRelativeTo(null);
            f.setSize(300, 300);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

            Canvas c = new Canvas();
            c.setBackground(Color.BLACK);

            JPanel p = new JPanel();
            p.setLayout(new BorderLayout());

            p.add(c);
            f.add(p);

            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

            MediaPlayerFactory mpf = new MediaPlayerFactory();

            EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
            emp.setVideoSurface(mpf.newVideoSurface(c));

            emp.setEnableMouseInputHandling(true);
            emp.setEnableKeyInputHandling(false);

            File file = new File("file.mp4");
            
            System.out.println("Mamorina");
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println("Vita");
            FileOutputStream outFile = new FileOutputStream(file);
            outFile.write(bytes);

            emp.prepareMedia(file.getPath());

            emp.play();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        ois.close();
        oos.close();
        // Thread.sleep(1000);
        // Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3

    }
}