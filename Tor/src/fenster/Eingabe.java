package fenster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Eingabe {
	
	private JFrame frame;
	private JLabel mannschaft1nameLabel, mannschaft2nameLabel, spielzeitLabel;
	private JTextField mannschaft1name, mannschaft2name, spielzeit;
	private JTextArea anleitung;
	private JButton weiter;
	private Toolkit toolkit;
	private Dimension dimension;
	
	private double breiteFenster, höheFenster;
	
	public Eingabe() {
		frame = new JFrame("Eingabe");
		// Bildschrimgröße auslesen
		toolkit = Toolkit.getDefaultToolkit();
		dimension = toolkit.getScreenSize();
		breiteFenster = dimension.getWidth();
		höheFenster = dimension.getHeight();
		
		frame.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		mannschaft1nameLabel = new JLabel("Mannschaft 1:");
		mannschaft1nameLabel.setBounds(20, 20, (int) ((breiteFenster - 60) / 2), 40);
		mannschaft1nameLabel.setHorizontalAlignment(JTextField.CENTER);
		mannschaft1nameLabel.setBorder(null);
		mannschaft1nameLabel.setForeground(Color.black);
		mannschaft1nameLabel.setFont(getFont());
		frame.add(mannschaft1nameLabel);
		
		mannschaft2nameLabel = new JLabel("Mannschaft 2:");
		mannschaft2nameLabel.setBounds(20, mannschaft1nameLabel.getHeight() + 40, (int) ((breiteFenster - 60) / 2), 40);
		mannschaft2nameLabel.setHorizontalAlignment(JTextField.CENTER);
		mannschaft2nameLabel.setBorder(null);
		mannschaft2nameLabel.setForeground(Color.black);
		mannschaft2nameLabel.setFont(getFont());
		frame.add(mannschaft2nameLabel);
		
		spielzeitLabel = new JLabel("Spielzeit:");
		spielzeitLabel.setBounds(20, mannschaft1nameLabel.getHeight() * 2 + 60, (int) ((breiteFenster - 60) / 2), 40);
		spielzeitLabel.setHorizontalAlignment(JTextField.CENTER);
		spielzeitLabel.setBorder(null);
		spielzeitLabel.setForeground(Color.black);
		spielzeitLabel.setFont(getFont());
		frame.add(spielzeitLabel);
		
		mannschaft1name = new JTextField();
		mannschaft1name.setBounds(mannschaft1nameLabel.getWidth() + 40, 20, (int) ((breiteFenster - 60) / 2), 40);
		mannschaft1name.setHorizontalAlignment(JTextField.CENTER);
		mannschaft1name.setEditable(true);
		mannschaft1name.setForeground(Color.black);
		mannschaft1name.setFont(getFont());
		frame.add(mannschaft1name);
		
		mannschaft2name = new JTextField();
		mannschaft2name.setBounds(mannschaft1nameLabel.getWidth() + 40, mannschaft1nameLabel.getHeight() + 40, (int) ((breiteFenster - 60) / 2), 40);
		mannschaft2name.setHorizontalAlignment(JTextField.CENTER);
		mannschaft2name.setEditable(true);
		mannschaft2name.setForeground(Color.black);
		mannschaft2name.setFont(getFont());
		frame.add(mannschaft2name);
		
		spielzeit = new JTextField();
		spielzeit.setBounds(mannschaft1nameLabel.getWidth() + 40, mannschaft1nameLabel.getHeight() * 2 + 60, (int) ((breiteFenster - 60) / 2), 40);
		spielzeit.setHorizontalAlignment(JTextField.CENTER);
		spielzeit.setEditable(true);
		spielzeit.setFont(getFont());
		spielzeit.setForeground(Color.black);
		frame.add(spielzeit);
		
		anleitung = new JTextArea("\"+\" Mannschaft 1 ein Tor mehr\r\n" + 
				"\"-\" Mannschaft 1 ein Tor abziehen\r\n" + 
				"\"*\" Mannschaft 2 ein Tor mehr\r\n" + 
				"\"/\" Mannschaft 2 ein Torabziehen\r\n" + 
				"\"1\" Timer starten, pausieren\r\n" + 
				"\"2\" reseten (alles auf 0)\r\n" + 
				"\"3\" Programm beenden\r\n" + 
				"");
		anleitung.setEditable(false);
		anleitung.setForeground(Color.BLACK);
		anleitung.setFont(getFont());
		anleitung.setBounds(mannschaft1nameLabel.getWidth(), mannschaft1nameLabel.getHeight() * 3 + 100, (int) ((breiteFenster - 60) / 2), mannschaft1nameLabel.getHeight() * 5);
		frame.add(anleitung);
		
		weiter = new JButton("Weiter");
		weiter.setFont(getFont());
		weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Hauptfenster(Integer.valueOf(spielzeit.getText()), mannschaft1name.getText(), mannschaft2name.getText());
				frame.dispose();
			}
		});
		weiter.setBounds(mannschaft1nameLabel.getWidth(), mannschaft1nameLabel.getHeight() * 8 + 100, (int) ((breiteFenster - 60) / 2), 40);
		frame.add(weiter);
		
		frame.setVisible(true);
	}
	
	private Font getFont() {
		// Schriftgröße an Bildschirmgröße anpassen
		return new Font("Arial", 0, 20);
	}
}
