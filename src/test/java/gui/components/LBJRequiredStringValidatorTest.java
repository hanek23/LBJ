package gui.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LBJRequiredStringValidatorTest {

	private LBJRequiredStringValidator tested = new LBJRequiredStringValidator();

	@Test
	public void testIsValidTrue() {
		assertTrue(tested.isValid("LBJ is the GOAT"));
	}

	@Test
	public void testIsValidNull() {
		assertFalse(tested.isValid(null));
	}

	@Test
	public void testIsValidEmpty() {
		assertFalse(tested.isValid(""));
	}

	@Test
	public void testIsValidWhitespace() {
		assertFalse(tested.isValid(" "));
	}

}
