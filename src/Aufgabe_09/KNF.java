package Aufgabe_09;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KNF {

	public Set<Klausel> klauseln;
	
	private KNF(HashSet<Klausel> klauseln) {
		this.klauseln = new HashSet<Klausel>();
	}

	public Set<Klausel> getKlauseln() {
		return Collections.unmodifiableSet(klauseln);
	}
	
	
}
