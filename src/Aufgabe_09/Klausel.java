package Aufgabe_09;

import java.util.Collections;
import java.util.Set;

public class Klausel {
	private final Set<Literal> literale;

	public Klausel(Set<Literal> literale) {
		this.literale = literale;
	}
	public Set<Literal> getLiterale() {
		return Collections.unmodifiableSet(literale);
	}
	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Object[] lit = literale.toArray();
		builder.append('{');
		
		if (lit.length != 0) {
		for (int i = 0; i < lit.length-1; i++) {
			builder.append(lit[i].toString()).append(", ");
		}
		builder.append(lit[lit.length-1]);
		} else {
			builder.append(' ');
		}
		builder.append('}');
		return builder.toString();
	}
}
