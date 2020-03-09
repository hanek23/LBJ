package gui.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LBJStringLengthValidatorTest {

	private LBJStringLengthValidator tested = new LBJStringLengthValidator();

	@Test
	public void testIsValid40() {
		assertFalse(tested.isValid("SC30 is the greatest shooter of all time"));
	}

	@Test
	public void testIsValid31() {
		assertFalse(tested.isValid("Rachmaninoff - Piano Concerto 2"));
	}

	@Test
	public void testIsValid30() {
		assertTrue(tested.isValid("Inky Johnson is a bad somebody"));
	}

	@Test
	public void testIsValid16() {
		assertTrue(tested.isValid("STAY OF THE WEED"));
	}

}
