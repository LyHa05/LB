package a09_resolution_lydia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class SortResolution {

	public static TreeSet<Klausel> knf;
	public static boolean resolviert = false;
	public static boolean bereitsEnthalten = false;
	public static int zaehler1 = 0;
	public static int zaehler2 = 0;

	/** Methode zum resolvieren */
	public static TreeSet<Klausel> resolvierenSortiert(TreeSet<Klausel> uebergebenerKNF) {

		TreeSet<Klausel> verfuegbareKlauseln = new TreeSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		TreeSet<Klausel> zuResolvierendeKlauseln = new TreeSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		int anzahlVerfKlau;

		do {

			resolviert = false;
			anzahlVerfKlau = verfuegbareKlauseln.size();

			/** Suchen von komplementaeren Klauseln */

			Iterator<Klausel> verfuegIter = verfuegbareKlauseln.iterator();
			while (verfuegIter.hasNext() && (resolviert == false)) {

				// System.out.println("Schleife 1");
				// System.out.println("verfuegbareKlauseln: " +
				// verfuegbareKlauseln);
				Klausel k1 = verfuegIter.next();
				// System.out.println("k1: " + k1);

				Iterator<Klausel> zuResIter = zuResolvierendeKlauseln.iterator();
				while (zuResIter.hasNext() && (resolviert == false)) {
					// System.out.println("Schleife 2");
					// System.out.println("zuResolvierendeKlauseln: " +
					// zuResolvierendeKlauseln);

					Klausel k2 = zuResIter.next();

					// System.out.println("k2: " + k2);

					Iterator<Literal> k1LitIter = k1.getLiterale().iterator();
					while (k1LitIter.hasNext() && (resolviert == false)) {
						Literal l1 = k1LitIter.next();
						Iterator<Literal> k2LitIter = k2.getLiterale().iterator();
						while (k2LitIter.hasNext() && (resolviert == false)) {
							Literal l2 = k2LitIter.next();
							++zaehler1;

							if (istResolvierbar(k1, k2, l1, l2)) {

								// System.out.println("---ist resolvierbar--");

								resolviert = true;

								// System.out.println("k1: " + k1);
								// System.out.println("k2: " + k2);

								Klausel neueKlausel = new Klausel(
										resolvierteLiteraleZusammen(zuResolvierendeKlauseln, k1, k2, l1, l2));

								// System.out.println("neueKlausel:" +
								// neueKlausel);

								if (!neueKlausel.getLiterale().isEmpty()) {
									verfuegbareKlauseln.add(neueKlausel);
								}
								;

								zuResolvierendeKlauseln.remove(k1);
								zuResolvierendeKlauseln.remove(k2);

								if (!neueKlausel.getLiterale().isEmpty()) {
									zuResolvierendeKlauseln.add(neueKlausel);
								}
								;

								// System.out.println("zuResolvierendeKlauseln:
								// " + zuResolvierendeKlauseln);
								// System.out.println("verfuegbareKlauseln: " +
								// verfuegbareKlauseln);

								if (verfuegbareKlauseln.contains(neueKlausel)) {
									bereitsEnthalten = true;
								}
								
								++zaehler2;
							}
						}
					}
				}

			}
		} while (resolviert == true && !(zuResolvierendeKlauseln.isEmpty())
				&& ((verfuegbareKlauseln.size() != anzahlVerfKlau) || bereitsEnthalten == true));

		return zuResolvierendeKlauseln;

	}

	/** Methode erstellt neue Klausel aus resolvierten Klauseln */
	private static Klausel resolvierteLiteraleZusammen(TreeSet<Klausel> nochZuResolvieren, Klausel k1, Klausel k2,
			Literal l1, Literal l2) {

		Klausel neuK = new Klausel();

		for (Literal tempL : k1.getLiterale()) {
			if (!(tempL.equals(l1))) {
				neuK.setLiteral(tempL);
			}
		}

		for (Literal tempL : k2.getLiterale()) {
			if (!(tempL.equals(l2))) {
				neuK.setLiteral(tempL);
			}
		}

		return neuK;
	}

	/** Methode, um tiefe Kopie von KNF zu erstellen */
	private static TreeSet<Klausel> tiefeKopieErstellen(TreeSet<Klausel> klauselliste) {
		TreeSet<Klausel> kopierteKlauselListe = new TreeSet<Klausel>();

		for (Klausel tempKlausel : klauselliste) {
			Klausel neueK = new Klausel(tempKlausel.getLiterale());

			for (Literal tempLiteral : tempKlausel.getLiterale()) {
				Literal lit = new Literal(tempLiteral);
				neueK.setLiteral(lit);
			}

			kopierteKlauselListe.add(neueK);
		}

		return kopierteKlauselListe;
	}

	/** Methode fuer formatierte Ergebnisausgabe */
	public static String ausgabe(TreeSet<Klausel> ergebnisKlausel) {
		StringBuilder builder = new StringBuilder();
		Object[] knf = ergebnisKlausel.toArray();
		builder.append('{');

		if (knf.length != 0) {
			for (int i = 0; i < knf.length - 1; i++) {
				builder.append(knf[i].toString()).append(", ");
			}
			builder.append(knf[knf.length - 1]);
		} else {
			builder.append(' ');
		}
		builder.append('}');

		return builder.toString();
	}

	/** Methode fuer formatierte Ergebnisausgabe */
	public static String ergebnis(TreeSet<Klausel> ergebnisKlausel) {
		String ergebnis = "";
		
		if (ergebnisKlausel.isEmpty()) {
			ergebnis = "nicht erfuellbar"; 
		} else {
			ergebnis = "erfuellbar";
		}

		return ergebnis;
	}
	
	/** prueft, ob 2 Literale resolvierbar sind */
	private static boolean istResolvierbar(Klausel klauselA, Klausel klauselB, Literal literalA, Literal literalB) {
		return ((literalA.getBezeichner() == literalB.getBezeichner())
				&& (literalA.getWahrheitswert() != literalB.getWahrheitswert()) && !(klauselA.equals(klauselB)));
	}

	public static void main(String[] args) {

		/**{{A,B},{!B,C},{!C,D}} = {A,D}*/
		
    	HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', true);
		Literal l2 = new Literal('B', true);
		litset1.add(l1);
		litset1.add(l2);

		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l4 = new Literal('B', false);
		Literal l5 = new Literal('C', true);
		litset2.add(l4);
		litset2.add(l5);

		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l6 = new Literal('C', false);
		Literal l7 = new Literal('D', true);
		litset3.add(l6);
		litset3.add(l7);
		
		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		Klausel klausel3 = new Klausel(litset3);

		knf = new TreeSet<Klausel>();
		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		
		System.out.println(ausgabe(knf));

		System.out.println(ausgabe(resolvierenSortiert(knf)));
		System.out.println(zaehler1);


	}

}
