package a11_turingmaschine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TM_Parser {

	static List<String> tm_definition = new ArrayList<String>();

	public TM_Parser(String txtName) throws IOException {
		BufferedReader txtDateiReader = new BufferedReader(new FileReader(txtName));
		String aktuelleZeile = txtDateiReader.readLine();
		while (aktuelleZeile != null) {
			tm_definition.add(aktuelleZeile);
			aktuelleZeile = txtDateiReader.readLine();
		}
		txtDateiReader.close();
	}

	public static List<Zustand> parse_zustaende() throws IOException {
		int anzahlZustaende = 0;
		List<Zustand> zustaende = new ArrayList<Zustand>();

		for (String line : tm_definition) {
			if (line.contains("Z:")) {
				anzahlZustaende = Integer.valueOf(line.substring(3, line.length()));
			}
		}
		List<Integer> endzustaende = parse_endzustaende();

		for (int i = 0; i < anzahlZustaende; i++) {
			StringBuilder builder = new StringBuilder();
			zustaende.add(new Zustand(builder.append('z').append(i).toString(), (0 == i), endzustaende.contains(i)));
		}
		return zustaende;
	}

	private static List<Integer> parse_endzustaende() throws IOException {
		List<Integer> endzustaende = new ArrayList<Integer>();
		for (String line : tm_definition) {
			if (line.contains("Z_end")) {
				endzustaende.add(Integer.valueOf(line.substring(7, line.length())));
			}
		}
		return endzustaende;
	}

	public static void parse_konfiguration(List<Zustand> zustaende) throws IOException {
		tm_definition.forEach(line -> {
			if (line.contains("->")) {
				int aktuellerZustand = Integer.valueOf(line.substring(0, line.indexOf('-') - 1));
				int folgeZustand = Integer.valueOf(line.substring(line.indexOf(">")+2, line.indexOf("(") - 1));
				String gelesen = line.substring(line.indexOf("(")+1, line.indexOf(","));
				String geschrieben = line.substring(line.indexOf(",")+1, line.lastIndexOf(","));
				String bewegung = line.substring(line.lastIndexOf(",")+1, line.indexOf(")"));
				zustaende.get(aktuellerZustand).addZustandsuebergang(gelesen, geschrieben, bewegung,
						zustaende.get(folgeZustand));
			}
		});
	}

}
