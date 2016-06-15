package a09_resolution_lydia;

import java.util.HashSet;
import java.util.TreeSet;

public class AusfuehrungResolutionTeil2 {
	
	static HashSet<Klausel> knf;
	static TreeSet<Klausel> knfSort;
	static long zeit1;
	static long zeit2;

	public static void main(String[] args) {
		
		/**{{A,!C},{!B,C},{!A,!B,!C},{B}} = {} */
		
		HashSet<Literal> litset1 = new HashSet<Literal>();
		Literal l1 = new Literal('A', true);
		Literal l2 = new Literal('C', false);
		litset1.add(l1);
		litset1.add(l2);
		
		HashSet<Literal> litset2 = new HashSet<Literal>();
		Literal l3 = new Literal('C', true);
		Literal l4 = new Literal('B', false);
		litset2.add(l3);
		litset2.add(l4);

		HashSet<Literal> litset3 = new HashSet<Literal>();
		Literal l5 = new Literal('A', false);
		Literal l6 = new Literal('B', false);
		Literal l7 = new Literal('C', false);
		litset3.add(l5);
		litset3.add(l6);
		litset3.add(l7);
		
		HashSet<Literal> litset4 = new HashSet<Literal>();
		Literal l8 = new Literal('B', true);
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
		
		/**{{!A,!B,E},{C},{A},{!C,B},{!A,D}} */
		
//		HashSet<Literal> litset1 = new HashSet<Literal>();
//		Literal l1 = new Literal('A', false);
//		Literal l2 = new Literal('B', false);
//		Literal l3 = new Literal('E', true);
//		litset1.add(l1);
//		litset1.add(l2);
//		litset1.add(l3);
//		
//		HashSet<Literal> litset2 = new HashSet<Literal>();
//		Literal l4 = new Literal('C', true);
//		litset2.add(l4);
//
//		HashSet<Literal> litset3 = new HashSet<Literal>();
//		Literal l5 = new Literal('A', true);
//		litset3.add(l5);
//		
//		HashSet<Literal> litset4 = new HashSet<Literal>();
//		Literal l6 = new Literal('C', false);
//		Literal l7 = new Literal('B', true);
//		litset4.add(l6);
//		litset4.add(l7);
//		
//		HashSet<Literal> litset5 = new HashSet<Literal>();
//		Literal l8 = new Literal('A', false);
//		Literal l9 = new Literal('D', true);
//		litset5.add(l8);
//		litset5.add(l9);
//				
//		Klausel klausel1 = new Klausel(litset1);
//		Klausel klausel2 = new Klausel(litset2);
//		Klausel klausel3 = new Klausel(litset3);
//		Klausel klausel4 = new Klausel(litset4);
//		Klausel klausel5 = new Klausel(litset5);
//		
//		knf = new HashSet<Klausel>();
//
//		knf.add(klausel1);
//		knf.add(klausel2);
//		knf.add(klausel3);
//		knf.add(klausel4);
//		knf.add(klausel5);
//		
//		knfSort = new TreeSet<Klausel>(knf);
		
		System.out.println("normale Resolution:");
		zeit1 = System.currentTimeMillis();
		EinfacheResolution.resolvierenNormal(knf);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + EinfacheResolution.ausgabe(knf));
		System.out.println("Ergebnis: "  + EinfacheResolution.ergebnis(EinfacheResolution.resolvierenNormal(knf)));
		System.out.println(EinfacheResolution.zaehler1);
						
		System.out.println("\n" + "sortierte Resolution:");
		zeit1 = System.currentTimeMillis();
		SortResolution.resolvierenSortiert(knfSort);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + SortResolution.ausgabe(knfSort));
		System.out.println("Ergebnis: "  + SortResolution.ergebnis(SortResolution.resolvierenSortiert(knfSort)));
		System.out.println(SortResolution.zaehler1);
		
		System.out.println("\n" + "Einheitsresolution:");
		zeit1 = System.currentTimeMillis();
		EinheitsResolution.einheitsResolvieren(knfSort);
		zeit2 = System.currentTimeMillis();
		System.out.println("benoetigte Zeit: " + (zeit2-zeit1));
		System.out.println("Eingabe: " + EinheitsResolution.ausgabe(knfSort));
		System.out.println("Ergebnis: "  + EinheitsResolution.ergebnis(EinheitsResolution.einheitsResolvieren(knfSort)));
		System.out.println(EinheitsResolution.zaehler1);
		
	}
	
}
