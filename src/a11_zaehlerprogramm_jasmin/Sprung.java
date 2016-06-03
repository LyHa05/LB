package a11_zaehlerprogramm_jasmin;

public class Sprung {
	Zaehler zaehlerToCompare;
	String bedingung;
	int numberToCompare;
	String thenDo;
	String elseDo;
	
	public Sprung(Zaehler zaehlerToCompare, String bedingung, int numberToCompare, String thenDo, String elseDo) {
		this.zaehlerToCompare = zaehlerToCompare;
		this.bedingung = bedingung;
		this.numberToCompare = numberToCompare;
		this.thenDo = thenDo;
		this.elseDo = elseDo;
	}
	
	public Zaehler getZaehlerToCompare() {
		return zaehlerToCompare;
	}
	public String getBedingung() {
		return bedingung;
	}
	public int getNumberToCompare() {
		return numberToCompare;
	}
	public String getThenDo() {
		return thenDo;
	}
	public String getElseDo() {
		return elseDo;
	}
}
