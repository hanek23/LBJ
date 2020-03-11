package gui.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gui.validators.LBJRequiredStringValidator;

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
