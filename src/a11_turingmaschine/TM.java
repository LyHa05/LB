package a11_turingmaschine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TM {

	final String LEER_ZEICHEN = "#";
	private List<Zustand> zustaende = new ArrayList<Zustand>();
	private Zustand aktuellerZustand;
	private int LSK = 0;
	private String band;

	public TM(String tm_datei) throws IOException {
		new TM_Parser(tm_datei);
		zustaende = TM_Parser.parse_zustaende();
		TM_Parser.parse_konfiguration(zustaende);
		aktuellerZustand = zustaende.get(0);
	}

	public String naechsteKonfiguration(String eingabeband) {
		band = eingabeband;
		return naechsteKonfiguration();
	}

	public String naechsteKonfiguration() {
		String gelesen;

		if (LSK < 0 || LSK > band.length() - 1) {
			gelesen = LEER_ZEICHEN;
		} else {
			gelesen = String.valueOf(band.charAt(LSK));
		}
		List<String> uebergang = aktuellerZustand.getUebergang(gelesen);
		if (uebergang == null)
			return "Das Wort wird von der TM nicht akzeptiert.";
		String schreiben = uebergang.get(0);
		String bewegung = uebergang.get(1);
		Zustand folgeZustand = zustaende.get(Integer.valueOf(uebergang.get(2).substring(1, 2)));

		if (folgeZustand.isEndzustand())
			return "Das Wort wird von der TM akzeptiert.";

		aktuellerZustand = folgeZustand;
		write(band, schreiben);
		moveLSK(bewegung);
		return getAktuelleKonfiguration();
	}

	public String rechnung(int schrittzahl, String eingabeband) {
		String output = naechsteKonfiguration(eingabeband);
		while (output.contains("(") && schrittzahl != 1) {
			schrittzahl--;
			output = naechsteKonfiguration();
		}
		return output;
	}

	public String rechnung(String eingabeband) {
		String output = naechsteKonfiguration(eingabeband);
		while (output.contains("(")) {
			output = naechsteKonfiguration();
		}
		return output;
	}

	private void moveLSK(String bewegung) {
		if (bewegung.equals("L"))
			LSK--;
		if (bewegung.equals("R"))
			LSK++;
	}

	private void write(String eingabeband, String schreiben) {
		StringBuilder builder = new StringBuilder();
		band = builder.append(eingabeband).replace(LSK, LSK + 1, schreiben).toString();
	}

	public String getAktuelleKonfiguration() {
		StringBuilder builder = new StringBuilder();
		builder.append("(").append(band.substring(0, LSK)).append("-").append(aktuellerZustand.getName()).append("-")
				.append(band.substring(LSK, band.length())).append(")");
		return builder.toString();
	}
}
