package a11_zaehlerprogramm_jasmin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZaehlerParser {
	private static List<String> txtZaehler = new ArrayList<String>();

	public ZaehlerParser(String txt) throws IOException {
		BufferedReader txtDateiReader = new BufferedReader(new FileReader(txt));
		String aktuelleZeile = txtDateiReader.readLine();
		while (aktuelleZeile != null) {
			txtZaehler.add(aktuelleZeile);
			aktuelleZeile = txtDateiReader.readLine();
		}
		txtDateiReader.close();
	}
	
	public static List<String> parseProgramm() {
		return txtZaehler;
	}

	public static List<Zaehler> parseZaehler() {
		List<Zaehler> zaehlerList = new ArrayList<Zaehler>();
		int anzahlZaehler = 1;
		for (String line : txtZaehler) {
			if (line.startsWith("c")) {
				zaehlerList.add(
						new Zaehler("c" + anzahlZaehler, Integer.valueOf(line.substring((line.indexOf("=") + 2)))));
				anzahlZaehler++;
			}
		}
		return zaehlerList;
	}

	public static Map<String, Object> parseProgrammausfuehrung(List<Zaehler> zaehlerList) {
		Map<String, Object> programmausfuehrung = new HashMap<String, Object>();
		Integer zeile = 0;
		for (String line : txtZaehler) {
			if (line.startsWith("b")) {
				if (line.contains("++")) programmausfuehrung.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf("c")));
				else if (line.contains("--")) programmausfuehrung.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf("c")));
				else programmausfuehrung.put(line, "");
			}

			if (line.startsWith("if")) {
				String sprungBedingung = line.substring(3, line.indexOf("t")-1);
				String thenElse = line.substring(line.indexOf("n")+2);
			
				Zaehler zaehlerToCompare = null;
				System.out.println(sprungBedingung.substring(0, sprungBedingung.indexOf(" ")));
				for (Zaehler zaehler : zaehlerList) {
					if (zaehler.getName().equals(sprungBedingung.substring(0, sprungBedingung.indexOf(" ")))) {
						zaehlerToCompare = zaehler;
					}
				}
				String bedingung = sprungBedingung.substring(sprungBedingung.indexOf(" ")+1, sprungBedingung.lastIndexOf(" "));
				int numberToCompare = Integer.valueOf(sprungBedingung.substring(sprungBedingung.lastIndexOf(" ")+1));
				String thenDo = thenElse.substring(0, thenElse.indexOf(" "));
				String elseDo = thenElse.substring(thenElse.lastIndexOf(" ")+1);
				
				
				Sprung sprung = new Sprung(zaehlerToCompare, bedingung, numberToCompare, thenDo, elseDo);
				programmausfuehrung.put("if" + zeile.toString(), sprung);
				
				zeile++;
			}
		}
		return programmausfuehrung;
	}
	public static List<String> parseProgrammablauf() {
		List<String> programmablauf = new ArrayList<String>();
		Integer zeile = 0;
		for (String line : txtZaehler) {
			if (line.startsWith("b")) {
				if (line.contains("++")) programmablauf.add(line.substring(0, line.indexOf(":")));
				else if (line.contains("--")) programmablauf.add(line.substring(0, line.indexOf(":")));
				else programmablauf.add(line);
			}
			StringBuilder builder = new StringBuilder();
			if (line.startsWith("if")) {
				builder.append(line.substring(3, line.indexOf("t") - 1)).append(":")
				.append(line.substring(line.indexOf("b"), line.indexOf("else") - 1)).append(",")
				.append(line.substring(line.lastIndexOf("b")));
				programmablauf.add("if" + zeile.toString());
				zeile++;
			}
		}
		return programmablauf;
	}
}