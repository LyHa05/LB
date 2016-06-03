package a11_zaehlerprogramm_jasmin;

public class Zaehler {
	public int wert;
	public String name;

	public Zaehler(String name, int zaehler) {
		this.name = name;
		this.wert = zaehler;
	}

	public void dekrement() {
		wert--;
	}

	public void inkrement() {
		wert++;
	}

	public String getName() {
		return name;
	}

	public int getWert() {
		return wert;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append(name).append(" = ").append(wert).toString();
	}

}
