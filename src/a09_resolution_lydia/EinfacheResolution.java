<<<<<<< HEAD
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.Iterator;

public class EinfacheResolution {

	public static HashSet<Klausel> knf;
	public static boolean resolviert = false;
	public static int zaehler1 = 0;
	public static int zaehler2 = 0;
	
	/**Methode prueft, ob Hornklausel vorliegt.*/
	public static boolean istHornKlausel(HashSet<Literal> zuPruefendeKlausel) {
		int anzahlPositiveLiterale = 0;
		for(Literal l : zuPruefendeKlausel) {
			if(l.getWahrheitswert()) {
				++anzahlPositiveLiterale;
			}
		}
		if(anzahlPositiveLiterale <= 1) {
			return true;
		} else {
			return false;
		}
			
	}
	
	/** Methode prueft, ob Hornformel vorliegt.*/
	public static boolean istHornFormel(HashSet<Klausel> zupruefendeKlauseln) {
		for(Klausel k : zupruefendeKlauseln) {
			if(!istHornKlausel(k.getLiterale())) {
				return false;
			}
		}
		return true;
	}
	
	/**Methode prüft, ob Klausel für Einheitsklausel höchstens ein Literal enthält.*/
	public static boolean istEinheitsklausel(HashSet<Literal> zuPruefendeKlausel)  {
		if(zuPruefendeKlausel.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	

	public static HashSet<Klausel> resolvierenNormal(HashSet<Klausel> uebergebenerKNF) {

		HashSet<Klausel> verfuegbareKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		HashSet<Klausel> zuResolvierendeKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		int anzahlVerfKlau;

		do {
			
			resolviert = false;
			anzahlVerfKlau = verfuegbareKlauseln.size();
								
			/** Suchen von komplementaeren Klauseln */
			
			Iterator<Klausel> verfuegIter = verfuegbareKlauseln.iterator();
			while (verfuegIter.hasNext() && (resolviert == false)) {
				
//				System.out.println("Schleife 1");
//				System.out.println("verfuegbareKlauseln: " + verfuegbareKlauseln);
				Klausel k1 = verfuegIter.next();
//				System.out.println("k1: " + k1);
				
				Iterator<Klausel> zuResIter = zuResolvierendeKlauseln.iterator();
				while (zuResIter.hasNext() && (resolviert == false)) {
//					System.out.println("Schleife 2");
//					System.out.println("zuResolvierendeKlauseln: " + zuResolvierendeKlauseln);
					
					Klausel k2 = zuResIter.next();
					
//					System.out.println("k2: " + k2);
					
					Iterator<Literal> k1LitIter = k1.getLiterale().iterator();
					while (k1LitIter.hasNext() && (resolviert == false)) {
						Literal l1 = k1LitIter.next();
					
//					for (Literal l1 : k1.getLiterale()) {
						Iterator<Literal> k2LitIter = k2.getLiterale().iterator();
						while (k2LitIter.hasNext() && (resolviert == false)) {
//						for (Literal l2 : k2.getLiterale()) {
							Literal l2 = k2LitIter.next();
							++zaehler1;

							if (istResolvierbar(k1, k2, l1, l2)) {

//								System.out.println("---ist resolvierbar--");
								
								resolviert = true;
								
//								System.out.println("k1: " + k1);
//								System.out.println("k2: " + k2);

								Klausel neueKlausel = new Klausel(resolvierteLiteraleZusammen(zuResolvierendeKlauseln, k1, k2, l1, l2));
								
//								System.out.println("neueKlausel:" + neueKlausel);
								
								if (!neueKlausel.getLiterale().isEmpty()) {verfuegbareKlauseln.add(neueKlausel);};
																
								zuResolvierendeKlauseln.remove(k1);
								zuResolvierendeKlauseln.remove(k2);
								
								if (!neueKlausel.getLiterale().isEmpty()) {zuResolvierendeKlauseln.add(neueKlausel);};
								
//								System.out.println("zuResolvierendeKlauseln: " +  zuResolvierendeKlauseln);
//								System.out.println("verfuegbareKlauseln: " + verfuegbareKlauseln);
								
								++zaehler2;
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
				builder.append(knf[i].toString()).append(", ");
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
				&& (literalA.getWahrheitswert() != literalB.getWahrheitswert()) && !(klauselA.equals(klauselB)));
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

//		TreeSet<Klausel> knfSort = new TreeSet<Klausel>(knf);
		

		System.out.println(knf);
//		System.out.println(knfSort);

		System.out.println(ausgabe(resolvierenNormal(knf)));
		System.out.println(knf);
		System.out.println(zaehler1);
		System.out.println(zaehler2);

	}

}
=======
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.Iterator;

public class EinfacheResolution {

	public static HashSet<Klausel> knf;
	public static boolean resolviert = false;
	public static boolean bereitsEnthalten = false;
	public static int zaehler1 = 0;
	public static int zaehler2 = 0;

	/** Methode zum resolvieren */
	public static HashSet<Klausel> resolvierenNormal(HashSet<Klausel> uebergebenerKNF) {

		HashSet<Klausel> verfuegbareKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		HashSet<Klausel> zuResolvierendeKlauseln = new HashSet<Klausel>(tiefeKopieErstellen(uebergebenerKNF));
		int anzahlVerfKlau;

		do {

			resolviert = false;
			anzahlVerfKlau = verfuegbareKlauseln.size();

			/** Suchen von komplementaeren Klauseln */

			Iterator<Klausel> verfuegIter = verfuegbareKlauseln.iterator();
			while (verfuegIter.hasNext() && (resolviert == false)) {

//				 System.out.println("Schleife 1");
//				 System.out.println("verfuegbareKlauseln: " + verfuegbareKlauseln);
				 Klausel k1 = verfuegIter.next();
//				 System.out.println("k1: " + k1);

				Iterator<Klausel> zuResIter = zuResolvierendeKlauseln.iterator();
				while (zuResIter.hasNext() && (resolviert == false)) {
//					 System.out.println("Schleife 2");
//					 System.out.println("zuResolvierendeKlauseln: " +  zuResolvierendeKlauseln);

					Klausel k2 = zuResIter.next();

//					System.out.println("k2: " + k2);

					Iterator<Literal> k1LitIter = k1.getLiterale().iterator();
					while (k1LitIter.hasNext() && (resolviert == false)) {
						Literal l1 = k1LitIter.next();

						Iterator<Literal> k2LitIter = k2.getLiterale().iterator();
						while (k2LitIter.hasNext() && (resolviert == false)) {
							Literal l2 = k2LitIter.next();
							++zaehler1;

							if (istResolvierbar(k1, k2, l1, l2)) {

//								 System.out.println("---ist resolvierbar--");

								resolviert = true;

//								 System.out.println("k1: " + k1);
//								 System.out.println("k2: " + k2);

								Klausel neueKlausel = new Klausel(
										resolvierteLiteraleZusammen(zuResolvierendeKlauseln, k1, k2, l1, l2));

//								 System.out.println("neueKlausel:" + neueKlausel);

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
								
								if (verfuegbareKlauseln.contains(neueKlausel)) {
									bereitsEnthalten = true;
								}
								
//								 System.out.println("zuResolvierendeKlauseln: " + zuResolvierendeKlauseln);
//								 System.out.println("verfuegbareKlauseln: " + verfuegbareKlauseln);

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
	private static Klausel resolvierteLiteraleZusammen(HashSet<Klausel> nochZuResolvieren, Klausel k1, Klausel k2,
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

	/** Methode fuer formatierte Ergebnisausgabe */
	public static String ausgabe(HashSet<Klausel> ergebnisKlausel) {
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
	public static String ergebnis(HashSet<Klausel> ergebnisKlausel) {
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

//		HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', true);
//		Literal l2 = new Literal('B', true);
//		litset1.add(l1);
//		litset1.add(l2);
//
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('A', false);
//		Literal l5 = new Literal('B', true);
//		litset2.add(l4);
//		litset2.add(l5);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l6 = new Literal('A', true);
//		Literal l7 = new Literal('B', false);
//		litset3.add(l6);
//		litset3.add(l7);
//
//		HashSet<Literal> litset4 = new HashSet<Literal>();
//		Literal l9 = new Literal('A', false);
//		Literal l10 = new Literal('B', false);
//		litset4.add(l9);
//		litset4.add(l10);
//
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//		Klausel klausel4 = new Klausel(litset4);
//
//		knf = new HashSet<Klausel>();
//
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		knf.add(klausel4);

		/**Box*/
//		HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', false);
//		Literal l2 = new Literal('C', true);
//		litset1.add(l1);
//		litset1.add(l2);
//
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('A', true);
//		Literal l5 = new Literal('B', true);
//		litset2.add(l4);
//		litset2.add(l5);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l6 = new Literal('B', false);
//		Literal l7 = new Literal('C', true);
//		litset3.add(l6);
//		litset3.add(l7);
//
//		HashSet<Literal> litset4 = new HashSet<Literal>();
//		Literal l8 = new Literal('C', false);
//		litset4.add(l8);
//		
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//		Klausel klausel4 = new Klausel(litset4);
//
//		knf = new HashSet<Klausel>();
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		knf.add(klausel4);
		
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

		knf = new HashSet<Klausel>();
		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		
		System.out.println(ausgabe(knf));
		
		System.out.println(ausgabe(resolvierenNormal(knf)));
		System.out.println(zaehler1);
		System.out.println(zaehler2);

	}

}
>>>>>>> branch 'master' of https://github.com/LyHa05/LB.git
