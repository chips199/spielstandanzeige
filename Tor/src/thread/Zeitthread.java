package thread;

import fenster.Hauptfenster;

public class Zeitthread extends Thread{
	private int zeitInMinuten, minuten, sekunden;
	private boolean pausiert = true;
	
	public Zeitthread(int zeitInMinuten) {
		this.zeitInMinuten = zeitInMinuten;
	}
	
	public void run() {
		sekunden = zeitInMinuten * 60;
		
		
		
		
		while(sekunden >= 0) {
			do {
				try {
					Thread.sleep(1000);
				}catch(Exception e) {
					
				}
			} while (pausiert);
			minuten = (int) (sekunden / 60);
			int sekundenanzeige = sekunden - minuten * 60;
			if (sekundenanzeige < 10) {
				if(minuten < 10) {
					Hauptfenster.setzeZeitfeld("  " + minuten + ":" + "0" + (sekunden - minuten * 60));
				} else {
					Hauptfenster.setzeZeitfeld(minuten + ":" + "0" + (sekunden - minuten * 60));
				}
			} else {
				if(minuten < 10) {
					Hauptfenster.setzeZeitfeld("  " + minuten + ":" + (sekunden - minuten * 60));
				} else {
					Hauptfenster.setzeZeitfeld(minuten + ":" + (sekunden - minuten * 60));
				}		
			}
			sekunden--;
		}
	}
	
	public void setzePausiert() {
		if (pausiert) {
			pausiert = false;
		}else {
			pausiert = true;
		}
	}
	
	public void reset() {
		sekunden = zeitInMinuten * 60;
		Hauptfenster.setzeZeitfeld(minuten + ":" + (sekunden - minuten * 60));
	}
}