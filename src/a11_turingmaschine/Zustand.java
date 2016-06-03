package a11_turingmaschine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zustand {
	private final String name;
	private final boolean endzustand;
	private final boolean startzustand;
	private Map<String, List<String>> zustandsuebergaenge = new HashMap<String, List<String>>();
	
	public Zustand(String name, boolean startzustand, boolean endzustand) {
		this.name = name;
		this.startzustand = startzustand;
		this.endzustand = endzustand;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isEndzustand() {
		return endzustand;
	}
	
	public boolean isStartzustand() {
		return startzustand;
	}
	
	public Map<String, List<String>> getZustandsuebergaenge() {
		return zustandsuebergaenge;
	}
	public void addZustandsuebergang(String gelesen, String geschrieben, String bewegung, Zustand folgezustand) {
		List<String> ueberfuehrung = new ArrayList<String>();
		ueberfuehrung.add(geschrieben);
		ueberfuehrung.add(bewegung);
		ueberfuehrung.add(folgezustand.getName());
		zustandsuebergaenge.put(gelesen, ueberfuehrung);
	}
	
	public List<String> getUebergang(String eingabe) {
		return zustandsuebergaenge.get(eingabe);
	}

}

