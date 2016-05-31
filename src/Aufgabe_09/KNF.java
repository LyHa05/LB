package Aufgabe_09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KNF  {
	public Set<Klausel> klauseln;

	public KNF(Set<Klausel> klauseln) {
		this.klauseln = klauseln;
	}

	public Set<Klausel> getKlauseln() {
		return Collections.unmodifiableSet(klauseln);
	}

	public static Klausel returnResolvent(Klausel klauselA, Klausel klauselB) {
		boolean resolvent = false;
		List<Literal> listLiterale = new ArrayList<Literal>();
		
		for (Literal literalA : klauselA.getLiterale()) {			
			for (Literal literalB : klauselB.getLiterale()) {		
				if (isResolvent(literalA, literalB)) {		
					listLiterale.addAll(klauselA.getLiterale());
					listLiterale.remove(literalA);
					listLiterale.addAll(klauselB.getLiterale());
					listLiterale.remove(literalB);
					resolvent = true;
					break;
				}
			}
		}
		if (resolvent == true) {
			Set<Literal> set = new HashSet<Literal>();
			set.addAll(listLiterale);
			return new Klausel(set);
		} else {
			return null;
		}
	}

	private static boolean isResolvent(Literal literalA, Literal literalB) {
		return ((literalA.getBezeichner() == literalB.getBezeichner()) && (literalA
				.getWahrheitswert() != literalB.getWahrheitswert()));
	}

}
