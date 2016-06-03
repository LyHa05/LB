package a11_zaehlerprogramm_jasmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zaehlerprogramm {
	List<Zaehler> zaehlerListe = new ArrayList<Zaehler>();
	List<String> programm = new ArrayList<String>();
	private List<String> programmablauf = new ArrayList<String>();
	private Map<String, Object> programmausfuehrung = new HashMap<String, Object>();
	Integer aktuelleProgrammzeile = 0;

	public Zaehlerprogramm(String txt) throws IOException {
		new ZaehlerParser(txt);
		zaehlerListe = ZaehlerParser.parseZaehler();
		programm = ZaehlerParser.parseProgramm();
		programmablauf = ZaehlerParser.parseProgrammablauf();
		programmausfuehrung = ZaehlerParser.parseProgrammausfuehrung(zaehlerListe);
	}

	public void runZaehlerprogramm() {
		while (aktuelleProgrammzeile < programmablauf.size() - 1) {		
			System.out.println(aktuelleProgrammzeile);
			zaehlerListe.forEach(zaehler -> System.out.println(zaehler.getWert()));
			ausfuehren(programmablauf.get(aktuelleProgrammzeile));
		}
	}

	public String ausfuehren(String aktuelleZeile) {
		Object auszufuehren = programmausfuehrung.get(aktuelleZeile);
		if (auszufuehren instanceof String) {
			if (auszufuehren.toString().isEmpty())
				;//aktuelleProgrammzeile++;
			else if (auszufuehren.toString().contains("++"))
				doIncrement(auszufuehren.toString().substring(0, auszufuehren.toString().indexOf("+")));
			else if (auszufuehren.toString().contains("--"))
				doDecrement(auszufuehren.toString().substring(0, auszufuehren.toString().indexOf("-")));
		} else if (auszufuehren instanceof Sprung) {
			doSprung((Sprung) auszufuehren);
		}

		return aktuelleProgrammzeile.toString();

	}

	private void doSprung(Sprung auszufuehrenderSprung) {
		Boolean bedingung;
		
		switch (auszufuehrenderSprung.getBedingung()) {
		case "<":
			bedingung = (auszufuehrenderSprung.getZaehlerToCompare().getWert() < auszufuehrenderSprung.getNumberToCompare());
			break;
		case ">":
			bedingung = (auszufuehrenderSprung.getZaehlerToCompare().getWert() > auszufuehrenderSprung.getNumberToCompare());
			break;
		case "<=":
			bedingung = (auszufuehrenderSprung.getZaehlerToCompare().getWert() <= auszufuehrenderSprung.getNumberToCompare());
			break;
		case ">=":
			bedingung = (auszufuehrenderSprung.getZaehlerToCompare().getWert() >= auszufuehrenderSprung.getNumberToCompare());
			break;
		case "==":
			bedingung = (auszufuehrenderSprung.getZaehlerToCompare().getWert() == auszufuehrenderSprung.getNumberToCompare());
			break;
		default:
			bedingung = null;
			break;
		}
		
		System.out.println(bedingung);
		
		if (bedingung) {
			aktuelleProgrammzeile = programmablauf.indexOf(auszufuehrenderSprung.getThenDo());
		} else {
			aktuelleProgrammzeile = programmablauf.indexOf(auszufuehrenderSprung.getElseDo());
		}
	}

	private void doIncrement(String zaehlerName) {
		zaehlerListe.forEach(zaehler -> {
			if (zaehler.getName().equals(zaehlerName))
				zaehler.inkrement();
		});
		aktuelleProgrammzeile++;
	}

	private void doDecrement(String zaehlerName) {
		zaehlerListe.forEach(zaehler -> {
			if (zaehler.getName().equals(zaehlerName))
				zaehler.dekrement();
		});
		aktuelleProgrammzeile++;
	}
}
