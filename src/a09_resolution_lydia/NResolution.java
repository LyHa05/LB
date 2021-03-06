<<<<<<< HEAD
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.Iterator;

public class NResolution {

	public static HashSet<Klausel> knf;
	public static boolean resolviert = false;
	public static int zaehler1 = 0;
	public static int zaehler2 = 0;
	
	
	/**Methode prüft, ob Klausel negativ ist*/
	public static boolean istNegativeKlausel(HashSet<Literal> zuPruefendeKlausel) {
		for(Literal l: zuPruefendeKlausel) {
			if (l.getWahrheitswert()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean enthaeltNegativeKlausel(HashSet<Klausel> zuPruefendeKlauseln) {
		for(Klausel k: zuPruefendeKlauseln) {
			if (istNegativeKlausel(k.getLiterale())) {
				return true;
			}
		}
		return false;
	}
	
	public static HashSet<Klausel> nResolvieren(HashSet<Klausel> uebergebenerKNF) {

		if(!enthaeltNegativeKlausel(uebergebenerKNF)) {
			throw new IllegalArgumentException("Die uebergebene KNF enthaelt keine negativen Klauseln.");
		}
				
		HashSet<Klausel> verfuegbareKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		HashSet<Klausel> zuResolvierendeKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));

		int anzahlVerfKlau;
		
		do {
			
			resolviert = false;
			anzahlVerfKlau = verfuegbareKlauseln.size();
								
			/** Suchen von komplementaeren Klauseln */
			
			Iterator<Klausel> verfuegIter = verfuegbareKlauseln.iterator();
			while (verfuegIter.hasNext() && (resolviert == false)) {
				Klausel k1 = verfuegIter.next(); 	    
				
				Iterator<Klausel> zuResIter = zuResolvierendeKlauseln.iterator();
				while (zuResIter.hasNext() && (resolviert == false)) {
					Klausel k2 = zuResIter.next();
					
					for (Literal l1 : k1.getLiterale()) {
						for (Literal l2 : k2.getLiterale()) {
							
							++zaehler1;

							if (istResolvierbar(k1, k2, l1, l2)) {

								++zaehler2;
								
								resolviert = true;
								
//								System.out.println("k1: " + k1);
//								System.out.println("k2: " + k2);

								Klausel neueKlausel = new Klausel(resolvierteLiteraleZusammen(zuResolvierendeKlauseln, k1, k2, l1, l2));
								
//								System.out.println("neueKlausel:" + neueKlausel);
								
								if (!neueKlausel.getLiterale().isEmpty()) {verfuegbareKlauseln.add(neueKlausel);};
								
								if (!neueKlausel.getLiterale().isEmpty()) {zuResolvierendeKlauseln.add(neueKlausel);};
								
								zuResolvierendeKlauseln.remove(k1);
								zuResolvierendeKlauseln.remove(k2);
								
//								System.out.println("zuResolvierendeKlauseln: " +  zuResolvierendeKlauseln);
//								System.out.println("verfuegbareKlauseln: " + verfuegbareKlauseln);
								
								
							}
						}
					}
				}

			}
		} while (resolviert == true && !(zuResolvierendeKlauseln.isEmpty()) && (verfuegbareKlauseln.size() != anzahlVerfKlau));

		return zuResolvierendeKlauseln;

	}

	private static Klausel resolvierteLiteraleZusammen(HashSet<Klausel> nochZuResolvieren, Klausel k1, Klausel k2, Literal l1, Literal l2) {

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

	private static HashSet<Klausel> tiefeKopieErstellen(HashSet<Klausel> klauselliste) {
		HashSet<Klausel> kopierteKlauselListe = new HashSet<Klausel>();

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

	public static String ausgabe(HashSet<Klausel> ergebnisKlausel) {
		StringBuilder builder = new StringBuilder();
		Object[] knf = ergebnisKlausel.toArray();
		builder.append('{');
		
		if (knf.length != 0) {
			for (int i = 0; i < knf.length-1; i++) {
				builder.append(knf[i].toString());
			}
			builder.append(knf[knf.length-1]);
		} else {
			builder.append(' ');
		}
		builder.append('}');
		
		return builder.toString();
	}

	/** prueft, ob 2 Literale resolvierbar sind */
	private static boolean istResolvierbar(Klausel klauselA, Klausel klauselB, Literal literalA, Literal literalB) {
		return ((literalA.getBezeichner() == literalB.getBezeichner())
				&& (literalA.getWahrheitswert() != literalB.getWahrheitswert())
				&& !(klauselA.equals(klauselB))
				&& (istNegativeKlausel(klauselA.getLiterale()) || istNegativeKlausel(klauselB.getLiterale())));
	}

	public static void main(String[] args) {

		HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', true);
		Literal l2 = new Literal('B', true);
		litset1.add(l1);
		litset1.add(l2);

		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l4 = new Literal('A', false);
		Literal l5 = new Literal('B', true);
		litset2.add(l4);
		litset2.add(l5);

		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l6 = new Literal('A', true);
		Literal l7 = new Literal('B', false);
		litset3.add(l6);
		litset3.add(l7);
		
		HashSet<Literal> litset4 = new HashSet<Literal>();
		Literal l9 = new Literal('A', false);
		Literal l10 = new Literal('B', false);
		litset4.add(l9);
		litset4.add(l10);

		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		Klausel klausel3 = new Klausel(litset3);
		Klausel klausel4 = new Klausel(litset4);

		knf = new HashSet<Klausel>();

		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		knf.add(klausel4);



		System.out.println(knf);

		System.out.println(ausgabe(nResolvieren(knf)));
		System.out.println(knf);
		System.out.println(zaehler1);
		System.out.println(zaehler2);

	}

}
=======
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class NResolution {

	public static TreeSet<Klausel> knf;
	public static boolean resolviert = false;
	public static boolean bereitsEnthalten = false;
	public static int zaehler1 = 0;
	public static int zaehler2 = 0;

	/** Methode prueft, ob Klausel negativ ist */
	public static boolean istNegativeKlausel(HashSet<Literal> zuPruefendeKlausel) {
		for (Literal l : zuPruefendeKlausel) {
			if (l.getWahrheitswert()) {
				return false;
			}
		}
		return true;
	}

	/** Methode prueft, ob HashSet aus Klauseln negative Klausel enthaelt */
	public static boolean enthaeltNegativeKlausel(TreeSet<Klausel> zuPruefendeKlauseln) {
		for (Klausel k : zuPruefendeKlauseln) {
			if (istNegativeKlausel(k.getLiterale())) {
				return true;
			}
		}
		return false;
	}

	/** Methode zum resolvieren */
	public static TreeSet<Klausel> nResolvieren(TreeSet<Klausel> uebergebenerKNF) {

		if (!enthaeltNegativeKlausel(uebergebenerKNF)) {
			throw new IllegalArgumentException("Die uebergebene KNF enthaelt keine negativen Klauseln.");
		}

		TreeSet<Klausel> verfuegbareKlauseln = new TreeSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		TreeSet<Klausel> zuResolvierendeKlauseln = new TreeSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));

		int anzahlVerfKlau;

		do {

			resolviert = false;
			anzahlVerfKlau = verfuegbareKlauseln.size();

			/** Suchen von komplementaeren Klauseln */

			Iterator<Klausel> verfuegIter = verfuegbareKlauseln.iterator();
			while (verfuegIter.hasNext() && (resolviert == false)) {
				Klausel k1 = verfuegIter.next();

				Iterator<Klausel> zuResIter = zuResolvierendeKlauseln.iterator();
				while (zuResIter.hasNext() && (resolviert == false)) {
					Klausel k2 = zuResIter.next();

					for (Literal l1 : k1.getLiterale()) {
						for (Literal l2 : k2.getLiterale()) {

							++zaehler1;

							if (istResolvierbar(k1, k2, l1, l2)) {

								++zaehler2;

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

								if (!neueKlausel.getLiterale().isEmpty()) {
									zuResolvierendeKlauseln.add(neueKlausel);
								}
								;

								zuResolvierendeKlauseln.remove(k1);
								zuResolvierendeKlauseln.remove(k2);

								// System.out.println("zuResolvierendeKlauseln:
								// " + zuResolvierendeKlauseln);
								// System.out.println("verfuegbareKlauseln: " +
								// verfuegbareKlauseln);
								
								if (verfuegbareKlauseln.contains(neueKlausel)) {
									bereitsEnthalten = true;
								}

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
				&& (literalA.getWahrheitswert() != literalB.getWahrheitswert()) && !(klauselA.equals(klauselB))
				&& (istNegativeKlausel(klauselA.getLiterale()) || istNegativeKlausel(klauselB.getLiterale())));
	}

	public static void main(String[] args) {

		/**{{!A,B,E},{A,!D},{B,!C},{F},{!E}} */
		
		HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', false);
		Literal l2 = new Literal('B', true);
		Literal l3 = new Literal('E',true);
		litset1.add(l1);
		litset1.add(l2);
		litset1.add(l3);

		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l4 = new Literal('A', true);
		Literal l5 = new Literal('D', false);
		litset2.add(l4);
		litset2.add(l5);

		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l6 = new Literal('B', true);
		Literal l7 = new Literal('C', false);
		litset3.add(l6);
		litset3.add(l7);
		
		HashSet<Literal> litset4 = new HashSet<Literal>();
		Literal l10 = new Literal('F', true);
		litset4.add(l10);
		
		HashSet<Literal> litset5 = new HashSet<Literal>();
		Literal l11 = new Literal('E', false);
		litset5.add(l11);
		
		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		Klausel klausel3 = new Klausel(litset3);
		Klausel klausel4 = new Klausel(litset4);
		Klausel klausel5 = new Klausel(litset5);

		knf = new TreeSet<Klausel>();
		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		knf.add(klausel4);
		knf.add(klausel5);
		
		knf = new TreeSet<Klausel>(knf);
		System.out.println(ausgabe(knf));

		System.out.println(ergebnis(nResolvieren(knf)));
		System.out.println(zaehler1);

	}

}
>>>>>>> branch 'master' of https://github.com/LyHa05/LB.git
