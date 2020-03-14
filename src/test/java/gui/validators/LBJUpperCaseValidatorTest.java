package gui.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gui.validators.LBJUpperCaseValidator;

public class LBJUpperCaseValidatorTest {

	private LBJUpperCaseValidator tested = new LBJUpperCaseValidator();

	@Test
	public void testIsValidTrue() throws Exception {
		assertTrue(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidFalse() throws Exception {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("java"));
	}

}
