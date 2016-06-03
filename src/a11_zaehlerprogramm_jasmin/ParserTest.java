package a11_zaehlerprogramm_jasmin;

import java.io.IOException;

import org.junit.Test;

public class ParserTest {
	//Funktioniert, aber (noch) ohne Ausgabe.
	@Test
	public void test() throws IOException {
		/**
		 * gibt alle Files im Ordner aus
		 */
		//File file = new File(".");
		//for(String fileNames : file.list()) System.out.println(fileNames);
		
		Zaehlerprogramm z = new Zaehlerprogramm("Zaehlerprogramm.txt");
		z.runZaehlerprogramm();
	}

}
