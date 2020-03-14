package gui.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gui.validators.LBJLowerCaseValidator;

public class LBJLowerCaseValidatorTest {

	private LBJLowerCaseValidator tested = new LBJLowerCaseValidator();

	@Test
	public void testIsValidFalse() {
		assertFalse(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidTrue() {
		assertTrue(tested.isValid("java"));
	}

}
