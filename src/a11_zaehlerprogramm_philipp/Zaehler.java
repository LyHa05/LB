package a11_zaehlerprogramm_philipp;

public class Zaehler {
	public String name;
	public int zaehler;
	
	public Zaehler(String name, int zaehler) {
		this.name = name;
		this.zaehler = zaehler;
	}

	public void dekrement(){
		zaehler--;
	}
	
	public void inkrement(){
		zaehler++;
	}
	
	public String getName() {
		return  name;
	}
	
	public int getZaehlerwert() {
		return zaehler;
	}

	public Zaehler getZaehler() {
		return this;
	}

	@Override
	public String toString() {
		return "Zaehler [name=" + name + ", zaehler=" + zaehler + "]";
	}
	
}
