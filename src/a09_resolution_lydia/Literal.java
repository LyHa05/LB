<<<<<<< HEAD
package a09_resolution_lydia;

public class Literal {
	public char bezeichner;
	public boolean wahrheitswert;
	
	public Literal(char bezeichner, boolean wahrheitswert) {
		this.bezeichner = bezeichner;
		this.wahrheitswert = wahrheitswert;
	}
	
	public Literal() {
		
	}
	
	public Literal(Literal lit) {
		this(lit.getBezeichner(), lit.getWahrheitswert());
	}

	public char getBezeichner() {
		return bezeichner;
	}

	public void changeWahrheitswert() {
		wahrheitswert = !wahrheitswert;
	}
	
	public boolean getWahrheitswert() {
		return wahrheitswert;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bezeichner;
		result = prime * result + (wahrheitswert ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Literal other = (Literal) obj;
		if (bezeichner != other.bezeichner) {
			return false;
		}
		if (wahrheitswert != other.wahrheitswert) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (wahrheitswert == false) {
			builder.append('!');
		}
		builder.append(bezeichner);
		return builder.toString();
	}
=======
package a09_resolution_lydia;

public class Literal {
	public char bezeichner;
	public boolean wahrheitswert;

	/** Konstruktor zum Erstellen von Literalen */
	public Literal(char bezeichner, boolean wahrheitswert) {
		this.bezeichner = bezeichner;
		this.wahrheitswert = wahrheitswert;
	}

	/** Konstruktor ohne Parameter */
	public Literal() {

	}

	/** Kopie-Konstruktor */
	public Literal(Literal lit) {
		this(lit.getBezeichner(), lit.getWahrheitswert());
	}

	/** Getter Methode fuer Bezeichner */
	public char getBezeichner() {
		return bezeichner;
	}

	/** Setter Methode fuer Wahrheitswert */
	public void changeWahrheitswert() {
		wahrheitswert = !wahrheitswert;
	}

	/** Getter Methode fuer Wahrheitswert */
	public boolean getWahrheitswert() {
		return wahrheitswert;
	}

	/** ueberschriebene Methode fuer hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bezeichner;
		result = prime * result + (wahrheitswert ? 1231 : 1237);
		return result;
	}

	/** ueberschriebene Methode fuer equals */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Literal other = (Literal) obj;
		if (bezeichner != other.bezeichner) {
			return false;
		}
		if (wahrheitswert != other.wahrheitswert) {
			return false;
		}
		return true;
	}

	/** ueberschriebene toString Methode */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (wahrheitswert == false) {
			builder.append('!');
		}
		builder.append(bezeichner);
		return builder.toString();
	}
>>>>>>> branch 'master' of https://github.com/LyHa05/LB.git
}