package Aufgabe_09;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class KNFTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		Set<Literal> litset1 = new HashSet<Literal>();
		litset1.add(new Literal('A', true));
		litset1.add(new Literal('A', false));
		litset1.add(new Literal('B', true));
		litset1.add(new Literal('B', false));
		
		Set<Literal> litset2 = new HashSet<Literal>();
		litset2.add(new Literal('A', true));
		litset2.add(new Literal('C', true));
		
		Klausel klausel1 = new Klausel(litset1);
		Klausel klausel2 = new Klausel(litset2);
		
		System.out.println(KNF.returnResolvent(klausel1, klausel2));
	}

}
