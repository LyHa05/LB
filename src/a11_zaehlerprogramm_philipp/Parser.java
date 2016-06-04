package a11_zaehlerprogramm_philipp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	private List<String> programm = new ArrayList<String>();
	private List<String> code = new ArrayList<String>();
	private List<Zaehler> zaehlerlist = new ArrayList<Zaehler>();

	public Parser(String txt) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(txt));
		String line = br.readLine();
		// while Zählerprogramm wird gelesen
		while (line != null) {
			programm.add(line);
			line = br.readLine();
		}
		// schließt die Datei wieder
		br.close();
	}

	
	public void compile() {
		programm.forEach(c -> parse_zaehler(c));
		programm.forEach(c -> parse_programm(c));
	}

	private void parse_zaehler(String line) {
		if (line.startsWith("c")) {
			zaehlerlist.add(new Zaehler("c" + Integer.valueOf(line.substring((line.indexOf("c") + 1), line.indexOf("c") + 2)),
							Integer.valueOf(line.substring((line.indexOf("=") + 2)))));
		}
	}

	private void parse_programm(String line) {
		if (line.contains("++")) {
			// Zählerdekrement
			code.add((line.substring((line.indexOf("c")), (line.indexOf("++") - 1)) + "++"));
		} else if (line.contains("--")) {
			// Zählerinkrement
			code.add((line.substring((line.indexOf("c")), (line.indexOf("--") - 1)) + "--"));
		} else if (line.contains("if")) {
			// Sprung
			String Zaehler = (line.substring((line.indexOf("c")), (line.indexOf("c") + 2)));
			String kennziffer = (line.substring((line.indexOf("t") - 2), (line.indexOf("t") - 1)));
			String fall1 = line.substring((line.indexOf("b") + 1), (line.indexOf("b") + 2));
			String fall2 = line.substring((line.indexOf("se") + 4), (line.indexOf("se") + 5));
			if (line.contains("==")) {
				code.add(Zaehler + "==" + kennziffer + ":" + fall1 + " " + fall2);
			} else if (line.contains("=>")) {
				code.add(Zaehler + "=>" + kennziffer + ":" + fall1 + " " + fall2);
			} else if (line.contains(">")) {
				code.add(Zaehler + ">" + kennziffer + ":" + fall1 + " " + fall2);
			} else if (line.contains("<")) {
				code.add(Zaehler + "<" + kennziffer + ":" + fall1 + " " + fall2);
			} else {
				code.add(Zaehler + "=<" + kennziffer + ":" + fall1 + " " + fall2);
			}
		}
	}

	public List<String> getCode() {
		return code;
	}

	public List<Zaehler> getZaehler() {
		return zaehlerlist;
	}

}