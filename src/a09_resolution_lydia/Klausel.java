<<<<<<< HEAD
package a09_resolution_lydia;

import java.util.HashSet;

public class Klausel implements Comparable<Klausel> {
	private HashSet<Literal> literale;

	public Klausel(HashSet<Literal> literale) {
		this.literale = literale;
	}
	
	public Klausel(Klausel klau) {
		this(klau.getLiterale());
	}

	public Klausel() {
		
		this.literale = new HashSet<Literal>();
	}
	
	public HashSet<Literal> getLiterale() {
		return literale;
	}
	
	public void setLiteral(Literal literal) {
			literale.add(literal);
	}
	
	public void setLiterale(HashSet<Literal> literale) {
		this.literale = literale;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literale == null) ? 0 : literale.hashCode());
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
		Klausel other = (Klausel) obj;
		if (literale == null) {
			if (other.literale != null) {
				return false;
			}
		} else if (!literale.equals(other.literale)) {
			return false;
		}
		return true;
	}

	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Object[] lit = literale.toArray();
		builder.append('{');
		
		if (lit.length != 0) {
		for (int i = 0; i < lit.length-1; i++) {
			builder.append(lit[i].toString()).append(", ");
		}
			builder.append(lit[lit.length-1]);
		} else {
			builder.append(' ');
		}
		builder.append('}');
		return builder.toString();
	}

	@Override
	public int compareTo(Klausel k) {
		if (this.equals(k)) return 0;
		if (this.getLiterale().size() == k.getLiterale().size()) return -1;
		if (!this.equals(k)) return (this.getLiterale().size() - k.getLiterale().size());
		return 0; 
	}
	
	

}
=======
package a09_resolution_lydia;

import java.util.HashSet;

public class Klausel implements Comparable<Klausel> {
	private HashSet<Literal> literale;

	/** Konstruktor mit uebergebenen HashSet aus Literalen */
	public Klausel(HashSet<Literal> literale) {
		this.literale = literale;
	}

	/** Kopie-Konstruktor */
	public Klausel(Klausel klau) {
		this(klau.getLiterale());
	}

	/** Konstruktor ohne Parameter */
	public Klausel() {

		this.literale = new HashSet<Literal>();
	}

	/** Getter Methode fuer HashSet aus Literalen */
	public HashSet<Literal> getLiterale() {
		return literale;
	}

	/** Setter Methode fuer Literal */
	public void setLiteral(Literal literal) {
		literale.add(literal);
	}

	/** Setter Methode fuer HashSet aus Literalen */
	public void setLiterale(HashSet<Literal> literale) {
		this.literale = literale;
	}

	/** ueberschriebene Methode fuer hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literale == null) ? 0 : literale.hashCode());
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
		Klausel other = (Klausel) obj;
		if (literale == null) {
			if (other.literale != null) {
				return false;
			}
		} else if (!literale.equals(other.literale)) {
			return false;
		}
		return true;
	}

	/** ueberschriebene toString Methode */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Object[] lit = literale.toArray();
		builder.append('{');

		if (lit.length != 0) {
			for (int i = 0; i < lit.length - 1; i++) {
				builder.append(lit[i].toString()).append(", ");
			}
			builder.append(lit[lit.length - 1]);
		} else {
			builder.append(' ');
		}
		builder.append('}');
		return builder.toString();
	}

	/** ueberschriebene Methode fuer compareTo */
	@Override
	public int compareTo(Klausel k) {
		if (this.equals(k))
			return 0;
		if (this.getLiterale().size() == k.getLiterale().size())
			return -1;
		if (!this.equals(k))
			return (this.getLiterale().size() - k.getLiterale().size());
		return 0;
	}

}
>>>>>>> branch 'master' of https://github.com/LyHa05/LB.git
