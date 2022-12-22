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
 /**
  * Main
  */
 public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setSize(300,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Canvas c = new Canvas();
        c.setBackground(Color.BLACK);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        p.add(c);
        f.add(p);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        MediaPlayerFactory mpf = new MediaPlayerFactory();

        EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
        emp.setVideoSurface(mpf.newVideoSurface(c));

        emp.setEnableMouseInputHandling(true);
        emp.setEnableKeyInputHandling(false);

        emp.prepareMedia("Niova Ianao (Nanie) - Tsanta Randri featuring Sity Rakoto.mp4");
        emp.play();
    }
 }