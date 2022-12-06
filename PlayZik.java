package zik;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import javazoom.jl.player.Player;

public class PlayZik {
    public static void main(String args[]) throws IOException, ClassNotFoundException {

        // PlayMusic ("E:/zik/FF9 isaorana anie JEHOVAH.wav");
        try {
            //FileInputStream fileInputStream = new FileInputStream("E:/zik/Telo_mianaka_(Silo)_par_Sity_Rakoto.mp3");
            
            JFileChooser file = new JFileChooser();
            if(file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                FileInputStream fileInputStream = new FileInputStream(file.getSelectedFile());
                System.out.println("You chose to open this file: " +file.getSelectedFile().getName());
                Player player = new Player(fileInputStream);
                System.out.println(fileInputStream.getChannel());
                player.play();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
