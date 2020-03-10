package gui.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LBJUpperCaseStringValidatorTest {

	private LBJUpperCaseStringValidator tested = new LBJUpperCaseStringValidator();

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