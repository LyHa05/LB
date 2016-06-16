package a11_zaehlerprogramm_philipp;

import java.io.IOException;

public class Ausfuehrbar {

	public static void main(String[] args) throws IOException {
		Zaehlerprogramm test = new Zaehlerprogramm("Zaehlerprogramm.txt", "");
		test.programmAusfuehren();

//		Zaehlerprogramm test1 = new Zaehlerprogramm("Zaehlerprogramm.txt", "m");
//		test1.programmAusfuehren();
		
//		Zaehlerprogramm test2 = new Zaehlerprogramm("Zaehlerprogramm.txt", "t");
//		test2.programmAusfuehren();
	}

}
