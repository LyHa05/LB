package Aufgabe_09;

public class Literal {

	public String bezeichner;
	public boolean wahrheitswert;
	
	private Literal(String bezeichner, boolean wahrheitswert) {
		this.bezeichner = bezeichner;
		this.wahrheitswert = wahrheitswert;
	}

	public String getBezeichner() {
		return bezeichner;
	}

	public boolean isWahrheitswert() {
		return wahrheitswert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bezeichner == null) ? 0 : bezeichner.hashCode());
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
		if (bezeichner == null) {
			if (other.bezeichner != null) {
				return false;
			}
		} else if (!bezeichner.equals(other.bezeichner)) {
			return false;
		}
		if (wahrheitswert != other.wahrheitswert) {
			return false;
		}
		return true;
	}
	
	
}
