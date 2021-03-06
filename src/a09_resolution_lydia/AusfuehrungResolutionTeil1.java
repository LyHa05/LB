<<<<<<< HEAD
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.TreeSet;

public class AusfuehrungResolutionTeil1 {
	
	static HashSet<Klausel> knf;
	static TreeSet<Klausel> knfSort;
	static long zeit1;
	static long zeit2;

	public static void main(String[] args) {
		
//		/**(A,B),(!B,C),(!C,D) = (A,D)  */
//		
//    	HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', true);
//		Literal l2 = new Literal('B', true);
//		litset1.add(l1);
//		litset1.add(l2);
//
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('B', false);
//		Literal l5 = new Literal('C', true);
//		litset2.add(l4);
//		litset2.add(l5);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l6 = new Literal('C', false);
//		Literal l7 = new Literal('D', true);
//		litset3.add(l6);
//		litset3.add(l7);
//		
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//
//		knf = new HashSet<Klausel>();
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		
//		knfSort = new TreeSet<Klausel>(knf);
		
		HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', false);
		Literal l2 = new Literal('B', false);
		Literal l3 = new Literal('C', false);
		Literal l4 = new Literal('D', false);
		litset1.add(l1);
		litset1.add(l2);
		litset1.add(l3);
		litset1.add(l4);
		
		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l5 = new Literal('B', true);
		Literal l6 = new Literal('D', true);
		Literal l7 = new Literal('G', true);
		Literal l8 = new Literal('E', true);
		Literal l9 = new Literal('H', true);
		litset2.add(l5);
		litset2.add(l6);
		litset2.add(l7);
		litset2.add(l8);
		litset2.add(l9);
		
		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l10 = new Literal('C', false);
		//Literal l7 = new Literal('B', false);
		//Literal l8 = new Literal('C', true);
		litset3.add(l10);
		//litset3.add(l7);
		//litset3.add(l8);
		
		HashSet<Literal> litset4 = new HashSet<Literal>();
		Literal l11 = new Literal('B', true);
		//Literal l10 = new Literal('B', false);
		//Literal l8 = new Literal('C', true);
		litset4.add(l11);
		//litset4.add(l10);
		//litset3.add(l8);
		
		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		Klausel klausel3 = new Klausel(litset3);
		Klausel klausel4 = new Klausel(litset4);
		
		knf = new HashSet<Klausel>();

		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		knf.add(klausel4);
		
		knfSort = new TreeSet<Klausel>(knf);
		
		System.out.println("normale Resolution:");
		zeit1 = System.currentTimeMillis();
		EinfacheResolution.resolvierenNormal(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + knf);
		System.out.println(EinfacheResolution.ausgabe(EinfacheResolution.resolvierenNormal(knf)));
		System.out.println(EinfacheResolution.zaehler1);
		System.out.println(EinfacheResolution.zaehler2);
		
		System.out.println("\n" + "P-Resolution:");
		zeit1 = System.currentTimeMillis();
		PResolution.pResolvieren(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + knf);
		System.out.println(PResolution.ausgabe(PResolution.pResolvieren(knf)));
		System.out.println(PResolution.zaehler1);
		System.out.println(PResolution.zaehler2);
		
		System.out.println("\n" + "N-Resolution:");
		zeit1 = System.currentTimeMillis();
		NResolution.nResolvieren(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + knf);
		System.out.println(NResolution.ausgabe(NResolution.nResolvieren(knf)));
		System.out.println(NResolution.zaehler1);
		System.out.println(NResolution.zaehler2);
		
		System.out.println("\n" + "sortierte Resolution:");
		zeit1 = System.currentTimeMillis();
		SortResolution.resolvierenSortiert(knfSort);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + knfSort);
		System.out.println(SortResolution.ausgabe(SortResolution.resolvierenSortiert(knfSort)));
		System.out.println(SortResolution.zaehler1);
		System.out.println(SortResolution.zaehler2);
		
		System.out.println("\n" + "Einheitsresolution:");
		zeit1 = System.currentTimeMillis();
		EinheitsResolution.einheitsResolvieren(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + knf);
		System.out.println(EinheitsResolution.ausgabe(EinheitsResolution.einheitsResolvieren(knf)));
		System.out.println(EinheitsResolution.zaehler1);
		System.out.println(EinheitsResolution.zaehler2);
		
	}
	
}
=======
package a09_resolution_lydia;

import java.util.HashSet;
import java.util.TreeSet;

public class AusfuehrungResolutionTeil1 {
	
	static HashSet<Klausel> knf;
	static TreeSet<Klausel> knfSort;
	static long zeit1;
	static long zeit2;

	public static void main(String[] args) {

		/**{{!A,C},{A,B},{!B,C},{!C}} = {}*/
		
    	HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', false);
		Literal l2 = new Literal('C', true);
		litset1.add(l1);
		litset1.add(l2);

		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l4 = new Literal('A', true);
		Literal l5 = new Literal('B', true);
		litset2.add(l4);
		litset2.add(l5);

		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l6 = new Literal('B', false);
		Literal l7 = new Literal('C', true);
		litset3.add(l6);
		litset3.add(l7);

		HashSet<Literal> litset4 = new HashSet<Literal>();
		Literal l8 = new Literal('C', false);
		litset4.add(l8);
		
		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		Klausel klausel3 = new Klausel(litset3);
		Klausel klausel4 = new Klausel(litset4);

		knf = new HashSet<Klausel>();
		knf.add(klausel1);
		knf.add(klausel2);
		knf.add(klausel3);
		knf.add(klausel4);
		
		knfSort = new TreeSet<Klausel>(knf);
		
		/**{{A,B},{!B,C},{!C,D}} = {A,D}*/
		
//    	HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', true);
//		Literal l2 = new Literal('B', true);
//		litset1.add(l1);
//		litset1.add(l2);
//
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('B', false);
//		Literal l5 = new Literal('C', true);
//		litset2.add(l4);
//		litset2.add(l5);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l6 = new Literal('C', false);
//		Literal l7 = new Literal('D', true);
//		litset3.add(l6);
//		litset3.add(l7);
//		
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//
//		knf = new HashSet<Klausel>();
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		
//		knfSort = new TreeSet<Klausel>(knf);
		
		/**{{!A,B,E},{A,!D},{B,!C},{F},{!E}} */
		
//		HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', false);
//		Literal l2 = new Literal('B', true);
//		Literal l3 = new Literal('E',true);
//		litset1.add(l1);
//		litset1.add(l2);
//		litset1.add(l3);
//
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('A', true);
//		Literal l5 = new Literal('D', false);
//		litset2.add(l4);
//		litset2.add(l5);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l6 = new Literal('B', true);
//		Literal l7 = new Literal('C', false);
//		litset3.add(l6);
//		litset3.add(l7);
//		
//		HashSet<Literal> litset4 = new HashSet<Literal>();
//		Literal l10 = new Literal('F', true);
//		litset4.add(l10);
//		
//		HashSet<Literal> litset5 = new HashSet<Literal>();
//		Literal l11 = new Literal('E', false);
//		litset5.add(l11);
//		
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//		Klausel klausel4 = new Klausel(litset4);
//		Klausel klausel5 = new Klausel(litset5);
//
//		knf = new HashSet<Klausel>();
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		knf.add(klausel4);
//		knf.add(klausel5);
//		
//		knfSort = new TreeSet<Klausel>(knf);
//		
		
		System.out.println("normale Resolution:");
		zeit1 = System.currentTimeMillis();
		EinfacheResolution.resolvierenNormal(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + EinfacheResolution.ausgabe(knf));
		System.out.println("Ergebnis: "  + EinfacheResolution.ergebnis(EinfacheResolution.resolvierenNormal(knf)));
		System.out.println(EinfacheResolution.zaehler1);
		
		System.out.println("\n" + "P-Resolution:");
		zeit1 = System.currentTimeMillis();
		PResolution.pResolvieren(knfSort);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + PResolution.ausgabe(knfSort));
		System.out.println("Ergebnis: "  + PResolution.ergebnis(PResolution.pResolvieren(knfSort)));
		System.out.println(PResolution.zaehler1);
		
//		System.out.println("\n" + "N-Resolution:");
//		zeit1 = System.currentTimeMillis();
//		NResolution.nResolvieren(knfSort);
//		zeit2 = System.currentTimeMillis();
//		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
//		System.out.println("Eingabe: " + NResolution.ausgabe(knfSort));
//		System.out.println("Ergebnis: "  + NResolution.ergebnis(NResolution.nResolvieren(knfSort)));
//		System.out.println(NResolution.zaehler1);
		
		System.out.println("\n" + "sortierte Resolution:");
		zeit1 = System.currentTimeMillis();
		SortResolution.resolvierenSortiert(knfSort);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + SortResolution.ausgabe(knfSort));
		System.out.println("Ergebnis: "  + SortResolution.ergebnis(SortResolution.resolvierenSortiert(knfSort)));
		System.out.println(SortResolution.zaehler1);
	}
	
}
>>>>>>> branch 'master' of https://github.com/LyHa05/LB.git
