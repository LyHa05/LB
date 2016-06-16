package a11_zaehlerprogramm_philipp;

import java.io.IOException;
import java.util.List;

public class Zaehlerprogramm {
	private List<Zaehler> zaehlerliste;
	private List<String> programm;
	private String debugger = null;
	int aktuelleProgrammzeile = 0;

	public Zaehlerprogramm(String txt_File, String debugger) throws IOException {
		Parser temp = new Parser(txt_File);
		temp.compile();
		this.zaehlerliste = temp.getZaehler();
		this.programm = temp.getCode();
		this.debugger = debugger;
		
		System.out.println("Eingabe:");
		zaehlerliste.forEach(c -> System.out.println(c.toString()));
	}

	private void ausfuehren(Integer aktuelleZeile) {
		String ausfuehren = programm.get(aktuelleZeile);
		if (ausfuehren.contains("++")) {
			zaehlerliste.forEach(c -> {if(c.getName().equals(ausfuehren.substring(0, 2))){c.inkrement();}});
			aktuelleProgrammzeile ++;
		} else if (ausfuehren.contains("--")) {
			zaehlerliste.forEach(c -> {if(c.getName().equals(ausfuehren.substring(0, 2))){c.dekrement();}});
		    aktuelleProgrammzeile ++;
		} else if (ausfuehren.contains(":")){
			if(ausfuehren.contains("==")){
			Integer kennziffer = Integer.valueOf(ausfuehren.substring(4, 5));
			Integer fall1 = Integer.valueOf(ausfuehren.substring(6, 7));
			Integer fall2 = Integer.valueOf(ausfuehren.substring(8, 9));
			Integer zaehlerwert = null;
			for (Zaehler zaehler : zaehlerliste) {
				if (zaehler.getName().equals(ausfuehren.substring(0, 2))) {
					zaehlerwert = zaehler.getZaehlerwert(); 
				}
			} 
			doSprung((zaehlerwert == kennziffer), fall1, fall2);
		}
		}
	}

	public void programmAusfuehren() throws IOException {
		while (aktuelleProgrammzeile < programm.size()) {
			withDebugger(debugger);
			ausfuehren(aktuelleProgrammzeile);
		}
		System.out.println("Ergebnis:");
		zaehlerliste.forEach(c -> System.out.println(c.toString()));
	}

	private void doSprung(boolean bedingung, Integer fall1, Integer fall2) {
		if(bedingung == true){
			this.aktuelleProgrammzeile = fall1; 
		}else{
			this.aktuelleProgrammzeile = fall2;
		}
	}
	
	private void withDebugger(String debugger) throws IOException{
		if(debugger == "t"){
			Zaehlerprogramm_Debugger.debugRechnung(this);	
		}else if(debugger == "m"){
			Zaehlerprogramm_Debugger.debugManually(this);
		}
	}

	public String getAktuelleProgrammzeile() {
		return programm.get(aktuelleProgrammzeile);
	}
}
