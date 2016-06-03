package a11_turingmaschine;

import java.io.IOException;

public class TM_Debugger {
	public static void debugRechnung(TM turingmaschine, String eingabe) {
		String output = turingmaschine.naechsteKonfiguration(eingabe);
		while (output.contains("(")) {
			System.out.println(output);
			output = turingmaschine.naechsteKonfiguration();
		}
		System.out.println(output);
	}
	
	public static void debugRechnungUntil(TM turingmaschine, String eingabe, int until) {
		String output = turingmaschine.naechsteKonfiguration(eingabe);
		
			while (output.contains("(") && until != 1) {
				System.out.println(output);
				output = turingmaschine.naechsteKonfiguration();
				until --;
			}
			System.out.println(output);
	}
	
	public static void debugManually(TM turingmaschine, String eingabe) throws IOException {
		System.out.println("Debug beginnt. Drücke Enter für nächste Konfiguration.");
		String output = turingmaschine.naechsteKonfiguration(eingabe);
		System.in.read();
		while (output.contains("(")) {
			System.out.println(output);
			output = turingmaschine.naechsteKonfiguration();
			System.in.read();
			System.in.read();
		}
		System.out.println(output);
	}
}
