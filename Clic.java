package clicop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;

import javax.swing.JLabel;

import javazoom.jl.player.Player;

public class Clic implements MouseListener {
    JLabel label;

    public Clic(JLabel label) {
        this.label = label;
    }

    public void mouseClicked(MouseEvent e) {
        try {
            System.out.println(this.label.getText());
            FileInputStream fileInputStream = new FileInputStream(this.label.getText());
            // System.out.println("You chose to open this file: "
            // +file.getSelectedFile().getName());
             Player player = new Player(fileInputStream);
             player.play();

        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
