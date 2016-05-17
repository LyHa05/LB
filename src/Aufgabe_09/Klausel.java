package Aufgabe_09;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Klausel {

	private Set<Literal> klausel;
	
	public Klausel(HashSet<Literal> klausel) {
		this.klausel = new HashSet<Literal>();
	}

	public Set<Literal> getKlausel() {
		return Collections.unmodifiableSet(klausel);
	}

}
