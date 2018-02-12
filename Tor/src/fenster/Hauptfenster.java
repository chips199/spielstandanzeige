package fenster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JTextField;

import thread.Zeitthread;

public class Hauptfenster implements KeyListener {
	private JFrame frame;
	private JTextField spielstand, mannschaften;
	private static JTextField zeit;
	private Toolkit toolkit;
	private Dimension dimension;
	
	private String nameMannschaft1, nameMannschaft2;
	private boolean spielPausiert = true;
	private int manschaft1 = 0, manschaft2 = 0;
	private double breiteFenster, höheFenster;

	private Zeitthread zeitthread;

	public Hauptfenster(int spielzeit, String nameMannschaft1, String nameMannschaft2) {
		zeitthread = new Zeitthread(spielzeit);
		zeitthread.start();
		
		this.nameMannschaft1 = nameMannschaft1;
		this.nameMannschaft2 = nameMannschaft2;
		
		// Fenster erstellen
		frame = new JFrame();
		
		// Bildschrimgröße auslesen
		toolkit = Toolkit.getDefaultToolkit();
		dimension = toolkit.getScreenSize();
		breiteFenster = dimension.getWidth();
		höheFenster = dimension.getHeight();
		frame.setLayout(null);
		
		// Maus verschwinden lassen
		frame.setCursor(java.awt.Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR), new java.awt.Point(0, 0), "NOCURSOR"));
		
		// Fenster auf Vollbild setzen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		// Ausgabe der Mannschaften
		mannschaften = new JTextField(this.nameMannschaft1 + " : " + this.nameMannschaft2);
		mannschaften.setBounds(20, 20, (int) (breiteFenster - 40),(int) (höheFenster / 3 - 30));
		mannschaften.setFont(getFont());
		mannschaften.setHorizontalAlignment(JTextField.CENTER);
		mannschaften.setBorder(null);
		mannschaften.setForeground(Color.black);
		mannschaften.setEditable(false);
		mannschaften.addKeyListener(this);
		mannschaften.requestFocus();
		frame.add(mannschaften);

		// Ausgabe des Spielstandes
		spielstand = new JTextField("0:0");
		spielstand.setBounds(20, mannschaften.getHeight() + 40, (int) (breiteFenster - 40),(int) (höheFenster / 3 - 30));
		spielstand.setFont(getFont());
		spielstand.setHorizontalAlignment(JTextField.CENTER);
		spielstand.setBorder(null);
		spielstand.setForeground(Color.black);
		spielstand.setEditable(false);
		// Keylistener hinzufügen
		spielstand.addKeyListener(this);
		spielstand.requestFocus();
		frame.add(spielstand);
		
		// Ausgabe der Spielzeit
		zeit = new JTextField(spielzeit + ":00");
		zeit.setBounds(20, (int) mannschaften.getHeight() * 2 + 60, (int) (breiteFenster - 40),(int) (höheFenster / 3 - 30));
		zeit.setFont(getFont());
		zeit.setHorizontalAlignment(JTextField.CENTER);
		zeit.setBorder(null);
		zeit.setForeground(Color.black);
		zeit.setEditable(false);
		frame.add(zeit);
		
		if(spielzeit < 10) {
			Hauptfenster.setzeZeitfeld("  " + spielzeit + ":" + "00");
		} else {
			Hauptfenster.setzeZeitfeld(spielzeit + ":" + "00");
		}
		
		frame.setVisible(true);
	}

	private Font getFont() {
		// Schriftgröße an Bildschirmgröße anpassen
		return new Font("Arial", 0, (int) (höheFenster / 3 - 40));
	}

	// Punktestand aktualisieren
	public void punktestand() {
		spielstand.setText(manschaft1 + ":" + manschaft2);
	}

	// zeit starten oder pausieren
	public void zeit() {
		if (spielPausiert) {
			zeitthread.setzePausiert();
			spielPausiert = false;
		} else {
			zeitthread.setzePausiert();
			spielPausiert = true;
		}
	}

	public static void setzeZeitfeld(String zeittext) {
		zeit.setText(zeittext);
	}

	// Implementieren der Methoden des Interfaces KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar() + "") {
		case "+":
			manschaft1++;
			punktestand();
			break;

		case "-":
			manschaft1--;
			punktestand();
			break;

		case "/":
			manschaft2--;
			punktestand();
			break;
		case "*":
			manschaft2++;
			punktestand();
			break;
		case "1":
			zeit();
			break;
			
		case "2":
			zeitthread.reset();
			manschaft1 = 0;
			manschaft2 = 0;
			punktestand();
			break;
		case "3":
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
