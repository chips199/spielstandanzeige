package fenster;

import java.awt.*;
import java.awt.event.*;

public class Window implements KeyListener {
    private Frame f;
    private TextField tf;
    
    public Window() {
        f = new Frame("Key-Listener-Test");
        tf = new TextField("Label");
    }
    
    public void launchFrame() {
        Label label = new Label("Dr�cken Sie eine Taste "
                                + "der Tastatur");
        
        // Hinzuf�gen der Komponenten zum Frame
        f.add(label, BorderLayout.NORTH);
        f.add(tf, BorderLayout.SOUTH);
        
        // Hinzuf�gen des Listeners
        tf.addKeyListener(this);
        tf.requestFocus();
        
        f.setSize(300, 200);
        f.setVisible(true);
    }
    
    // Implementieren der Methoden des Interfaces KeyListener
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        String s = "Taste gedr�ckt: Key = " + e.getKeyChar()
            + " Code = " + e.getKeyCode();
        
        tf.setText(s);
    }

    public void keyReleased(KeyEvent e) {}

    public static void main(String args[]) {
        Window keyTest = new Window();
        keyTest.launchFrame();
    }
}