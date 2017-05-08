package welt;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class InselTest {

	@Test
	public void equals_givenNumber_thenFalse() {
		Assert.assertFalse(new Integer(8).equals(new Insel()));
	}
	
	@Test
	public void equals_givenRodos6x2andRodos6x2_thenTrue() {
		final Insel insel1 = new Insel();
		insel1.setName("Rodos");
		insel1.setX(6);
		insel1.setY(2);
		final Insel insel2 = new Insel();
		insel2.setName("Rodos");
		insel2.setX(6);
		insel2.setY(2);
		Assert.assertTrue(insel1.equals(insel2));
	}
}
