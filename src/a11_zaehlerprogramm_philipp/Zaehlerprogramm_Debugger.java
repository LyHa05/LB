package a11_zaehlerprogramm_philipp;

import java.io.IOException;

public class Zaehlerprogramm_Debugger {
	public static void debugRechnung(Zaehlerprogramm programm) {
		String output = programm.getAktuelleProgrammzeile();
		System.out.println(output);
	}

	public static void debugRechnungUntil(Zaehlerprogramm programm, int until) {
		String output = programm.getAktuelleProgrammzeile();
		while (until != 1) {
			System.out.println(output);
			until--;
		}
		System.out.println(output);
	}

	public static void debugManually(Zaehlerprogramm programm) throws IOException {
		System.out.println("Dr�cke Enter f�r die n�chste Zeile.");
		String output = programm.getAktuelleProgrammzeile();
		System.in.read();
		System.in.read();
		System.out.println(output);
	}
}
