package a11_turingmaschine;

import java.io.IOException;
// Um mit Aufgabe 12 rumzuprobieren "LB_Aufgabe12" der TM übergeben, ansonsten
//"TM_Datei.txt" eintragen. Aufgabe 12 macht f(a^x b^y) = b^y a^x, die TM-Datei ersetzt a's durch X.
public class AusprobierDatei {

	public static void main(String[] args) throws IOException {
//	TM unsereTM = new TM("LB_Aufgabe12");
//	TM_Debugger.debugManually(unsereTM, "aabb");
//	
//	System.out.println(" ");
//	System.out.println(" ");
	
		
	TM unsereTM1 = new TM("LB_Aufgabe12");
	TM_Debugger.debugRechnung(unsereTM1, "aaaabbbbbaa");
	
//	System.out.println(" ");
//	System.out.println(" ");
//	
//	TM unsereTM2 = new TM("TM_Datei.txt");
//	TM_Debugger.debugRechnungUntil(unsereTM2, "aabb", 2);
	}

}
